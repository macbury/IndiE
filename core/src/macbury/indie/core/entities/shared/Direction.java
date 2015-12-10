package macbury.indie.core.entities.shared;

import com.badlogic.gdx.math.Vector3;

/**
 * This enum contains information about Direction that entity can be facing
 */
public enum Direction {
  None(new Vector3()),
  Left(new Vector3(-1, 0, 0)),
  Right(new Vector3(1, 0, 0)),
  Up(new Vector3(0, 0, 1)),
  Down(new Vector3(0, 0, -1));

  public final Vector3 vector;
  Direction(Vector3 direction) {
    this.vector = direction;
  }

}
