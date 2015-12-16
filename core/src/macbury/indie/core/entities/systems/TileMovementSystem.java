package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.Database;
import macbury.indie.core.entities.Components;
import macbury.indie.core.entities.components.PositionComponent;
import macbury.indie.core.entities.components.TileMovementComponent;
import macbury.indie.core.entities.states.MovementState;

/**
 * This system manages moving from {@link TileMovementComponent#startPosition} to {@link TileMovementComponent#finalPosition} and
 * updates {@link PositionComponent}
 */
public class TileMovementSystem extends IteratingSystem implements Disposable {
  private static final String TAG                    = "TileMovementSystem";
  private Database db;

  public TileMovementSystem(IndiE game) {
    super(Family.all(PositionComponent.class, TileMovementComponent.class).get());
    this.db = game.db;
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    PositionComponent positionComponent         = Components.Position.get(entity);
    TileMovementComponent tileMovementComponent = Components.TileMovement.get(entity);

    if (!tileMovementComponent.finishedMoving()) {
      tileMovementComponent.addAlpha(deltaTime);
      positionComponent.set(tileMovementComponent.startPosition).lerp(
        tileMovementComponent.finalPosition,
        tileMovementComponent.alpha
      );
    }
  }

  @Override
  public void dispose() {
    db = null;
  }
}
