package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import macbury.indie.core.utils.UnitUtil;

/**
 * This class contains information about entity position on Screen, and if this entity is visible on current screen;
 */
public class PositionComponent extends Vector3 implements Component, Pool.Poolable {
  private boolean isVisible;

  /**
   * Transform passed tile position into world position
   * @param tx x position in tiles
   * @param ty y position in tiles
   * @return
   */
  public PositionComponent setTilePosition(int tx, int ty) {
    UnitUtil.setVector3ToTilePosition(this, tx, ty);
    return this;
  }

  @Override
  public void reset() {
    setZero();
  }
}
