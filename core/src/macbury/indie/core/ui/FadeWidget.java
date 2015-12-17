package macbury.indie.core.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created on 17.12.15.
 */
public class FadeWidget extends Image {

  public FadeWidget() {
    super();
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.BLACK);
    pixmap.fill();

    setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(pixmap))));
  }

  @Override
  public float getWidth() {
    return Gdx.graphics.getWidth();
  }

  @Override
  public float getHeight() {
    return Gdx.graphics.getHeight();
  }
}
