package macbury.indie.core.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import macbury.indie.core.entities.CharacterAnimationSpeed;
import macbury.indie.core.entities.shared.Direction;

/**
 * This class contains {@link com.badlogic.gdx.graphics.g2d.Animation} for each {@link macbury.indie.core.entities.shared.Direction}
 */
public class CharacterAnimationComponent implements Component, Pool.Poolable {
  private static final float RESTART_ANIMATION_AFTER = 100.0f;
  private ObjectMap<Direction, Animation> animations;
  private float animationTime;
  private Direction currentDirection;
  private boolean enabled = false;
  public CharacterAnimationComponent() {
    animations = new ObjectMap<Direction, Animation>();
  }

  @Override
  public void reset() {
    enabled          = false;
    currentDirection = Direction.None;
    animations.clear();
    resetAnimations();
  }

  /**
   * Return proper animation frame for passed direction. Updates internal animation time
   * @param nextDirection
   * @param deltaTime
   * @return
   */
  public TextureRegion getAnimationTexture(Direction nextDirection, float deltaTime) {
    if (currentDirection != nextDirection || animationTime > RESTART_ANIMATION_AFTER) {
      resetAnimations();
      currentDirection = nextDirection;
    }

    if (enabled) {
      animationTime += deltaTime;
    } else {
      animationTime = 0;
    }


    Animation animation = animations.get(nextDirection);
    if (animation == null) {
      throw new GdxRuntimeException("Could not find animation for "+ nextDirection.toString());
    }
    return animation.getKeyFrame(animationTime);
  }

  /**
   * Reset animation time
   * @return
   */
  public void resetAnimations() {
    animationTime = 0;
  }

  /**
   * Loads from {@link TextureAtlas} regions by charsetName and {@link Direction}
   * @param atlas
   * @param charsetName
   */
  public void setAtlas(TextureAtlas atlas, String charsetName) {
    createAnimationForDirection(atlas, charsetName, Direction.Left);
    createAnimationForDirection(atlas, charsetName, Direction.Down);
    createAnimationForDirection(atlas, charsetName, Direction.Right);
    createAnimationForDirection(atlas, charsetName, Direction.Up);
  }

  private void createAnimationForDirection(TextureAtlas atlas, String charsetName, Direction direction) {
    String regionName                       = charsetName+"_"+direction.toString().toLowerCase();
    Array<TextureAtlas.AtlasRegion> frames  = atlas.findRegions(regionName);
    if (frames.size <= 2) {
      throw new GdxRuntimeException("There are only " + frames.size + " frames for region "+regionName);
    }
    frames.insert(2, frames.get(0));

    Animation animation = new Animation(CharacterAnimationSpeed.Normal.timeForFrame, frames, Animation.PlayMode.LOOP);
    animations.put(direction, animation);

  }

  /**
   * Reset animation timer end starts it
   */
  public void startAnimation() {
    resetAnimations();
    enabled = true;
  }

  /**
   * Disable animation
   */
  public void stopAnimation() {
    enabled = false;
  }
}
