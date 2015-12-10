package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.components.ControllableComponent;
import macbury.indie.core.entities.components.PositionComponent;
import macbury.indie.core.entities.components.StateComponent;
import macbury.indie.core.entities.components.TileMovementComponent;
import macbury.indie.core.entities.shared.Direction;
import macbury.indie.core.entities.states.MovementState;
import macbury.indie.core.input.InputManager;

/**
 * This system captures inputs from keyboard or joystick and apply it to entity with component {@link macbury.indie.core.entities.components.ControllableComponent}
 */
public class PlayerControllerSystem extends EntitySystem implements Disposable, InputManager.Listener {
  private static final String TAG = "PlayerControllerSystem";
  private IndiE game;
  private InputManager input;
  private ImmutableArray<Entity> controllableEntities;
  private ComponentMapper<PositionComponent>    pc   = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<ControllableComponent> cm  = ComponentMapper.getFor(ControllableComponent.class);
  private ComponentMapper<TileMovementComponent> tmc = ComponentMapper.getFor(TileMovementComponent.class);
  private ComponentMapper<StateComponent> sc         = ComponentMapper.getFor(StateComponent.class);

  public PlayerControllerSystem(IndiE game) {
    this.input = game.input;
    this.game  = game;
    input.addListener(this);
  }

  @Override
  public void addedToEngine(Engine engine) {
    controllableEntities = engine.getEntitiesFor(Family.all(ControllableComponent.class, StateComponent.class, TileMovementComponent.class, PositionComponent.class).get());
  }

  @Override
  public void removedFromEngine(Engine engine) {
    controllableEntities = null;
  }

  /**
   * Process controllable entities and trigger {@link PlayerControllerSystem#handleEntityMovementByPlayer(Entity)}
   * @param deltaTime
   */
  @Override
  public void update(float deltaTime) {
    for (int i = 0; i < controllableEntities.size(); i++) {
      handleEntityMovementByPlayer(controllableEntities.get(i));
    }

  }

  /**
   * If movment key is pressed make {@link TileMovementComponent} move and map {@link macbury.indie.core.input.InputManager.ActionButton}
   * to {@link Direction}, otherwise if state is {@link MovementState#FinishMoving} set state to {@link MovementState#Idle}
   * @param entity
   */
  private void handleEntityMovementByPlayer(Entity entity) {
    PositionComponent positionComponent         = pc.get(entity);
    TileMovementComponent tileMovementComponent = tmc.get(entity);
    if (input.isAnyMovementKeyActive()) {
      if (tileMovementComponent.finishedMoving()) {
        tileMovementComponent.moveInDirection(positionComponent, input.getDirectionAction().toDirection(), game.db.getTileSize());
      }
    }
  }

  @Override
  public void dispose() {
    input.removeListener(this);
    game = null;
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
