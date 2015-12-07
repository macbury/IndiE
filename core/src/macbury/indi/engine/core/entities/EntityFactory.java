package macbury.indi.engine.core.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import macbury.indi.engine.IndiE;

/**
 * This is helper class for creating common game entities
 */
public class EntityFactory implements Disposable {
  private EntityManager entityManager;
  private IndiE game;

  public EntityFactory(IndiE game, EntityManager entityManager) {
    this.game = game;
    this.entityManager = entityManager;
  }

  /**
   * Creates player enitity.
   * @return
   */
  public Entity player(Vector3 spawnPosition) {
    return null;
  }

  @Override
  public void dispose() {
    game = null;
    entityManager = null;
  }
}
