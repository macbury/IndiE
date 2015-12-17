package macbury.indie.core.entities.states;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import macbury.indie.core.TelegramMessage;
import macbury.indie.core.entities.Components;
import macbury.indie.core.entities.components.*;
import macbury.indie.core.input.ActionButton;

/**
 * This enum have all logic for moving and attacking player
 */
public enum PlayerState implements State<Entity> {
  Idle {
    @Override
    public void update(Entity entity) {
      FSMComponent      fsm              = Components.FSM.get(entity);
      JoystickComponent joystick         = Components.Joystick.get(entity);

      if (joystick.input.isActive(ActionButton.DoAction)) {
        fsm.changeState(PlayerState.DoAction);
      } else if (joystick.input.isMovementKeyStronglyPressed()) {
        fsm.changeState(PlayerState.Moving);
      } else if (joystick.input.isMovementKeySlightlyPressed()) {
        TileMovementComponent tileMovement = Components.TileMovement.get(entity);
        tileMovement.direction             = joystick.input.getDirectionAction().toDirection();
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
  },

  DoAction {
    @Override
    public void update(Entity entity) {
      FSMComponent fsm              = Components.FSM.get(entity);
      JoystickComponent joystick    = Components.Joystick.get(entity);

      if (!joystick.input.isAnyMovementKeyActive()) {
        fsm.changeState(PlayerState.Idle);
      }
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
