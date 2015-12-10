package macbury.indie.core.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.core.entities.components.FollowCameraComponent;
import macbury.indie.core.entities.components.PositionComponent;

/**
 * This system sets {@link com.badlogic.gdx.graphics.Camera#position} to
 * equal {@link macbury.indie.core.entities.components.PositionComponent}
 */
public class CameraFollowEntitySystem extends IteratingSystem implements Disposable {
  private Camera camera;
  private ComponentMapper<PositionComponent> pc       = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<FollowCameraComponent> fc   = ComponentMapper.getFor(FollowCameraComponent.class);

  public CameraFollowEntitySystem(Camera camera) {
    super(Family.all(PositionComponent.class, FollowCameraComponent.class).get());
    this.camera = camera;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    camera.update();
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    PositionComponent positionComponent = pc.get(entity);
    camera.position.set(positionComponent.x, positionComponent.z, 0);
  }

  @Override
  public void dispose() {
    camera = null;
  }
}
