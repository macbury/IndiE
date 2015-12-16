package macbury.indie.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import macbury.indie.core.input.mappings.PS3DualShock;

/**
 * This class maps {@link ActionButton} to keycodes and triggers {@link macbury.indie.core.input.InputManager.Listener} if
 * any action button is pressed
 */
public class InputManager implements InputProcessor, Disposable, ControllerListener {
  private static final String TAG = "InputManager";

  private ObjectMap<Integer, ActionButton> keyBoardMappings;
  private ObjectMap<Integer, ActionButton> controllerMappings;
  private ObjectMap<ActionButton, Boolean> buttonActive;
  private Array<Listener> listeners;

  public InputManager() {
    listeners          = new Array<Listener>();
    controllerMappings = new ObjectMap<Integer, ActionButton>();
    keyBoardMappings   = new ObjectMap<Integer, ActionButton>();
    buttonActive       = new ObjectMap<ActionButton, Boolean>();

    for (ActionButton actionButton : ActionButton.values()) {
      buttonActive.put(actionButton, false);
    }

    configureKeyboard();
    configureController();
    Controllers.addListener(this);
  }

  private void configureController() {
    controllerMappings.put(PS3DualShock.Up.keyCode, ActionButton.Up);
    controllerMappings.put(PS3DualShock.Left.keyCode, ActionButton.Left);
    controllerMappings.put(PS3DualShock.Right.keyCode, ActionButton.Right);
    controllerMappings.put(PS3DualShock.Down.keyCode, ActionButton.Down);

    controllerMappings.put(PS3DualShock.X.keyCode, ActionButton.DoAction);
    controllerMappings.put(PS3DualShock.Square.keyCode, ActionButton.CancelOrQuit);
  }

  /**
   * Configure basic mapping for keyboard
   */
  private void configureKeyboard() {
    keyBoardMappings.put(Input.Keys.LEFT, ActionButton.Left);
    keyBoardMappings.put(Input.Keys.RIGHT, ActionButton.Right);
    keyBoardMappings.put(Input.Keys.UP, ActionButton.Up);
    keyBoardMappings.put(Input.Keys.DOWN, ActionButton.Down);

    keyBoardMappings.put(Input.Keys.X, ActionButton.DoAction);
    keyBoardMappings.put(Input.Keys.Z, ActionButton.CancelOrQuit);
  }

  /**
   * Adds listener for {@link ActionButton}
   * @param listener
   */
  public void addListener(Listener listener) {
    listeners.add(listener);
  }

  /**
   * Removes listener
   * @param listener
   */
  public void removeListener(Listener listener) {
    listeners.removeValue(listener, true);
  }

  /**
   * Returns true if {@link ActionButton} is pressed
   * @param actionButton
   * @return
   */
  public boolean isActive(ActionButton actionButton) {
    return buttonActive.get(actionButton);
  }

  /**
   * Is any movement key active(pressed)
   * @return
   */
  public boolean isAnyMovementKeyActive() {
    return isActive(ActionButton.Left) || isActive(ActionButton.Right) || isActive(ActionButton.Up) || isActive(ActionButton.Down);
  }

  /**
   * Return current pressed {@link ActionButton}
   * @return
   */
  public ActionButton getDirectionAction() {
    if (isActive(ActionButton.Left)) {
      return ActionButton.Left;
    } else if (isActive(ActionButton.Right)) {
      return ActionButton.Right;
    } else if (isActive(ActionButton.Up)) {
      return ActionButton.Up;
    } else if (isActive(ActionButton.Down)) {
      return ActionButton.Down;
    }

    return null;
  }

  /**
   * Sets {@link ActionButton} to be active only if previously is not.
   * If is active triggers {@link macbury.indie.core.input.InputManager.Listener#onActionButtonUp(InputManager, ActionButton)}
   * @param triggeredButton button to be triggered
   * @return
   */
  public boolean triggerActionButtonUp(ActionButton triggeredButton) {
    if (isActive(triggeredButton)) {
      buttonActive.put(triggeredButton, false);
      for (int i = 0; i < listeners.size; i++) {
        listeners.get(i).onActionButtonUp(this, triggeredButton);
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sets {@link ActionButton} not to be active only if previously is .
   * If is active triggers {@link macbury.indie.core.input.InputManager.Listener#onActionButtonDown(InputManager, ActionButton)} (InputManager, ActionButton)}
   * @param triggeredButton button to be triggered
   * @return
   */
  public boolean triggerActionButtonDown(ActionButton triggeredButton) {
    if (!isActive(triggeredButton)) {
      buttonActive.put(triggeredButton, true);
      for (int i = 0; i < listeners.size; i++) {
        listeners.get(i).onActionButtonDown(this, triggeredButton);
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void dispose() {
    keyBoardMappings.clear();
    buttonActive.clear();
    buttonActive = null;
    keyBoardMappings = null;
  }

  @Override
  public boolean keyDown(int keycode) {
    ActionButton triggeredButton = keyBoardMappings.get(keycode);
    if (triggeredButton != null) {
      return triggerActionButtonDown(triggeredButton);
    } else {
      return false;
    }
  }

  @Override
  public boolean keyUp(int keycode) {
    ActionButton triggeredButton = keyBoardMappings.get(keycode);
    if (triggeredButton != null) {
      return triggerActionButtonUp(triggeredButton);
    } else {
      return false;
    }
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }


  @Override
  public void connected(Controller controller) {

  }

  @Override
  public void disconnected(Controller controller) {

  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    ActionButton triggeredButton = controllerMappings.get(buttonCode);
    if (triggeredButton != null) {
      return triggerActionButtonDown(triggeredButton);
    } else {
      return false;
    }
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonCode) {
    ActionButton triggeredButton = controllerMappings.get(buttonCode);
    if (triggeredButton != null) {
      return triggerActionButtonUp(triggeredButton);
    } else {
      return false;
    }
  }

  @Override
  public boolean axisMoved(Controller controller, int axisCode, float value) {
    return false;
  }

  @Override
  public boolean povMoved(Controller controller, int povCode, PovDirection value) {
    return false;
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
    return false;
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
    return false;
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
    return false;
  }

  public interface Listener {
    public void onActionButtonUp(InputManager manager, ActionButton actionButton);
    public void onActionButtonDown(InputManager manager, ActionButton actionButton);
  }
}
