package macbury.indi.engine.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import macbury.indi.engine.core.entities.shared.Direction;

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
    alpha = 0;
    speed = 0;
  }

}
