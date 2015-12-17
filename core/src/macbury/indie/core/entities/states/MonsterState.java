package macbury.indie.core.entities.states;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import macbury.indie.core.entities.Components;
import macbury.indie.core.entities.components.FSMComponent;
import macbury.indie.core.entities.components.JoystickComponent;
import macbury.indie.core.entities.components.PositionComponent;
import macbury.indie.core.entities.components.TileMovementComponent;

/**
 * Created on 17.12.15.
 */
public enum MonsterState implements State<Entity> {
  Idle {
    @Override
    public void update(Entity entity) {
      Components.FSM.get(entity).changeState(MonsterState.Moving);
    }
  },

  Moving {
    @Override
    public void enter(Entity entity) {
      Components.FSM.get(entity).getMessages().dispatchMessage(null, null, TelegramMessage.StartMoving, entity);
    }

    @Override
    public void update(Entity entity) {

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
