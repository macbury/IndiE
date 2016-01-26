package macbury.indie.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * This enum describes states for game
 */
public enum ScreenState implements State<ScreenManager> {

  /**
   * In this state screen is fade out, after it is complete change to {@link ScreenState#LoadNextScreen}
   */
  AnimatedHideCurrentScreen {
    @Override
    public void enter(ScreenManager screenManager) {
      final ScreenManager sm = screenManager;
      if (sm.getCurrentScreen() == null) {
        sm.getStateMachine().changeState(ScreenState.LoadNextScreen);
      } else {
        sm.game.ui.fadeInScreen().addAction(run(new Runnable() {
          @Override
          public void run() {
            sm.getStateMachine().changeState(ScreenState.LoadNextScreen);
          }
        }));
      }
    }

    @Override
    public void exit(ScreenManager screenManager) {
      screenManager.hideCurrentScreen();
    }
  },

  /**
   * In this state loading screen is showed and all assets from next screen are added to queue. If loading is complete switch to {@link ScreenState#AnimatedShowCurrentScreen}
   */
  LoadNextScreen {
    @Override
    public void exit(ScreenManager screenManager) {
      screenManager.createAndSwitchToCurrentScreen();
    }

    @Override
    public void update(ScreenManager screenManager) {
      if (screenManager.game.assets.update()) {
        screenManager.getStateMachine().changeState(ScreenState.AnimatedShowCurrentScreen);
      }
    }

    @Override
    public void enter(ScreenManager screenManager) {
      screenManager.linkAndEnqueeNextScreenAssets();
    }
  },

  /**
   * In this state screen is fade in, after it is complete change to {@link ScreenState#ScreenRuntime}
   */
  AnimatedShowCurrentScreen {
    @Override
    public void enter(ScreenManager screenManager) {
      final ScreenManager sm = screenManager;
      screenManager.showAndResizeCurrentScreen();
      screenManager.game.ui.fadeOutScreen().addAction(run(new Runnable() {
        @Override
        public void run() {
          sm.getStateMachine().changeState(ScreenState.ScreenRuntime);
        }
      }));
    }
  },

  /**
   * This State just placeholder
   */
  ScreenRuntime
  ;

  @Override
  public void enter(ScreenManager entity) {

  }

  @Override
  public void update(ScreenManager entity) {

  }

  @Override
  public void exit(ScreenManager entity) {

  }

  @Override
  public boolean onMessage(ScreenManager entity, Telegram telegram) {
    return false;
  }
}
