package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.components.CharacterAnimationComponent;
import macbury.indie.core.entities.components.PositionComponent;
import macbury.indie.core.entities.components.StateComponent;
import macbury.indie.core.entities.components.TileMovementComponent;

/**
 * Created on 07.12.15.
 */
public class RenderingSystem extends IteratingSystem implements Disposable {
  private ComponentMapper<PositionComponent> pc             = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<CharacterAnimationComponent> chac = ComponentMapper.getFor(CharacterAnimationComponent.class);

  private final SpriteBatch spriteBatch;

  public RenderingSystem(IndiE game) {
    super(Family.all(PositionComponent.class, CharacterAnimationComponent.class).get());
    this.spriteBatch = new SpriteBatch();
  }

  @Override
  public void dispose() {
    spriteBatch.dispose();
  }

  @Override
  public void update(float deltaTime) {
    spriteBatch.begin(); {
      super.update(deltaTime);
    } spriteBatch.end();
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    CharacterAnimationComponent characterAnimationComponent = chac.get(entity);
    PositionComponent positionComponent                     = pc.get(entity);
    spriteBatch.draw(characterAnimationComponent.getTexture(), positionComponent.x, positionComponent.z);
  }
}
