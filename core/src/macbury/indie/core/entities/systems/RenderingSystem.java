package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.components.CharacterAnimationComponent;
import macbury.indie.core.entities.components.PositionComponent;
import macbury.indie.core.entities.components.StateComponent;
import macbury.indie.core.entities.components.TileMovementComponent;
import macbury.indie.core.entities.states.MovementState;
import macbury.indie.core.utils.UnitUtil;

/**
 * Created on 07.12.15.
 */
public class RenderingSystem extends IteratingSystem implements Disposable {
  private final Texture tileA;
  private final Texture tileB;
  private IndiE game;
  private OrthographicCamera camera;
  private ComponentMapper<PositionComponent> pc             = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<CharacterAnimationComponent> chac = ComponentMapper.getFor(CharacterAnimationComponent.class);
  private ComponentMapper<TileMovementComponent> tmc        = ComponentMapper.getFor(TileMovementComponent.class);

  private ComponentMapper<StateComponent> sc                = ComponentMapper.getFor(StateComponent.class);
  private final SpriteBatch spriteBatch;

  public RenderingSystem(IndiE game, OrthographicCamera camera) {
    super(Family.all(StateComponent.class, PositionComponent.class, CharacterAnimationComponent.class, TileMovementComponent.class, StateComponent.class).get());
    this.spriteBatch = new SpriteBatch();
    this.camera      = camera;
    this.game        = game;
    this.tileA         = game.assets.get("textures:a.png", Texture.class);
    this.tileB         = game.assets.get("textures:b.png", Texture.class);

  }

  @Override
  public void dispose() {
    spriteBatch.dispose();
    game = null;
  }

  @Override
  public void update(float deltaTime) {
    spriteBatch.setProjectionMatrix(camera.combined);

    spriteBatch.begin(); {
      int i = 0;
      for (int x = 0; x < 43; x++) {
        for (int y = 0; y < 33; y++) {
          i++;
          spriteBatch.draw(i%2 == 0 ? tileA : tileB, x * game.db.getTileSize(), y * game.db.getTileSize());
        }
      }

    } spriteBatch.end();

    spriteBatch.begin(); {
      super.update(deltaTime);
    } spriteBatch.end();
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    CharacterAnimationComponent characterAnimationComponent = chac.get(entity);
    PositionComponent positionComponent                     = pc.get(entity);
    TileMovementComponent tileMovementComponent             = tmc.get(entity);
    StateComponent stateComponent                           = sc.get(entity);

    if (tileMovementComponent.finishedMoving()) {
      characterAnimationComponent.resetAnimations();
    }

    TextureRegion animationFrameRegion = characterAnimationComponent.getAnimationTexture(tileMovementComponent.direction, deltaTime);

    float offsetX = (UnitUtil.TILE_SIZE - animationFrameRegion.getRegionWidth()) / 2.0f;

    spriteBatch.draw(
      animationFrameRegion,
      positionComponent.x + offsetX,
      positionComponent.z
    );
  }
}
