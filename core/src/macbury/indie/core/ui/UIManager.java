package macbury.indie.core.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Created on 17.12.15.
 */
public class UIManager extends Stage {
  private final FadeWidget fadeWidget;

  public UIManager() {
    super();
    this.fadeWidget = new FadeWidget();
  }

  public SequenceAction fadeInScreen() {
    SequenceAction sequenceAction = new SequenceAction();
    fadeWidget.getColor().a = 0.0f;
    sequenceAction.addAction(fadeIn(0.40f));
    sequenceAction.addAction(run(new Runnable() {
      @Override
      public void run() {
        //fadeWidget.remove();
      }
    }));
    addActor(fadeWidget);
    sequenceAction.setActor(fadeWidget);
    addAction(sequenceAction);
    return sequenceAction;
  }

  public SequenceAction fadeOutScreen() {
    fadeWidget.remove();
    SequenceAction sequenceAction = new SequenceAction();
    fadeWidget.getColor().a = 1.0f;
    sequenceAction.addAction(fadeOut(0.40f));
    sequenceAction.addAction(run(new Runnable() {
      @Override
      public void run() {
        fadeWidget.remove();
      }
    }));
    addActor(fadeWidget);
    sequenceAction.setActor(fadeWidget);
    addAction(sequenceAction);
    return sequenceAction;
  }
}
