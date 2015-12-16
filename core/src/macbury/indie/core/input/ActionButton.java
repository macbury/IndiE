package macbury.indie.core.input;

import macbury.indie.core.entities.shared.Direction;

public enum ActionButton {
  Left, Right, Up, Down, CancelOrQuit, DoAction;

  /**
   * Return true if action button is {@link ActionButton#Left}, {@link ActionButton#Right}
   * {@link ActionButton#Top}, {@link ActionButton#Down}
   *
   * @param actionButton
   * @return
   */
  public static boolean isMovement(ActionButton actionButton) {
    return (actionButton == Left || actionButton == Right || actionButton == Up || actionButton == DoAction);
  }

  /**
   * Maps current {@link ActionButton} to {@link Direction}
   *
   * @return
   */
  public Direction toDirection() {
    switch (this) {
      case Up:
        return Direction.Up;

      case Down:
        return Direction.Down;

      case Left:
        return Direction.Left;

      case Right:
        return Direction.Right;
    }

    throw new RuntimeException("" + this + " is not movement");
  }
}
