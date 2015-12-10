package macbury.indie.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import macbury.indie.core.entities.EntityManager;

/**
 * In this screen player can move on map and interact with events, and fight monsters
 */
public class GamePlayScreen extends ScreenBase {
  private static final String TAG = "GamePlayScreen";
  private EntityManager entities;
  private OrthographicCamera camera;

  @Override
  public void preload() {
    assets.load("charsets:debug_player.png", Texture.class);
    assets.load("textures:a.png", Texture.class);
    assets.load("textures:b.png", Texture.class);
  }

  @Override
  public void create() {
    this.camera             = new OrthographicCamera();
    this.entities           = new EntityManager(game, camera);

    entities.add.player(new Vector3(0,0,0));
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    camera.update();
    Gdx.gl.glClearColor(1,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    entities.update(delta);
  }

  @Override
  public void resize(int width, int height) {
    camera.setToOrtho(false, width/3, height/3);
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
    assets.unload("charsets:debug_player.png");
    assets.unload("textures:a.png");
    assets.unload("textures:b.png");
    entities.dispose();
    camera = null;
  }
}
