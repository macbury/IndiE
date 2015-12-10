package macbury.indie.core.utils;

import com.badlogic.gdx.math.Vector3;
import macbury.indie.core.entities.components.PositionComponent;

/**
 * This helper helps with conversion of {@link com.badlogic.gdx.math.Vector3} to tile position
 */
public class UnitUtil {
  /**
   * Size in pixels of game tile
   */
  public static final int TILE_SIZE = 24;

  /**
   * Transform to world {@link Vector3} using passed tile position
   * @param tx x position in tiles
   * @param ty y position in tiles
   * @param vectorToChange
   */
  public static void setVector3ToTilePosition(Vector3 vectorToChange, int tx, int ty) {
    vectorToChange.x = tx * TILE_SIZE;
    vectorToChange.z = ty * TILE_SIZE;
  }
}
