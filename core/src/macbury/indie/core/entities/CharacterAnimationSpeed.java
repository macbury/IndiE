package macbury.indie.core.entities;

/**
 * Created on 10.12.15.
 */
public enum CharacterAnimationSpeed {
  Normal(0.20f);
  public final float timeForFrame;
  CharacterAnimationSpeed(float timeForFrame) {
    this.timeForFrame = timeForFrame;
  }
}
