package macbury.indie.core.entities;

import com.badlogic.ashley.core.ComponentMapper;
import macbury.indie.core.entities.components.*;

/**
 * This class contains {@link ComponentMapper} for each component
 */
public class Components {
  public final static ComponentMapper<FollowCameraComponent> FollowCamera             = ComponentMapper.getFor(FollowCameraComponent.class);
  public final static ComponentMapper<PositionComponent> Position                     = ComponentMapper.getFor(PositionComponent.class);
  public final static ComponentMapper<TileMovementComponent> TileMovement             = ComponentMapper.getFor(TileMovementComponent.class);
  public final static ComponentMapper<CharacterAnimationComponent> CharacterAnimation = ComponentMapper.getFor(CharacterAnimationComponent.class);
  public final static ComponentMapper<FSMComponent> FSM                               = ComponentMapper.getFor(FSMComponent.class);
  public final static ComponentMapper<JoystickComponent> Joystick                     = ComponentMapper.getFor(JoystickComponent.class);
}
