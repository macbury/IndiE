package macbury.indi.engine.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * This class maps {@link ActionButton} to keycodes and triggers {@link macbury.indi.engine.core.input.InputManager.Listener} if any action button is pressed
 */
public class InputManager implements InputProcessor, Disposable {
  public enum ActionButton {
    Left, Right, Up, Down, CancelOrQuit, DoAction
  }
  private ObjectMap<Integer, ActionButton> keyBoardMappings;
  private ObjectMap<ActionButton, Boolean> buttonActive;
  private Array<Listener> listeners;

  public InputManager() {
    listeners        = new Array<Listener>();
    keyBoardMappings = new ObjectMap<Integer, ActionButton>();
    buttonActive     = new ObjectMap<ActionButton, Boolean>();

    for (ActionButton actionButton : ActionButton.values()) {
      buttonActive.put(actionButton, false);
    }

    configureKeyboard();
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
   * Adds listener for {@link macbury.indi.engine.core.input.InputManager.ActionButton}
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
   * Returns true if {@link macbury.indi.engine.core.input.InputManager.ActionButton} is pressed
   * @param actionButton
   * @return
   */
  public boolean isActive(ActionButton actionButton) {
    return buttonActive.get(actionButton);
  }

  /**
   * Sets {@link macbury.indi.engine.core.input.InputManager.ActionButton} to be active only if previously is not.
   * If is active triggers {@link macbury.indi.engine.core.input.InputManager.Listener#onActionButtonUp(InputManager, ActionButton)}
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
   * Sets {@link macbury.indi.engine.core.input.InputManager.ActionButton} not to be active only if previously is .
   * If is active triggers {@link macbury.indi.engine.core.input.InputManager.Listener#onActionButtonDown(InputManager, ActionButton)} (InputManager, ActionButton)}
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
    Gdx.app.debug("DEBUG PS3", "Pressed: " + keycode);
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

  public interface Listener {
    public void onActionButtonUp(InputManager manager, ActionButton actionButton);
    public void onActionButtonDown(InputManager manager, ActionButton actionButton);
  }
}
