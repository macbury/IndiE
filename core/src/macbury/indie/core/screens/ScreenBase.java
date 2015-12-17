package macbury.indie.core.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import macbury.indie.IndiE;
import macbury.indie.core.assets.Assets;
import macbury.indie.core.input.InputManager;
import macbury.indie.core.ui.UIManager;

/** <p>
 * Represents one of many application screens, such as a main menu, a settings menu, the game screen and so on.
 * </p>
 * <p>
 * Note that {@link #dispose()} is not called automatically.
 * </p>*/
public abstract class ScreenBase implements Disposable {
  protected IndiE game;
  protected Assets assets;
  protected UIManager ui;
  protected ScreenManager screens;
  protected InputManager input;

  /**
   * Links references to current {@link IndiE}
   * @param game
   */
  public void link(IndiE game) {
    this.unlink();
    this.game     = game;
    this.assets   = game.assets;
    this.ui       = game.ui;
    this.screens  = game.screens;
    this.input    = game.input;
  }

  /**
   * Unlink references to current {@link IndiE}
   */
  public void unlink() {
    this.game     = null;
    this.assets   = null;
    this.ui       = null;
    this.screens  = null;
    this.input    = null;
  }

  /**
   * Called before {@link ScreenBase#create()}. You can add assets to load here. If there are assets to load it shows loading screen
   */
  public abstract void preload();
  /**
   * Called when screen is showed for the first time
   */
  public abstract void create();

  /** Called when this screen becomes the current screen for a Game. */
  public abstract void show ();

  /** Called when the screen should render itself.
   * @param delta The time in seconds since the last render. */
  public abstract void render (float delta);

  /** Called after screen show or game window resize */
  public abstract void resize (int width, int height);

  public abstract void pause ();

  public abstract void resume ();

  /**
   * If return true, after {@link ScreenBase#hide()} it will call {@link ScreenBase#dispose()}
   * @return
   */
  public abstract boolean isDisposedAfterHide();

  /**
   * This method is called before next screen will show
   */
  public abstract void hide ();

}