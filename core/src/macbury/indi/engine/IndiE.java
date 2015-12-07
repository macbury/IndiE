package macbury.indi.engine;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import macbury.indi.engine.core.Database;
import macbury.indi.engine.core.assets.Assets;
import macbury.indi.engine.core.input.InputManager;
import macbury.indi.engine.core.screens.GamePlayScreen;
import macbury.indi.engine.core.screens.ScreenManager;
import sun.rmi.runtime.Log;

/**
 * Main game class that reference all;
 */
public class IndiE extends ApplicationAdapter {
  private static final String TAG = "IndiE";
  /**
   * Manage in game screens
   */
  public ScreenManager screens;
  /**
   * Quick access in game database
   */
  public Database db;

  /**
   *  Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on.
   */
  public Assets assets;
  /**
   * Manages game inputs
   */
  public InputManager input;

  @Override
  public void create () {
    Gdx.app.setLogLevel(Application.LOG_DEBUG);
    Gdx.app.log(TAG, "Init...");
    this.screens = new ScreenManager(this);
    this.db      = new Database();
    this.assets  = new Assets();
    this.input   = new InputManager();

    screens.set(new GamePlayScreen());
    Gdx.input.setInputProcessor(input);
  }


  @Override
  public void render () {
    screens._render();
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
    screens._resize(width, height);
  }

  @Override
  public void pause() {
    super.pause();
    screens._pause();
  }

  @Override
  public void resume() {
    super.resume();
    screens._resume();
  }

  @Override
  public void dispose() {
    super.dispose();
    screens.dispose();
    assets.dispose();
    db.dispose();
  }
}
