package macbury.indie.core.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.components.*;
import macbury.indie.core.entities.shared.Direction;

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
  private StateComponent state() {
    StateComponent stateComponent = entityManager.createComponent(StateComponent.class);
    stateComponent.reset();
    return stateComponent;
  }

  /**
   * Creates state component helper
   * @return
   */
  private CharacterAnimationComponent characterAnimation() {
    CharacterAnimationComponent characterAnimationComponent = entityManager.createComponent(CharacterAnimationComponent.class);
    characterAnimationComponent.reset();
    characterAnimationComponent.setTexture(game.assets.get("charsets:debug_player.png", Texture.class));
    return characterAnimationComponent;
  }

  private FollowCameraComponent followCamera() {
    return entityManager.createComponent(FollowCameraComponent.class);
  }

  /**
   * Creates tile movement component
   * @return
   */
  private TileMovementComponent tileMovement(float speed, Direction direction) {
    TileMovementComponent tileMovementComponent = entityManager.createComponent(TileMovementComponent.class);
    tileMovementComponent.reset();
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
    entity.add(tileMovement(2.5f, Direction.Down));
    entity.add(state());
    entity.add(characterAnimation());
    entity.add(followCamera());
    entityManager.addEntity(entity);
    return entity;
  }

  @Override
  public void dispose() {
    game = null;
    entityManager = null;
  }
}
