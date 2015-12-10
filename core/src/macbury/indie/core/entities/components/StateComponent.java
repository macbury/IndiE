package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Pool;
import macbury.indie.core.entities.states.MovementState;
import macbury.indie.core.entities.states.ActionState;

/**
 * This component contains {@link ActionState} and {@link MovementState} information for current entity
 */
public class StateComponent implements Component, Pool.Poolable {
  private MovementState movementState;
  private ActionState   actionState;


  @Override
  public void reset() {
    setActionState(ActionState.Idle);
    setMovementState(MovementState.Idle);
  }

  public MovementState getMovementState() {
    return movementState;
  }

  public void setMovementState(MovementState movementState) {
    //Gdx.app.log("State", movementState.toString());
    this.movementState = movementState;
  }

  public ActionState getActionState() {
    return actionState;
  }

  public void setActionState(ActionState actionState) {
    this.actionState = actionState;
  }

  /**
   * If state is {@link MovementState#Idle} or {@link MovementState#FinishMoving}
   * @return
   */
  public boolean canMove() {
    return movementState == MovementState.Idle || movementState == MovementState.FinishMoving;
  }
}
