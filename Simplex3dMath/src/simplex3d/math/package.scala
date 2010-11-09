/*
 * Simplex3d, CoreMath module
 * Copyright (C) 2010, Simplex3d Team
 *
 * This file is part of Simplex3dMath.
 *
 * Simplex3dMath is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Simplex3dMath is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package simplex3d

import java.nio._


/**
 * @author Aleksey Nikiforov (lex)
 */
package object math {

  // Implicits
  implicit def imInt(s: Int) = new ExtendedInt(s)

  // In and Out aliases

  /** <code>in</code> prefix for Vec2b.
   * Use the prefix when declaring functions.
   */
  type inVec2b = ReadVec2b

  /** <code>out</code> prefix for Vec2b.
   * Use the prefix when declaring functions.
   */
  type outVec2b = Vec2b with Implicits[Off]
  @inline implicit def outVec2b(u: Vec2b) = u.asInstanceOf[outVec2b]


  /** <code>in</code> prefix for Vec3b.
   * Use the prefix when declaring functions.
   */
  type inVec3b = ReadVec3b

  /** <code>out</code> prefix for Vec3b.
   * Use the prefix when declaring functions.
   */
  type outVec3b = Vec3b with Implicits[Off]
  @inline implicit def outVec3b(u: Vec3b) = u.asInstanceOf[outVec3b]


  /** <code>in</code> prefix for Vec4b.
   * Use the prefix when declaring functions.
   */
  type inVec4b = ReadVec4b

  /** <code>out</code> prefix for Vec4b.
   * Use the prefix when declaring functions.
   */
  type outVec4b = Vec4b with Implicits[Off]
  @inline implicit def outVec4b(u: Vec4b) = u.asInstanceOf[outVec4b]


  type inVec2i = ReadVec2i
  type outVec2i = Vec2i with Implicits[Off]
  @inline implicit def outVec2i(u: Vec2i) = u.asInstanceOf[outVec2i]

  type inVec3i = ReadVec3i
  type outVec3i = Vec3i with Implicits[Off]
  @inline implicit def outVec3i(u: Vec3i) = u.asInstanceOf[outVec3i]

  type inVec4i = ReadVec4i
  type outVec4i = Vec4i with Implicits[Off]
  @inline implicit def outVec4i(u: Vec4i) = u.asInstanceOf[outVec4i]


  // Casting.

  /** Casts a Boolean to a Boolean. This method is here for completeness.
   * @param x a value to cast.
   * @return x.
   */
  @inline def bool(x: Boolean) :Boolean = x

  /** Casts a Byte to a Boolean.
   * @param x a value to cast.
   * @return false if x == 0, true otherwise.
   */
  @inline def bool(x: Byte) :Boolean = (x != 0)

  /** Casts a Short to a Boolean.
   * @param x a value to cast.
   * @return false if x == 0, true otherwise.
   */
  @inline def bool(x: Short) :Boolean = (x != 0)

  /** Casts an Int to a Boolean.
   * @param x a value to cast.
   * @return false if x == 0, true otherwise.
   */
  @inline def bool(x: Int) :Boolean = (x != 0)

  /** Casts a Long to a Boolean.
   * @param x a value to cast.
   * @return false if x == 0, true otherwise.
   */
  @inline def bool(x: Long) :Boolean = (x != 0)

  /** Casts a Float to a Boolean.
   * @param x a value to cast.
   * @return false if x == 0.0, true otherwise.
   */
  @inline def bool(x: Float) :Boolean = (x != 0)

  /** Casts a Double to a Boolean.
   * @param x a value to cast.
   * @return false if x == 0.0, true otherwise.
   */
  @inline def bool(x: Double) :Boolean = (x != 0)

  /** Casts a Boolean to a Byte.
   * @param x a value to cast.
   * @return 1 if x is true, 0 otherwise.
   */
  @inline def byte(x: Boolean) :Byte = if (x) 1 else 0

  /** Casts a Byte to a Byte. This method is here for completeness.
   * @param x a value to cast.
   * @return x.
   */
  @inline def byte(x: Byte) :Byte = x

  /** Casts a Short to a Byte.
   * @param x a value to cast.
   * @return 8 lower bits of x.
   */
  @inline def byte(x: Short) :Byte = x.asInstanceOf[Byte]

  /** Casts an Int to a Byte.
   * @param x a value to cast.
   * @return 8 lower bits of x.
   */
  @inline def byte(x: Int) :Byte = x.asInstanceOf[Byte]

  /** Casts a Long to a Byte.
   * @param x a value to cast.
   * @return 8 lower bits of x.
   */
  @inline def byte(x: Long) :Byte = x.asInstanceOf[Byte]

  /** Casts a Float to a Byte.
   * @param x a value to cast.
   * @return 8 lower bits of integer part of x,
   *           0 if x <= Int.MinValue,
   *          -1 if x >= Int.MaxValue,
   *           possible loss of precision.
   */
  @inline def byte(x: Float) :Byte = x.asInstanceOf[Byte]

  /** Casts a Double to a Byte.
   * @param x a value to cast.
   * @return 8 lower bits of integer part of x,
   *           0 if x <= Int.MinValue,
   *          -1 if x >= Int.MaxValue,
   *           possible loss of precision.
   */
  @inline def byte(x: Double) :Byte = x.asInstanceOf[Byte]

  /** Casts a Boolean to a Short.
   * @param x a value to cast.
   * @return 1 if x is true, 0 otherwise.
   */
  @inline def short(x: Boolean) :Short = if (x) 1 else 0

  /** Casts a Byte to a Short.
   * @param x a value to cast.
   * @return x as Short keeping the sign.
   */
  @inline def short(x: Byte) :Short = x.asInstanceOf[Short]

  /** Casts a Short to a Short. This method is here for completeness.
   * @param x a value to cast.
   * @return x.
   */
  @inline def short(x: Short) :Short = x

  /** Casts an Int to a Short.
   * @param x a value to cast.
   * @return 16 lower bits of x.
   */
  @inline def short(x: Int) :Short = x.asInstanceOf[Short]

  /** Casts a Long to a Short.
   * @param x a value to cast.
   * @return 16 lower bits of x.
   */
  @inline def short(x: Long) :Short = x.asInstanceOf[Short]

  /** Casts a Float to a Short.
   * @param x a value to cast.
   * @return 16 lower bits of integer part of x,
   *           0 if x <= Int.MinValue,
   *          -1 if x >= Int.MaxValue,
   *           possible loss of precision.
   */
  @inline def short(x: Float) :Short = x.asInstanceOf[Short]

  /** Casts a Double to a Short.
   * @param x a value to cast.
   * @return 16 lower bits of integer part of x,
   *           0 if x <= Int.MinValue,
   *          -1 if x >= Int.MaxValue,
   *           possible loss of precision.
   */
  @inline def short(x: Double) :Short = x.asInstanceOf[Short]

  /** Casts a Boolean to an Int.
   * @param x a value to cast.
   * @return 1 if x is true, 0 otherwise.
   */
  @inline def int(x: Boolean) :Int = if (x) 1 else 0

  /** Casts a Byte to an Int.
   * @param x a value to cast.
   * @return x as Int keeping the sign.
   */
  @inline def int(x: Byte) :Int = x.asInstanceOf[Int]

  /** Casts a Short to an Int.
   * @param x a value to cast.
   * @return x as Int keeping the sign.
   */
  @inline def int(x: Short) :Int = x.asInstanceOf[Int]

  /** Casts an Int to an Int. This method is here for completeness.
   * @param x a value to cast.
   * @return x.
   */
  @inline def int(x: Int) :Int = x

  /** Casts a Long to an Int.
   * @param x a value to cast.
   * @return 32 lower bits of x.
   */
  @inline def int(x: Long) :Int = x.asInstanceOf[Int]

  /** Casts a Float to an Int.
   * @param x a value to cast.
   * @return integer part of x,
   *           Int.MinValue if x <= Int.MinValue,
   *           Int.MaxValue if x >= Int.MaxValue,
   *           possible loss of precision.
   */
  @inline def int(x: Float) :Int = x.asInstanceOf[Int]

  /** Casts a Double to an Int.
   * @param x a value to cast.
   * @return integer part of x,
   *           Int.MinValue if x <= Int.MinValue,
   *           Int.MaxValue if x >= Int.MaxValue,
   *           possible loss of precision.
   */
  @inline def int(x: Double) :Int = x.asInstanceOf[Int]

  /** Casts a Boolean to a Long.
   * @param x a value to cast.
   * @return 1 if x is true, 0 otherwise.
   */
  @inline def long(x: Boolean) :Long = if (x) 1 else 0

  /** Casts a Byte to a Long.
   * @param x a value to cast.
   * @return x as Long keeping the sign.
   */
  @inline def long(x: Byte) :Long = x.asInstanceOf[Long]

  /** Casts a Short to a Long.
   * @param x a value to cast.
   * @return x as Long keeping the sign.
   */
  @inline def long(x: Short) :Long = x.asInstanceOf[Long]

  /** Casts an Int to a Long.
   * @param x a value to cast.
   * @return x as Long keeping the sign.
   */
  @inline def long(x: Int) :Long = x.asInstanceOf[Long]

  /** Casts a Long to a Long. This method is here for completeness.
   * @param x a value to cast.
   * @return x.
   */
  @inline def long(x: Long) :Long = x

  /** Casts a Float to a Long.
   * @param x a value to cast.
   * @return integer part of x,
   *           Long.MinValue if x <= Long.MinValue,
   *           Long.MaxValue if x >= Long.MaxValue,
   *           possible loss of precision.
   */
  @inline def long(x: Float) :Long = x.asInstanceOf[Long]

  /** Casts a Double to a Long.
   * @param x a value to cast.
   * @return integer part of x,
   *           Long.MinValue if x <= Long.MinValue,
   *           Long.MaxValue if x >= Long.MaxValue,
   *           possible loss of precision.
   */
  @inline def long(x: Double) :Long = x.asInstanceOf[Long]

  /** Casts a Boolean to a Float.
   * @param x a value to cast.
   * @return 1.0 if x is true, 0.0 otherwise.
   */
  @inline def float(x: Boolean) :Float = if (x) 1 else 0

  /** Casts a Byte to a Float.
   * @param x a value to cast.
   * @return x as Float.
   */
  @inline def float(x: Byte) :Float = x.asInstanceOf[Float]

  /** Casts a Short to a Float.
   * @param x a value to cast.
   * @return x as Float.
   */
  @inline def float(x: Short) :Float = x.asInstanceOf[Float]

  /** Casts an Int to a Float.
   * @param x a value to cast.
   * @return x as Float, possible loss of precision.
   */
  @inline def float(x: Int) :Float = x.asInstanceOf[Float]

  /** Casts a Long to a Float.
   * @param x a value to cast.
   * @return x as Float, possible loss of precision.
   */
  @inline def float(x: Long) :Float = x.asInstanceOf[Float]

  /** Casts a Float to a Float. This method is here for completeness.
   * @param x a value to cast.
   * @return x.
   */
  @inline def float(x: Float) :Float = x

  /** Casts a Double to a Float.
   * @param x a value to cast.
   * @return x as Float, possible loss of precision.
   */
  @inline def float(x: Double) :Float = x.asInstanceOf[Float]

  /** Casts a Boolean to a Douoble.
   * @param x a value to cast.
   * @return 1.0 if x is true, 0.0 otherwise.
   */
  @inline def double(x: Boolean) :Double = if (x) 1 else 0

  /** Casts a Byte to a Douoble.
   * @param x a value to cast.
   * @return x as Double.
   */
  @inline def double(x: Byte) :Double = x.asInstanceOf[Double]

  /** Casts a Short to a Douoble.
   * @param x a value to cast.
   * @return x as Double.
   */
  @inline def double(x: Short) :Double = x.asInstanceOf[Double]

  /** Casts an Int to a Douoble.
   * @param x a value to cast.
   * @return x as Double.
   */
  @inline def double(x: Int) :Double = x.asInstanceOf[Double]

  /** Casts a Long to a Douoble.
   * @param x a value to cast.
   * @return x as Double, possible loss of precision.
   */
  @inline def double(x: Long) :Double = x.asInstanceOf[Double]

  /** Casts a Float to a Douoble.
   * @param x a value to cast.
   * @return x as Double.
   */
  @inline def double(x: Float) :Double = x.asInstanceOf[Double]

  /** Casts a Double to a Douoble. This method is here for completeness.
   * @param x a value to cast.
   * @return x.
   */
  @inline def double(x: Double) :Double = x
}
