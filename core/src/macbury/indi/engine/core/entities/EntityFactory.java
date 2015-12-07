package macbury.indi.engine.core.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import macbury.indi.engine.IndiE;
import macbury.indi.engine.core.entities.components.ControllableComponent;
import macbury.indi.engine.core.entities.components.PositionComponent;
import macbury.indi.engine.core.entities.components.StateComponent;
import macbury.indi.engine.core.entities.components.TileMovementComponent;
import macbury.indi.engine.core.entities.shared.Direction;
import macbury.indi.engine.core.entities.shared.EntityState;

/**
 * This is helper class for creating common game entities
 */
public class EntityFactory implements Disposable {
  private EntityManager entityManager;
  private IndiE game;

  public EntityFactory(IndiE game, EntityManager entityManager) {
    this.game          = game;
    this.entityManager = entityManager;
  }

  /**
   * Creates entity helper
   * @return
   */
  private Entity ce() {
    return entityManager.createEntity();
  }

  /**
   * Creates controllable component helper
   * @return
   */
  private ControllableComponent controllable() {
    return entityManager.createComponent(ControllableComponent.class);
  }

  /**
   * Creates position component helper
   * @return
   */
  private PositionComponent position(Vector3 position) {
    PositionComponent positionComponent = entityManager.createComponent(PositionComponent.class);
    positionComponent.set(position);
    return positionComponent;
  }

  /**
   * Creates state component helper
   * @return
   */
  private StateComponent state(EntityState entityState) {
    StateComponent stateComponent = entityManager.createComponent(StateComponent.class);
    stateComponent.setCurrentState(entityState);
    return stateComponent;
  }

  /**
   * Creates tile movement component
   * @return
   */
  private TileMovementComponent tileMovement(float speed, Direction direction) {
    TileMovementComponent tileMovementComponent = entityManager.createComponent(TileMovementComponent.class);
    tileMovementComponent.speed                 = speed;
    tileMovementComponent.direction             = direction;
    return tileMovementComponent;
  }

  /**
   * Creates player enitity.
   * @return
   */
  public Entity player(Vector3 spawnPosition) {
    Entity entity = ce();
    entity.add(controllable());
    entity.add(position(spawnPosition));
    entity.add(tileMovement(2, Direction.Down));
    entity.add(state(EntityState.Idle));
    entityManager.addEntity(entity);
    return entity;
  }

  @Override
  public void dispose() {
    game = null;
    entityManager = null;
  }
}
