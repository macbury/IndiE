package macbury.indi.engine.core.input.mappings;

import com.badlogic.gdx.Gdx;

/**
 * This class defines mapping for keyboard key
 */
public class KeyMapping implements BaseMapping {
  private int keyCode;

  public KeyMapping(int keyCode) {
    this.keyCode = keyCode;
  }

  @Override
  public boolean isActive() {
    return Gdx.input.isKeyPressed(keyCode);
  }
}
