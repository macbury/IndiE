package macbury.indi.engine.core.entities;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Disposable;
import macbury.indi.engine.IndiE;

/**
 * Manages creation of entities and entity systems
 */
public class EntityManager extends PooledEngine implements Disposable {
  public final EntityFactory add;
  private IndiE game;

  public EntityManager(IndiE game) {
    super();
    this.game = game;
    this.add  = new EntityFactory(game, this);
  }


  @Override
  public void dispose() {
    add.dispose();
    game = null;
  }
}
