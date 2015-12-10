package macbury.indie.core.entities;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.systems.CameraFollowEntitySystem;
import macbury.indie.core.entities.systems.PlayerControllerSystem;
import macbury.indie.core.entities.systems.RenderingSystem;
import macbury.indie.core.entities.systems.TileMovementSystem;

/**
 * Manages creation of entities and entity systems
 */
public class EntityManager extends PooledEngine implements Disposable {
  private OrthographicCamera camera;
  public EntityFactory add;
  private IndiE game;
  private PlayerControllerSystem playerControllerSystem;
  private TileMovementSystem tileMovementSystem;
  private RenderingSystem renderingSystem;
  private CameraFollowEntitySystem followCameraSystem;

  public EntityManager(IndiE game, OrthographicCamera camera) {
    super();
    this.game                = game;
    this.camera              = camera;
    this.add                 = new EntityFactory(game, this);

    setupSystems();
  }

  private void setupSystems() {
    this.tileMovementSystem     = new TileMovementSystem(game);
    addSystem(tileMovementSystem);

    this.playerControllerSystem = new PlayerControllerSystem(game);
    addSystem(playerControllerSystem);

    this.followCameraSystem     = new CameraFollowEntitySystem(camera);
    addSystem(followCameraSystem);

    this.renderingSystem        = new RenderingSystem(game, camera);
    addSystem(renderingSystem);
  }

  @Override
  public void dispose() {
    add.dispose();
    playerControllerSystem.dispose();
    tileMovementSystem.dispose();
    renderingSystem.dispose();
    followCameraSystem.dispose();

    followCameraSystem      = null;
    playerControllerSystem  = null;
    renderingSystem         = null;
    tileMovementSystem      = null;
    camera                  = null;
    game                    = null;
    add                     = null;
  }
}
