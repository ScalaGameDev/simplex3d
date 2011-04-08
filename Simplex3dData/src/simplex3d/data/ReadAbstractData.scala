/*
 * Simplex3d, CoreData module
 * Copyright (C) 2010-2011, Aleksey Nikiforov
 *
 * This file is part of Simplex3dData.
 *
 * Simplex3dData is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Simplex3dData is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package simplex3d.data

import java.nio._
import scala.annotation._
import scala.annotation.unchecked._
import scala.collection._
import StoreType._


/**
 * @author Aleksey Nikiforov (lex)
 */
private[data] abstract class ReadAbstractData[
  E <: Meta, @specialized(Int, Float, Double) ReadAs, +R <: Raw
](
  shared: AnyRef, prim: AnyRef, ro: Boolean,
  final val offset: Int, final val stride: Int
) extends ProtectedData[R#Array @uncheckedVariance](shared) with DataFactory[E, R]
with IndexedSeq[ReadAs] with IndexedSeqOptimized[ReadAs, IndexedSeq[ReadAs]] {

  // Argument checks.
  assert(components >= 1)

  if (offset < 0)
    throw new IllegalArgumentException(
      "Offset must be greater than or equal to zero."
    )
  if (stride < components)
    throw new IllegalArgumentException(
      "Stride must be greater than or equal to components."
    )
  if (offset > stride - components)
    throw new IllegalArgumentException(
      "Offset must be less than (stride - components)."
    )
  
  // Init.
  final val primitive: Primitive = {
    if (prim == null) this.asInstanceOf[Primitive]
    else prim.asInstanceOf[Primitive]
  }
  
  protected final val storeType = storeFromRaw(rawType)

  private[data] final val buff: R#Buffer = {
    if (prim != null) {
      primitive.buff
    }
    else {
      (if (sharedStore.isInstanceOf[ByteBuffer]) {
        if (!sharedBuffer.isDirect) {
          throw new IllegalArgumentException(
            "The buffer must be direct."
          )
        }

        val byteBuffer = {
          if (ro) sharedBuffer.asReadOnlyBuffer()
          else sharedBuffer.duplicate()
        }

        byteBuffer.clear()
        byteBuffer.order(ByteOrder.nativeOrder)

        (storeType: @switch) match {
          case ByteStore => byteBuffer
          case ShortStore => byteBuffer.asShortBuffer()
          case CharStore => byteBuffer.asCharBuffer()
          case IntStore => byteBuffer.asIntBuffer()
          case FloatStore => byteBuffer.asFloatBuffer()
          case DoubleStore => byteBuffer.asDoubleBuffer()
        }
      }
      else {
        val buff = Util.wrapArray(storeType, sharedArray)
        if (ro) Util.readOnlyBuff(storeType, buff) else buff
      }).asInstanceOf[R#Buffer]
    }
  }
  
  if (offset > buff.capacity)
    throw new IllegalArgumentException(
      "Offset must not be greater than limit."
    )

  private[data] final def sizeFrom(capacity: Int, offset: Int, stride: Int, components: Int) :Int = {
    val s = (capacity - offset + stride - components)/stride
    if (s > 0) s else 0
  }

  final override val size = sizeFrom(buff.capacity, offset, stride, components)
  final def length = size

  // Type definitions.
  type RawBuffer <: Buffer
  type Primitive <: ReadContiguous[E#Component, R]

  // Public API.
  def rawType: Int
  def components: Int
  def elemManifest: ClassManifest[E]
  def readManifest: ClassManifest[E#Read]
  def isNormalized: Boolean

  final val bytesPerComponent = RawType.byteLength(rawType)
  final def byteCapacity = {
    if (sharedStore.isInstanceOf[ByteBuffer]) sharedBuffer.capacity
    else buff.capacity*bytesPerComponent
  }
  final def byteOffset = offset*bytesPerComponent
  final def byteStride = stride*bytesPerComponent


  final def isReadOnly: Boolean = buff.isReadOnly()
  final def sharesStoreObject(seq: inDataSeq[_, _]) :Boolean = {
    sharedStore eq seq.sharedStore
  }

  def apply(i: Int) :ReadAs

  final def readOnlyBuffer() :R#Buffer = Util.readOnlyBuff(storeType, buff).asInstanceOf[R#Buffer]
  

  private[data] def mkReadOnlyInstance() :ReadDataSeq[E, R]
  def asReadOnly() :ReadDataSeq[E, R]
  private[data] final lazy val readOnlySeq :AnyRef = if (isReadOnly) this else mkReadOnlyInstance()


  private[this] final def binding() :Buffer = {
    if (sharedStore.isInstanceOf[ByteBuffer]) {
      val buff = sharedStore.asInstanceOf[ByteBuffer].asReadOnlyBuffer()
      buff.order(ByteOrder.nativeOrder)
      buff
    }
    else {
      Util.duplicateBuff(storeType, buff)
    }
  }

  final def rawBuffer() :RawBuffer = {
    val buff = binding()
    buff.limit(buff.capacity)
    buff.position(0)
    buff.asInstanceOf[RawBuffer]
  }
  final def rawBufferWithOffset() :RawBuffer = {
    val buff = binding()

    if (sharedStore.isInstanceOf[ByteBuffer]) {
      buff.limit(buff.capacity)
      buff.position(offset*bytesPerComponent)
    }
    else {
      buff.position(offset)
    }

    buff.asInstanceOf[RawBuffer]
  }
  final def rawBufferSubData(first: Int, count: Int) :RawBuffer = {
    val buff = binding()

    if (sharedStore.isInstanceOf[ByteBuffer]) {
      val off = first*stride*bytesPerComponent
      var lim = off + count*stride*bytesPerComponent
      if (lim > buff.capacity && first + count == size) lim = buff.capacity
      buff.limit(lim)
      buff.position(off)
    }
    else {
      val off = first*stride
      buff.limit(off + count*stride)
      buff.position(off)
    }

    buff.asInstanceOf[RawBuffer]
  }


  final def copyAsDataArray() :DataArray[E, R] = {
    val copy = mkDataArray(size)
    copy.put(0, primitive, this.offset, this.stride, size)
    copy
  }
  final def copyAsDataBuffer() :DataBuffer[E, R] = {
    val copy = mkDataBuffer(size)
    copy.put(0, primitive, this.offset, this.stride, size)
    copy
  }


  override def toString() :String = {
    def getElemName() = {
      elemManifest.erasure.getSimpleName
    }

    var view = false

    (if (isReadOnly) "ReadOnly" else "") +
    (this match {
      case s: DataArray[_, _] => "DataArray"
      case s: DataBuffer[_, _] => "DataBuffer"
      case s: DataView[_, _] => view = true; "DataView"
    }) +
    "[" + getElemName() + ", " + RawType.name(rawType)+ "](" +
    (if (view) "offset = " + offset + ", " else "") +
    "stride = " + stride + ", size = " + size + ")"
  }
}
