package macbury.indie.core.entities.states;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import macbury.indie.core.entities.Components;
import macbury.indie.core.entities.components.*;

/**
 * Created on 16.12.15.
 */
public enum PlayerState implements State<Entity> {
  Idle {
    private static final String TAG = "PlayerStateIdle";

    @Override
    public void update(Entity entity) {
      FSMComponent      fsm              = Components.FSM.get(entity);
      JoystickComponent joystick         = Components.Joystick.get(entity);

      if (joystick.input.isAnyMovementKeyActive()) {
        fsm.changeState(PlayerState.Moving);
      }
    }
  },

  Moving {
    @Override
    public void enter(Entity entity) {
      Components.FSM.get(entity).getMessages().dispatchMessage(null, null, TelegramMessage.StartMoving, entity);
    }

    @Override
    public void update(Entity entity) {
      FSMComponent      fsm              = Components.FSM.get(entity);
      JoystickComponent joystick         = Components.Joystick.get(entity);
      TileMovementComponent tileMovement = Components.TileMovement.get(entity);
      PositionComponent position         = Components.Position.get(entity);

      if (tileMovement.finishedMoving()) {
        if (joystick.input.isAnyMovementKeyActive()) {
          tileMovement.moveInDirection(position, joystick.input.getDirectionAction().toDirection());
        } else {
          fsm.changeState(PlayerState.Idle);
        }
      }
    }

    @Override
    public void exit(Entity entity) {
      Components.FSM.get(entity).getMessages().dispatchMessage(null, null, TelegramMessage.FinishMoving, entity);
    }
  };



  @Override
  public void enter(Entity entity) {
  }

  @Override
  public void update(Entity entity) {

  }

  @Override
  public void exit(Entity entity) {

  }

  @Override
  public boolean onMessage(Entity entity, Telegram telegram) {
    return false;
  }
}
