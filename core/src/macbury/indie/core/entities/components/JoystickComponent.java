package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import macbury.indie.core.input.InputManager;

/**
 * This component contains all information about pressed {@link macbury.indie.core.input.ActionButton},
 * how strong it is pressed etc
 */
public class JoystickComponent implements Component, Pool.Poolable {
  public InputManager input;

  @Override
  public void reset() {
    input = null;
  }
}
