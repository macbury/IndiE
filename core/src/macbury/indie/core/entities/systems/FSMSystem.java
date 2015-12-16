package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.core.entities.Components;
import macbury.indie.core.entities.components.FSMComponent;

/**
 * Update state machine in {@link FSMComponent}
 */
public class FSMSystem extends IteratingSystem implements Disposable {
  public FSMSystem() {
    super(Family.all(FSMComponent.class).get());
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    Components.FSM.get(entity).update();
  }

  @Override
  public void dispose() {

  }
}
