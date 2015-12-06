package macbury.indi.engine.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * In this screen player can move on map and interact with events, and fight monsters
 */
public class GamePlayScreen extends ScreenBase {

  @Override
  public void preload() {
    assets.load("textures:badlogic.jpg", Texture.class);
  }

  @Override
  public void create() {
    Texture textureBadlogic = assets.get("textures:badlogic.jpg");
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public boolean isDisposedAfterHide() {
    return false;
  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    assets.unload("textures:badlogic.jpg");
  }
}
