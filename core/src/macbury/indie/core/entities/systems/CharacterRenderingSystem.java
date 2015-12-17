package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.Components;
import macbury.indie.core.entities.components.CharacterAnimationComponent;
import macbury.indie.core.entities.components.PositionComponent;
import macbury.indie.core.entities.components.TileMovementComponent;
import macbury.indie.core.TelegramMessage;
import macbury.indie.core.utils.UnitUtil;

/**
 * This system renders all {@link Entity} with {@link SpriteBatch}. Entity must have:
 * {@link PositionComponent}
 * {@link CharacterAnimationComponent}
 * {@link TileMovementComponent}
 */
public class CharacterRenderingSystem extends IteratingSystem implements Disposable, Telegraph {
  private final Texture tileA;
  private final Texture tileB;
  private MessageDispatcher messages;
  private IndiE game;
  private OrthographicCamera camera;

  private final SpriteBatch spriteBatch;

  public CharacterRenderingSystem(IndiE game, OrthographicCamera camera, MessageDispatcher messages) {
    super(Family.all(PositionComponent.class, CharacterAnimationComponent.class, TileMovementComponent.class).get());
    this.spriteBatch = new SpriteBatch();
    this.camera      = camera;
    this.messages    = messages;
    this.game        = game;
    this.tileA       = game.assets.get("textures:a.png", Texture.class);
    this.tileB       = game.assets.get("textures:b.png", Texture.class);
    messages.addListener(this, TelegramMessage.StartMoving);
    messages.addListener(this, TelegramMessage.FinishMoving);
  }

  @Override
  public void dispose() {
    messages.removeListener(this, TelegramMessage.StartMoving);
    messages.removeListener(this, TelegramMessage.FinishMoving);
    messages = null;
    spriteBatch.dispose();
    game = null;
  }

  @Override
  public void update(float deltaTime) {
    spriteBatch.setProjectionMatrix(camera.combined);

    spriteBatch.begin(); {
      int i = 0;
      for (int x = 0; x < 11; x++) {
        for (int y = 0; y < 11; y++) {
          i++;
          spriteBatch.draw(i%2 == 0 ? tileA : tileB, x * UnitUtil.TILE_SIZE, y * UnitUtil.TILE_SIZE, UnitUtil.TILE_SIZE, UnitUtil.TILE_SIZE);
        }
      }

    } spriteBatch.end();

    spriteBatch.begin(); {
      super.update(deltaTime);
    } spriteBatch.end();
  }

  /**
   * Process each {@link Entity}, update animation and add to {@link SpriteBatch}
   * @param entity
   * @param deltaTime
   */
  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    CharacterAnimationComponent characterAnimationComponent = Components.CharacterAnimation.get(entity);
    PositionComponent positionComponent                     = Components.Position.get(entity);
    TileMovementComponent tileMovementComponent             = Components.TileMovement.get(entity);

    TextureRegion animationFrameRegion = characterAnimationComponent.getAnimationTexture(tileMovementComponent.direction, deltaTime);

    spriteBatch.draw(
      animationFrameRegion,
      positionComponent.x,
      positionComponent.z
    );
  }

  @Override
  public boolean handleMessage(Telegram telegram) {
    Entity entity = (Entity) telegram.extraInfo;
    switch (telegram.message) {
      case TelegramMessage.StartMoving:
        Components.CharacterAnimation.get(entity).startAnimation();
        return true;

      case TelegramMessage.FinishMoving:
        Components.CharacterAnimation.get(entity).stopAnimation();
        return true;
    }

    return false;
  }
}
