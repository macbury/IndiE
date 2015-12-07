package macbury.indi.engine.core.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import macbury.indi.engine.IndiE;
import macbury.indi.engine.core.Database;
import macbury.indi.engine.core.entities.components.PositionComponent;
import macbury.indi.engine.core.entities.components.StateComponent;
import macbury.indi.engine.core.entities.components.TileMovementComponent;

/**
 * This system manages moving from {@link TileMovementComponent#startPosition} to {@link TileMovementComponent#finalPosition} and
 * updates {@link PositionComponent} if {@link StateComponent} is moving
 */
public class TileMovementSystem extends IteratingSystem implements Disposable {
  private static final String TAG                    = "TileMovementSystem";
  private Database db;
  private ComponentMapper<PositionComponent> pc      = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<TileMovementComponent> tmc = ComponentMapper.getFor(TileMovementComponent.class);
  private ComponentMapper<StateComponent> sc         = ComponentMapper.getFor(StateComponent.class);
  private Vector3 tempVector                         = new Vector3();

  public TileMovementSystem(IndiE game) {
    super(Family.all(PositionComponent.class, TileMovementComponent.class, StateComponent.class).get());
    this.db = game.db;
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    PositionComponent positionComponent         = pc.get(entity);
    TileMovementComponent tileMovementComponent = tmc.get(entity);
    StateComponent        stateComponent        = sc.get(entity);

    tileMovementComponent.addAlpha(deltaTime);

    if (tileMovementComponent.finishedMoving()) {
      if (stateComponent.isMoving()) {
        tileMovementComponent.resetAlpha();
        tempVector.setZero().set(tileMovementComponent.direction.vector).scl(db.getTileSize()).add(positionComponent);

        tileMovementComponent.startPosition.set(positionComponent);
        tileMovementComponent.finalPosition.set(tempVector);
        Gdx.app.debug(TAG, "Going to: " + tileMovementComponent.finalPosition.toString() + " from " + tileMovementComponent.startPosition.toString());
      }
    } else {
      positionComponent.set(tileMovementComponent.startPosition).lerp(tileMovementComponent.finalPosition, tileMovementComponent.alpha);
    }
  }

  @Override
  public void dispose() {
    db = null;
    tempVector = null;
  }
}
