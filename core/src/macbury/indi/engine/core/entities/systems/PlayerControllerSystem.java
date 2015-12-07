package macbury.indi.engine.core.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import macbury.indi.engine.IndiE;
import macbury.indi.engine.core.entities.components.ControllableComponent;
import macbury.indi.engine.core.input.InputManager;

/**
 * This system captures inputs from keyboard or joystick and apply it to entity with component {@link macbury.indi.engine.core.entities.components.ControllableComponent}
 */
public class PlayerControllerSystem extends EntitySystem implements Disposable, InputManager.Listener {
  private static final String TAG = "PlayerControllerSystem";
  private InputManager input;
  private ImmutableArray<Entity> controllableEntities;
  private ComponentMapper<ControllableComponent> cm = ComponentMapper.getFor(ControllableComponent.class);

  public PlayerControllerSystem(IndiE game) {
    this.input = game.input;
    input.addListener(this);
  }

  @Override
  public void addedToEngine(Engine engine) {
    controllableEntities = engine.getEntitiesFor(Family.all(ControllableComponent.class).get());
  }

  @Override
  public void removedFromEngine(Engine engine) {
    controllableEntities = null;
  }

  /**
   * Every frame checks if any {@link macbury.indi.engine.core.input.InputManager.ActionButton} is pressed. If true, sends {@link com.badlogic.ashley.signals.Signal} to
   * every entity that has component {@link ControllableComponent}
   * @param deltaTime
   */
  @Override
  public void update(float deltaTime) {
    if (input.isActive(InputManager.ActionButton.Left)) {
      Gdx.app.log(TAG, "Action left!");
    }
    // Capture events here
    // use signals to dispose them to all entities that have this listener
  }

  @Override
  public void dispose() {
    input.removeListener(this);
    controllableEntities = null;
    input = null;
  }

  @Override
  public void onActionButtonUp(InputManager manager, InputManager.ActionButton actionButton) {
    Gdx.app.log(TAG, "onActionButtonUp: " + actionButton);
  }

  @Override
  public void onActionButtonDown(InputManager manager, InputManager.ActionButton actionButton) {
    Gdx.app.log(TAG, "onActionButtonDown: " + actionButton);
  }
}
