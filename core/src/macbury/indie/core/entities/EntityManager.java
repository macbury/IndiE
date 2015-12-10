package macbury.indie.core.entities;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.entities.signals.PlayerMoveSignal;
import macbury.indie.core.entities.systems.PlayerControllerSystem;
import macbury.indie.core.entities.systems.RenderingSystem;
import macbury.indie.core.entities.systems.TileMovementSystem;

/**
 * Manages creation of entities and entity systems
 */
public class EntityManager extends PooledEngine implements Disposable {
  public EntityFactory add;
  public PlayerMoveSignal playerMoveSignal;
  private IndiE game;
  private PlayerControllerSystem playerControllerSystem;
  private TileMovementSystem tileMovementSystem;
  private RenderingSystem renderingSystem;

  public EntityManager(IndiE game) {
    super();
    this.playerMoveSignal    = new PlayerMoveSignal();
    this.game                = game;
    this.add                 = new EntityFactory(game, this);

    setupSystems();
  }

  private void setupSystems() {
    this.tileMovementSystem     = new TileMovementSystem(game);
    addSystem(tileMovementSystem);

    this.playerControllerSystem = new PlayerControllerSystem(game);
    addSystem(playerControllerSystem);

    this.renderingSystem        = new RenderingSystem(game);
    addSystem(renderingSystem);
  }

  @Override
  public void dispose() {
    add.dispose();
    playerControllerSystem.dispose();
    tileMovementSystem.dispose();
    renderingSystem.dispose();

    playerControllerSystem = null;
    renderingSystem    = null;
    tileMovementSystem = null;
    game = null;
    add  = null;
  }
}
