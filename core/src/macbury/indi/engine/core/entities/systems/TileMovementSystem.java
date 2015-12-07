package macbury.indi.engine.core.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import macbury.indi.engine.core.entities.components.PositionComponent;
import macbury.indi.engine.core.entities.components.TileMovementComponent;

/**
 * This system
 */
public class TileMovementSystem extends IteratingSystem {
  private ComponentMapper<PositionComponent> pc      = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<TileMovementComponent> tmc = ComponentMapper.getFor(TileMovementComponent.class);

  public TileMovementSystem() {
    super(Family.all(PositionComponent.class, TileMovementComponent.class).get());
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    PositionComponent positionComponent         = pc.get(entity);
    TileMovementComponent tileMovementComponent = tmc.get(entity);

    if (tileMovementComponent.alpha == 0) {

    } else {
      tileMovementComponent.alpha += deltaTime * tileMovementComponent.speed;
      positionComponent.set(tileMovementComponent.startPosition).lerp(tileMovementComponent.finalPosition, tileMovementComponent.alpha);
    }
  }
}
