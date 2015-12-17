package macbury.indie.core.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Disposable;

/**
 * This class represents game map
 */
public class TrixelMap implements MapRenderer, Disposable {
  private static final String TAG = "TrixelMap";

  public TrixelMap(int width, int height) {

  }

  @Override
  public void setView(OrthographicCamera camera) {

  }

  @Override
  public void setView(Matrix4 projectionMatrix, float viewboundsX, float viewboundsY, float viewboundsWidth, float viewboundsHeight) {

  }

  @Override
  public void render() {

  }

  @Override
  public void render(int[] layers) {

  }

  @Override
  public void dispose() {
    Gdx.app.log(TAG, "dispose");
  }
}
