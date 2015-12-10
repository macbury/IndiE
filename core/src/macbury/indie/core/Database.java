package macbury.indie.core;

import com.badlogic.gdx.utils.Disposable;
import macbury.indie.core.utils.UnitUtil;

/**
 * This class contains all game constants and configuration
 */
public class Database implements Disposable {


  @Override
  public void dispose() {

  }

  /**
   * Return tile size in pixels
   * @return
   */
  public float getTileSize() {
    return UnitUtil.TILE_SIZE;
  }
}
