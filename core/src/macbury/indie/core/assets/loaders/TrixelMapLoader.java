package macbury.indie.core.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import macbury.indie.core.map.TrixelMap;

import java.io.IOException;

/**
 * This class loads trixel map
 */
public class TrixelMapLoader extends AsynchronousAssetLoader<TrixelMap, TrixelMapLoader.Parameters> {
  private XmlReader xml = new XmlReader();
  private XmlReader.Element root;
  private TrixelMap map;

  public TrixelMapLoader(FileHandleResolver resolver) {
    super(resolver);
  }

  @Override
  public void loadAsync(AssetManager manager, String fileName, FileHandle file, Parameters parameter) {
    try {
      root     = xml.parse(file);
      this.map = new TrixelMap(root.getInt("width"), root.getInt("height"));

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      root = null;
    }
  }

  @Override
  public TrixelMap loadSync(AssetManager manager, String fileName, FileHandle file, Parameters parameter) {
    TrixelMap currentMap  = map;
    map                   = null;
    return currentMap;
  }

  @Override
  public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, Parameters parameter) {
    Array<AssetDescriptor> deps = new Array();
    deps.add(new AssetDescriptor("textures:a.png", Texture.class));
    deps.add(new AssetDescriptor("textures:b.png", Texture.class));
    return deps;
  }


  public static class Parameters extends AssetLoaderParameters<TrixelMap> {

  }
}
