package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import macbury.indie.core.entities.shared.Direction;

/**
 * This class contains {@link com.badlogic.gdx.graphics.g2d.Animation} for each {@link macbury.indie.core.entities.shared.Direction}
 */
public class CharacterAnimationComponent implements Component, Pool.Poolable {
  private ObjectMap<Direction, Animation> animations;
  private Texture texture;

  public CharacterAnimationComponent() {
    animations = new ObjectMap<Direction, Animation>();
  }

  @Override
  public void reset() {
    animations.clear();
    texture  = null;
  }

  public void setTexture(Texture texture) {
    this.texture = texture;
  }

  public Texture getTexture() {
    return texture;
  }
}
