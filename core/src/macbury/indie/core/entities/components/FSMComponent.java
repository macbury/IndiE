package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Pool;

/**
 * This component contains instance of {@link DefaultStateMachine}
 */
public class FSMComponent implements Component, Pool.Poolable, Telegraph {
  private DefaultStateMachine<Entity> stateMachine;
  private MessageDispatcher messages;

  /** Can only be created by PooledEngine */
  private FSMComponent () {
    // private constructor
  }

  /**
   * Creates state machine for entity
   * @param entity
   * @param messages
   * @return
   */
  public FSMComponent init(Entity entity, MessageDispatcher messages) {
    stateMachine = new DefaultStateMachine<Entity>(entity);
    this.messages  = messages;
    return this;
  }

  public void update () {
    stateMachine.update();
  }

  public void changeState (State<Entity> state) {
    stateMachine.changeState(state);
  }

  public StateMachine<Entity> getStateMachine () {
    return stateMachine;
  }

  @Override
  public boolean handleMessage (Telegram msg) {
    return stateMachine.handleMessage(msg);
  }

  @Override
  public void reset() {
    stateMachine = null;
    messages = null;
  }

  public MessageDispatcher getMessages() {
    return messages;
  }
}
