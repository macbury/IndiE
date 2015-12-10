package macbury.indie.engine.desktop.config;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * This class manages configuration of {@link LwjglApplicationConfiguration} using program paramerers
 */
public class DesktopConfig extends LwjglApplicationConfiguration {
  public DesktopConfig(String[] arg) {
    super();

    this.width     = 1360;
    this.height    = 768;
    this.resizable = false;
    this.title     = "IndiE(ngine)";
  }
}
