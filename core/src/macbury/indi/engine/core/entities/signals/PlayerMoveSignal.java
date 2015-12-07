package macbury.indi.engine.core.entities.signals;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;

/**
 * This signal is triggered by {@link macbury.indi.engine.core.entities.systems.PlayerControllerSystem} if any of {@link macbury.indi.engine.core.input.InputManager.ActionButton}
 * is triggered
 */
public class PlayerMoveSignal extends Signal<Entity> {
}
