package macbury.indie.core.assets;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.utils.Logger;
import macbury.indie.core.assets.loaders.TrixelMapLoader;
import macbury.indie.core.map.TrixelMap;

/**
 * Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on.
 * Automaticaly map asssets to path
 * */
public class Assets extends AssetManager {
  public Assets() {
    this(new EngineFileHandleResolver());
  }

  public Assets(FileHandleResolver resolver) {
    super(resolver);
    setLogger(new Logger("AssetManager", Application.LOG_INFO));
    setLoader(TrixelMap.class, new TrixelMapLoader(resolver));
  }
}
