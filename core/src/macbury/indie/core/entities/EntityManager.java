package macbury.indie.core.entities;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.systems.*;

/**
 * Manages creation of entities and entity systems
 */
public class EntityManager extends PooledEngine implements Disposable {
  private OrthographicCamera camera;
  public EntityFactory add;
  private IndiE game;
  private TileMovementSystem tileMovementSystem;
  private CharacterRenderingSystem characterRenderingSystem;
  private CameraFollowEntitySystem followCameraSystem;
  private FSMSystem fsmSystem;

  public EntityManager(IndiE game, OrthographicCamera camera) {
    super();
    this.game                = game;
    this.camera              = camera;
    this.add                 = new EntityFactory(game, this);

    setupSystems();
  }

  private void setupSystems() {
    this.fsmSystem              = new FSMSystem();
    addSystem(fsmSystem);

    this.tileMovementSystem     = new TileMovementSystem(game);
    addSystem(tileMovementSystem);

    this.followCameraSystem     = new CameraFollowEntitySystem(camera);
    addSystem(followCameraSystem);

    this.characterRenderingSystem = new CharacterRenderingSystem(game, camera);
    addSystem(characterRenderingSystem);
  }

  @Override
  public void dispose() {
    add.dispose();
    tileMovementSystem.dispose();
    characterRenderingSystem.dispose();
    followCameraSystem.dispose();
    fsmSystem.dispose();

    followCameraSystem      = null;
    characterRenderingSystem = null;
    tileMovementSystem      = null;
    camera                  = null;
    game                    = null;
    add                     = null;
    fsmSystem               = null;
  }
}
