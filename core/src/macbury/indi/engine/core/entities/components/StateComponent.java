package macbury.indi.engine.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import macbury.indi.engine.core.entities.shared.EntityState;

/**
 * This component contains {@link EntityState} information for current entity
 */
public class StateComponent implements Component, Pool.Poolable {
  private EntityState currentState;

  public EntityState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(EntityState currentState) {
    this.currentState = currentState;
  }

  @Override
  public void reset() {
    setCurrentState(EntityState.Idle);
  }

  public boolean isMoving() {
    return currentState == EntityState.Moving;
  }

  public boolean isIdle() {
    return currentState == EntityState.Idle;
  }
}
