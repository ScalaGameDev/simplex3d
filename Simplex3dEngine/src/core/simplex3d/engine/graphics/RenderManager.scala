/*
 * Simplex3dEngine - Core Module
 * Copyright (C) 2011, Aleksey Nikiforov
 *
 * This file is part of Simplex3dEngine.
 *
 * Simplex3dEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Simplex3dEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package simplex3d.engine
package graphics

import simplex3d.engine.util._
import simplex3d.engine.scene._


trait RenderManager {
  val driver: String
  
  def init(graphicsCapabilities: GraphicsCapabilities, settings: AdvancedSettings)
  def renderContext: RenderContext
  
  def render(time: TimeStamp, camera: AbstractCamera, renderArray: SortBuffer[AbstractMesh]) :Unit
  def sortRenderArray(pass: Pass, renderArray: SortBuffer[AbstractMesh]) :Unit
  
  
  var showNormals = false
  
  //XXX this belongs on scenegraph or pass manager
  var showBoundingVolumes = false
}
