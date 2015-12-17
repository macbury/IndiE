package macbury.indie.engine.desktop;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.ObjectMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 02.12.15.
 */
public class MakeTextureAtlases {
  public static void main (String[] arg) {

    File charsetOutDir = new File("./charsets_out");

    try {
      FileUtils.cleanDirectory(charsetOutDir);
    } catch (IOException e) {
      e.printStackTrace();
    }

    File charsetInDir  = new File("./charsets_in");


    for(File file : charsetInDir.listFiles()) {
      try {
        String name = FilenameUtils.removeExtension(file.getName());
        File output = new File(charsetOutDir, name + "_%d.png");

        String cmd = "convert -crop 32x32 "+file.getCanonicalPath()+" " + output.getCanonicalPath();
        System.out.println(cmd);
        Runtime.getRuntime().exec(cmd).waitFor();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    String nameMappings[] = new String[] {
      "down_1",
      "down_0",
      "down_2",
      "left_1",
      "left_0",
      "left_2",
      "right_1",
      "right_0",
      "right_2",
      "up_1",
      "up_0",
      "up_2",
    };

    Pattern fileNamePattern = Pattern.compile("^(.+)_([0-9]+)$");

    for(File file : charsetOutDir.listFiles()) {
      String name     = FilenameUtils.removeExtension(file.getName());
      Matcher matcher = fileNamePattern.matcher(name);

      if (matcher.find()) {
        int idOfFrame = Integer.valueOf(matcher.group(2));
        String basename = matcher.group(1);
        File finalFileName = new File(charsetOutDir, basename + "_" + nameMappings[idOfFrame] + ".png");
        file.renameTo(finalFileName);

        try {
          System.out.println("Renaming: " + file.getCanonicalPath() + " to " + finalFileName.getCanonicalPath());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    TexturePacker.Settings settings = new TexturePacker.Settings();
    settings.stripWhitespaceX = settings.stripWhitespaceY = true;
    TexturePacker.process(settings, "./charsets_out", "../android/assets/graphics/charsets/", "charset");
  }
}