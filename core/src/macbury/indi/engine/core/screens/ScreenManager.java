package macbury.indi.engine.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import macbury.indi.engine.IndiE;

/**
 * Manages in game screens.
 */
public class ScreenManager implements Disposable {
  private static final String TAG = "ScreenManager";
  private final IndiE game;
  private ScreenBase currentScreen;

  /**
   * Initializes {@link ScreenManager} and assigns {@link IndiE} game reference
   * @param game
   */
  public ScreenManager(IndiE game) {
    this.game = game;
  }

  /**
   * Sets the current screen. {@link ScreenBase#hide()} is called on any old screen, and {@link ScreenBase#show()} is called on the new
   * screen, if any. If first time screen is showed {@link ScreenBase#create()} is called;
   * @param nextScreen may be {@code null}
   */
  public void set(ScreenBase nextScreen) {
    if (this.currentScreen != null) {
      Gdx.app.log(TAG, "Hiding " + currentScreen.getClass().getSimpleName());
      this.currentScreen.hide();
      this.currentScreen.unlink();
      if (this.currentScreen.isDisposedAfterHide()){
        Gdx.app.log(TAG, "Disposing " + currentScreen.getClass().getSimpleName());
        this.dispose();
      }
    }
    this.currentScreen = nextScreen;
    if (this.currentScreen != null) {
      this.currentScreen.link(game);

      if (!this.currentScreen.isInitialized()) {
        Gdx.app.log(TAG, "Initializing " + currentScreen.getClass().getSimpleName());
        this.currentScreen.preload();
        this.game.assets.finishLoading();
        this.currentScreen.create();
      }

      Gdx.app.log(TAG, "Showing " + currentScreen.getClass().getSimpleName());
      this.currentScreen.show();
      this.currentScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
  }

  @Override
  public void dispose() {
    if (currentScreen != null) {
      currentScreen.dispose();
      currentScreen = null;
    }
  }

  /**
   * Triggers render with delta time for current {@link ScreenBase}
   */
  public void _render() {
    if (currentScreen != null) {
      currentScreen.render(Gdx.graphics.getDeltaTime());
    }
  }

  /**
   * Triggers resume for current {@link ScreenBase}
   */
  public void _resume() {
    if (currentScreen != null) {
      currentScreen.resume();
    }
  }

  /**
   * Triggers pause for current {@link ScreenBase}
   */
  public void _pause() {
    if (currentScreen != null) {
      currentScreen.pause();
    }
  }

  /**
   * Triggers resize for current {@link ScreenBase}
   */
  public void _resize(int width, int height) {
    if (currentScreen != null) {
      currentScreen.resize(width, height);
    }
  }
}