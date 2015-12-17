package macbury.indie;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import macbury.indie.core.Database;
import macbury.indie.core.assets.Assets;
import macbury.indie.core.input.InputManager;
import macbury.indie.core.screens.GamePlayScreen;
import macbury.indie.core.screens.ScreenManager;
import macbury.indie.core.ui.UIManager;

/**
 * Main game class that holds reference to all main modules;
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
  /**
   * Message comunication class used by {@link macbury.indie.core.entities.EntityManager}
   */
  public MessageDispatcher messages;
  public UIManager ui;
  private FPSLogger fpsLogger;


  @Override
  public void create () {
    Gdx.app.setLogLevel(Application.LOG_DEBUG);
    Gdx.app.log(TAG, "Init...");

    this.messages   = new MessageDispatcher();
    this.fpsLogger  = new FPSLogger();
    this.screens    = new ScreenManager(this);
    this.db         = new Database();
    this.assets     = new Assets();
    this.input      = new InputManager();
    this.ui         = new UIManager();

    Gdx.input.setInputProcessor(new InputMultiplexer(ui, input));

    screens.switchTo(new GamePlayScreen());
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(0,0,0,0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

    input.update(Gdx.graphics.getDeltaTime());
    messages.update(Gdx.graphics.getDeltaTime());
    screens.update();
    ui.act();
    ui.draw();

    fpsLogger.log();
  }

  @Override
  public void resize(int width, int height) {
    screens._resize(width, height);
  }

  @Override
  public void pause() {
    screens._pause();
  }

  @Override
  public void resume() {
    screens._resume();
  }

  @Override
  public void dispose() {
    screens.dispose();
    assets.dispose();
    db.dispose();
  }
}
