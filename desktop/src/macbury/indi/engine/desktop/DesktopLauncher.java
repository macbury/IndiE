package macbury.indi.engine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import macbury.indi.engine.IndiE;
import macbury.indi.engine.desktop.config.DesktopConfig;

public class DesktopLauncher {
  public static void main (String[] arg) {
    new LwjglApplication(new IndiE(), new DesktopConfig(arg));
  }
}
