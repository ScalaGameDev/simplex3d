/*
 * Simplex3d, IntBuffer module
 * Copyright (C) 2010, Simplex3d Team
 *
 * This file is part of Simplex3dBuffer.
 *
 * Simplex3dBuffer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Simplex3dBuffer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package simplex3d.buffer.intm
package impl

import java.nio._
import simplex3d.math.intm._
import simplex3d.buffer._


/**
 * @author Aleksey Nikiforov (lex)
 */
// Vec3i SInt
private[buffer] final class ArrayVec3iSInt(
  backing: ArrayInt1SInt
) extends BaseVec3i[SInt](backing, 0, 3) with DataArray[Vec3i, SInt] {
  def this() = this(new ArrayInt1SInt)
  protected[buffer] def mkReadOnlyInstance() = new ArrayVec3iSInt(backing.mkReadOnlyInstance())

  def apply(i: Int) :ConstVec3i = {
    val j = i*3
    ConstVec3i(
      backing(j),
      backing(j + 1),
      backing(j + 2)
    )
  }
  def update(i: Int, v: ReadVec3i) {
    val j = i*3
    backing(j) = v.x
    backing(j + 1) = v.y
    backing(j + 2) = v.z
  }

  override def mkDataArray(array: Array[Int]) =
    new ArrayVec3iSInt(backing.mkDataArray(array))
  override def mkReadDataBuffer(byteBuffer: ByteBuffer) =
    new BufferVec3iSInt(backing.mkReadDataBuffer(byteBuffer))
  override protected def mkReadDataViewInstance(byteBuffer: ByteBuffer, offset: Int, stride: Int) =
    new ViewVec3iSInt(backing.mkReadDataBuffer(byteBuffer), offset, stride)
}

private[buffer] final class BufferVec3iSInt(
  backing: BufferInt1SInt
) extends BaseVec3i[SInt](backing, 0, 3) with DataBuffer[Vec3i, SInt] {
  protected[buffer] def mkReadOnlyInstance() = new BufferVec3iSInt(backing.mkReadOnlyInstance())

  def apply(i: Int) :ConstVec3i = {
    val j = i*3
    ConstVec3i(
      backing(j),
      backing(j + 1),
      backing(j + 2)
    )
  }
  def update(i: Int, v: ReadVec3i) {
    val j = i*3
    backing(j) = v.x
    backing(j + 1) = v.y
    backing(j + 2) = v.z
  }

  override def mkDataArray(array: Array[Int]) =
    new ArrayVec3iSInt(backing.mkDataArray(array))
  override def mkReadDataBuffer(byteBuffer: ByteBuffer) =
    new BufferVec3iSInt(backing.mkReadDataBuffer(byteBuffer))
  override protected def mkReadDataViewInstance(byteBuffer: ByteBuffer, offset: Int, stride: Int) =
    new ViewVec3iSInt(backing.mkReadDataBuffer(byteBuffer), offset, stride)
}

private[buffer] final class ViewVec3iSInt(
  backing: BufferInt1SInt, off: Int, str: Int
) extends BaseVec3i[SInt](backing, off, str) with DataView[Vec3i, SInt] {
  protected[buffer] def mkReadOnlyInstance() = new ViewVec3iSInt(backing.mkReadOnlyInstance(), offset, stride)

  def apply(i: Int) :ConstVec3i = {
    val j = offset + i*stride
    ConstVec3i(
      backing(j),
      backing(j + 1),
      backing(j + 2)
    )
  }
  def update(i: Int, v: ReadVec3i) {
    val j = offset + i*stride
    backing(j) = v.x
    backing(j + 1) = v.y
    backing(j + 2) = v.z
  }

  override def mkDataArray(array: Array[Int]) =
    new ArrayVec3iSInt(backing.mkDataArray(array))
  override def mkReadDataBuffer(byteBuffer: ByteBuffer) =
    new BufferVec3iSInt(backing.mkReadDataBuffer(byteBuffer))
  override protected def mkReadDataViewInstance(byteBuffer: ByteBuffer, offset: Int, stride: Int) =
    new ViewVec3iSInt(backing.mkReadDataBuffer(byteBuffer), offset, stride)
}
