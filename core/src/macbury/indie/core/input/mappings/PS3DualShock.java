package macbury.indie.core.input.mappings;

public enum PS3DualShock {
  Up(4),
  Down(6),
  Left(7),
  Right(5),
  Select(0),
  X(14),
  Square(15),
  O(13),
  Triangle(12),
  LeftAxisX(0),
  LeftAxisY(1);

  public final int keyCode;

  PS3DualShock(int keyCode) {
    this.keyCode = keyCode;
  }

  public boolean is(int otherKeyCode) {
    return this.keyCode == otherKeyCode;
  }
}