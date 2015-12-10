package macbury.indie.core.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
  private PositionComponent position() {
    return entityManager.createComponent(PositionComponent.class);
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
  private CharacterAnimationComponent characterAnimation(String atlasName, String charsetName) {
    CharacterAnimationComponent characterAnimationComponent = entityManager.createComponent(CharacterAnimationComponent.class);
    characterAnimationComponent.reset();

    TextureAtlas atlas = game.assets.get("charsets:"+atlasName+".atlas", TextureAtlas.class);
    characterAnimationComponent.setAtlas(atlas, charsetName);
    //Animation animation = new Animation(10, atlas.findRegion(charsetName + "_up", 0));
    //characterAnimationComponent.setTexture(game.assets.get("charsets:debug_player.png", Texture.class));
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
   * Creates player enitity and adds it to world
   * @param tx x tile position
   * @param ty y tile position
   * @return created entity
   */
  public Entity player(int tx, int ty) {
    TileMovementComponent tileMovementComponent = tileMovement(2.8f, Direction.Down);
    PositionComponent     positionComponent     = position();

    positionComponent.setTilePosition(tx, ty);
    tileMovementComponent.startPosition.set(positionComponent);

    Entity entity = ce();
    entity.add(controllable());
    entity.add(positionComponent);
    entity.add(tileMovementComponent);
    entity.add(state());
    entity.add(characterAnimation("npc", "npc"));
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
