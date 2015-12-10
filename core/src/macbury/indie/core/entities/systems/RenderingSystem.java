package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created on 07.12.15.
 */
public class RenderingSystem extends IteratingSystem implements Disposable {
  public RenderingSystem(Family family) {
    super(family);
  }

  @Override
  public void dispose() {

  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {

  }
}
