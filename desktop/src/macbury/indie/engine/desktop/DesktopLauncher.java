package macbury.indie.engine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import macbury.indie.IndiE;
import macbury.indie.engine.desktop.config.DesktopConfig;

public class DesktopLauncher {
  public static void main (String[] arg) {
    new LwjglApplication(new IndiE(), new DesktopConfig(arg));
  }
}
