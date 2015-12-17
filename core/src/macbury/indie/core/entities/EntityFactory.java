package macbury.indie.core.entities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import macbury.indie.IndiE;
import macbury.indie.core.entities.components.*;
import macbury.indie.core.entities.shared.Direction;
import macbury.indie.core.entities.states.MonsterState;
import macbury.indie.core.entities.states.PlayerState;

/**
 * This is helper class for creating common game entities
 */
public class EntityFactory implements Disposable {
  private Builder builder;
  private EntityManager entityManager;
  private IndiE game;

  public EntityFactory(IndiE game, EntityManager entityManager, MessageDispatcher messages) {
    this.game          = game;
    this.entityManager = entityManager;
    this.builder       = new Builder(entityManager, game, messages);
  }

  /**
   * Creates player enitity and adds it to world
   * @param tx x tile position
   * @param ty y tile position
   * @return created entity
   */
  public Entity player(int tx, int ty) {
    return builder.begin()
      .joystick()
      .position(tx, ty)
      .tileMovement(TileMovementComponent.Speed.Normal, Direction.Down)
      .stateMachine(PlayerState.Idle)
      .characterAnimation("charset", "dman")
      .followCamera()
      .end();
  }

  @Override
  public void dispose() {
    builder.dispose();
    builder = null;
    game = null;
    entityManager = null;
  }

  public Entity monster(int tx, int ty) {
    return builder.begin()
      .position(tx, ty)
      .tileMovement(TileMovementComponent.Speed.Slow, Direction.Down)
      .stateMachine(MonsterState.Idle)
      .characterAnimation("charset", "slime")
      .end();
  }

  /**
   * Assemble new entity
   */
  private class Builder implements Disposable {
    private MessageDispatcher messages;
    private IndiE game;
    private EntityManager manager;
    private Entity entity;

    public Builder(EntityManager manager, IndiE game, MessageDispatcher messages) {
      this.manager = manager;
      this.game    = game;
      this.messages = messages;
    }

    /**
     * Create base entity
     * @return
     */
    public Builder begin() {
      this.entity = manager.createEntity();
      return this;
    }

    /**
     * Adds {@link JoystickComponent} with reference to input
     * @return
     */
    private Builder joystick() {
      JoystickComponent component = entityManager.createComponent(JoystickComponent.class);
      component.reset();
      component.input = game.input;
      entity.add(component);
      return this;
    }

    /**
     * Creates state component helper
     * @return
     */
    private Builder characterAnimation(String atlasName, String charsetName) {
      CharacterAnimationComponent characterAnimationComponent = entityManager.createComponent(CharacterAnimationComponent.class);
      characterAnimationComponent.reset();

      TextureAtlas atlas = game.assets.get("charsets:" + atlasName + ".atlas", TextureAtlas.class);
      characterAnimationComponent.setAtlas(atlas, charsetName);

      entity.add(characterAnimationComponent);
      return this;
    }


    /**
     * Creates tile movement component
     * @return
     */
    private Builder tileMovement(TileMovementComponent.Speed speed, Direction direction) {
      TileMovementComponent tileMovementComponent = entityManager.createComponent(TileMovementComponent.class);
      tileMovementComponent.reset();
      tileMovementComponent.speed                 = speed;
      tileMovementComponent.direction             = direction;

      PositionComponent pc = Components.Position.get(entity);
      if (pc == null) {
        throw new GdxRuntimeException("First add PositionComponent!");
      } else {
        tileMovementComponent.startPosition.set(pc);
      }
      entity.add(tileMovementComponent);
      return this;
    }

    /**
     * Creates position component helper
     * @return
     */
    private Builder position(int tx, int ty) {
      PositionComponent positionComponent = entityManager.createComponent(PositionComponent.class);
      positionComponent.setTilePosition(tx, ty);
      entity.add(positionComponent);
      return this;
    }

    /**
     * Creates state machine component
     * @return
     */
    private Builder stateMachine(State<Entity> initialState) {
      FSMComponent component = entityManager.createComponent(FSMComponent.class);
      component.reset();
      component.init(entity, messages);
      component.changeState(initialState);
      entity.add(component);
      return this;
    }

    private Builder followCamera() {
      entity.add(entityManager.createComponent(FollowCameraComponent.class));
      return this;
    }

    public Entity end() {
      Entity entityToReturn = entity;
      manager.addEntity(entity);
      entity = null;
      return entityToReturn;
    }

    @Override
    public void dispose() {
      this.manager = null;
      this.game    = null;
      messages = null;
      entity = null;
    }
  }
}
