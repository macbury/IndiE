package macbury.indi.engine.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Pool;
import macbury.indi.engine.core.entities.shared.Direction;

import java.util.HashMap;

/**
 * This class contains {@link com.badlogic.gdx.graphics.g2d.Animation} for each {@link macbury.indi.engine.core.entities.shared.Direction}
 */
public class CharacterAnimationComponent implements Component, Pool.Poolable {
  private HashMap<Direction, Animation> animations;

  @Override
  public void reset() {

  }
}
