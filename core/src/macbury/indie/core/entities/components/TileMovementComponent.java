package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import macbury.indie.core.entities.shared.Direction;
import macbury.indie.core.entities.states.MovementState;

/**
 * This component contains all information needed for moving like {@link Direction} start position, end position speed
 */
public class TileMovementComponent implements Component, Pool.Poolable {
  private static final String TAG = "TileMovementComponent";
  private static final float MAX_ALPHA_VALUE = 1.0f;
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
    alpha     = 1.0f;
    speed     = 0;
  }

  /**
   * If alpha or movment is 100% done
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
    if (alpha > MAX_ALPHA_VALUE) {
      alpha = MAX_ALPHA_VALUE;
    }
  }

  /**
   * Resets alpha/movement to 0.0f
   */
  public void resetAlpha() {
    alpha = 0.0f;
  }

  /**
   * Prepares tile to move in next direction
   * @param startVector - from where should start move
   * @param inDirection - in what direction should move
   * @param tileSize - tile size or distance to move
   */
  public void moveInDirection(Vector3 startVector, Direction inDirection, float tileSize) {
    if (finishedMoving()) {
      resetAlpha();
      this.direction = inDirection;
      startPosition.set(startVector);
      finalPosition.setZero().set(direction.vector).scl(tileSize).add(startVector);

      //Gdx.app.debug(TAG, "Going to: " + finalPosition.toString() + " from " + startPosition.toString());
    } else {
      throw new RuntimeException("This entity did not finish moving!");
    }
  }
}
