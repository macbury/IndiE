package macbury.indi.engine.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/**
 * This class contains information about entity position on Screen, and if this entity is visible on current screen;
 */
public class PositionComponent extends Vector3 implements Component, Pool.Poolable {
  private boolean isVisible;
  @Override
  public void reset() {
    setZero();
  }
}
