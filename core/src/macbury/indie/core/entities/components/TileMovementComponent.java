package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import macbury.indie.core.entities.shared.Direction;

/**
 * This component contains all information needed for moving like {@link Direction} start position, end position speed
 */
public class TileMovementComponent implements Component, Pool.Poolable {
  public final Vector3 startPosition = new Vector3();
  public final Vector3 finalPosition = new Vector3();
  public float alpha;
  public Direction direction;
  public float speed;

  @Override
  public void reset() {
    startPosition.setZero();
    finalPosition.setZero();
    direction = Direction.None;
    alpha = 1.0f;
    speed = 0;
  }

  /**
   * If alpha is 100%
   * @return
   */
  public boolean finishedMoving() {
    return alpha >= 1.0;
  }

  /**
   * Adds alpha by gdx delta time. If alpha is more than 1.0 then clamp
   * @param delta
   */
  public void addAlpha(float delta) {
    this.alpha += delta * speed;
    if (alpha > 1.0f) {
      alpha = 1.0f;
    }
  }

  /**
   * Resets alpha to 0.0f
   */
  public void resetAlpha() {
    alpha = 0.0f;
  }
}
