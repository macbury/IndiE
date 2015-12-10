package macbury.indie.engine.desktop;


import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created on 02.12.15.
 */
public class MakeTextureAtlases {
  public static void main (String[] arg) {
    TexturePacker.Settings settings = new TexturePacker.Settings();
    settings.stripWhitespaceX = settings.stripWhitespaceY = true;
    TexturePacker.process(settings, "./player_frames", "../android/assets/graphics/charsets/", "npc");
  }
}