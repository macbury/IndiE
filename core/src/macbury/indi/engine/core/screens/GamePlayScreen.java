package macbury.indi.engine.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import macbury.indi.engine.core.entities.EntityManager;

/**
 * In this screen player can move on map and interact with events, and fight monsters
 */
public class GamePlayScreen extends ScreenBase {
  private static final String TAG = "GamePlayScreen";
  private EntityManager entities;

  @Override
  public void preload() {
    assets.load("textures:badlogic.jpg", Texture.class);
  }

  @Override
  public void create() {
    this.entities           = new EntityManager(game);
    Texture textureBadlogic = assets.get("textures:badlogic.jpg");

    entities.add.player(new Vector3(1,1,1));
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
    entities.dispose();
  }
}
