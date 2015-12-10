package macbury.indie.core.entities.signals;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;

/**
 * This signal is triggered by {@link macbury.indie.core.entities.systems.PlayerControllerSystem} if any of {@link macbury.indie.core.input.InputManager.ActionButton}
 * is triggered
 */
public class PlayerMoveSignal extends Signal<Entity> {
}
