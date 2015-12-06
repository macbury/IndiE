package macbury.indi.engine.core.assets;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Logger;

/**
 * Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on.
 * Automaticaly map asssets to path
 * */
public class Assets extends AssetManager {
  public Assets() {
    super(new EngineFileHandleResolver());
    setLogger(new Logger("AssetManager", Application.LOG_INFO));
  }

}
