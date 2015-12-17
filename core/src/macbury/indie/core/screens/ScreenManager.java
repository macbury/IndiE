package macbury.indie.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import macbury.indie.IndiE;

/**
 * Manages in game screens.
 */
public class ScreenManager implements Disposable {
  private static final String TAG = "ScreenManager";
  public final IndiE game;
  private final DefaultStateMachine<ScreenManager> stateMachine;
  private ScreenBase currentScreen;
  private ScreenBase nextScreen;

  /**
   * Initializes {@link ScreenManager} and assigns {@link IndiE} game reference
   * @param game
   */
  public ScreenManager(IndiE game) {
    this.game         = game;
    this.stateMachine = new DefaultStateMachine<ScreenManager>(this);
  }

  public void switchTo(ScreenBase nextScreen) {
    if (this.nextScreen != null)
      throw new GdxRuntimeException("Want to change screen while another screen did not finish switching!!!!");
    this.nextScreen = nextScreen;
    stateMachine.changeState(ScreenState.AnimatedHideCurrentScreen);
  }

  /**
   * Hides current screen by calling {@link ScreenBase#hide()}, if required dispose it and clears all {@link IndiE#messages}
   */
  public void hideCurrentScreen() {
    if (this.currentScreen != null) {
      Gdx.app.log(TAG, "Hiding " + currentScreen.getClass().getSimpleName());
      this.currentScreen.hide();
      if (this.currentScreen.isDisposedAfterHide()){
        Gdx.app.log(TAG, "Disposing " + currentScreen.getClass().getSimpleName());
        this.currentScreen.dispose();
      }
      this.currentScreen.unlink();
      this.game.messages.clear();
    }
    currentScreen = null;
  }

  /**
   * Link next screen and adds all assets to load
   */
  public void linkAndEnqueeNextScreenAssets() {
    if (this.nextScreen != null) {
      this.nextScreen.link(game);
      Gdx.app.log(TAG, "Initializing " + nextScreen.getClass().getSimpleName());
      this.nextScreen.preload();
    }
  }

  /**
   * Switch next screen to current screen and triggers {@link ScreenBase#create()}
   */
  public void createAndSwitchToCurrentScreen() {
    this.currentScreen = nextScreen;
    nextScreen         = null;
    this.currentScreen.create();
  }

  /**
   * Triggers  {@link ScreenBase#show()} and {@link ScreenBase#resize(int, int)} on current screen
   */
  public void showAndResizeCurrentScreen() {
    Gdx.app.log(TAG, "Showing " + currentScreen.getClass().getSimpleName());
    this.currentScreen.show();
    this.currentScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
  public void update() {
    stateMachine.update();

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

  public ScreenBase getNextScreen() {
    return nextScreen;
  }

  public ScreenBase getCurrentScreen() {
    return currentScreen;
  }

  public DefaultStateMachine<ScreenManager> getStateMachine() {
    return this.stateMachine;
  }

}