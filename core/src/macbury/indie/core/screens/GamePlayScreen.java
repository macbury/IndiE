package macbury.indie.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import macbury.indie.core.entities.EntityManager;
import macbury.indie.core.map.TrixelMap;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * In this screen player can move on map and interact with events, and fight monsters
 */
public class GamePlayScreen extends ScreenBase {
  private static final String TAG = "GamePlayScreen";
  private EntityManager entities;
  private OrthographicCamera camera;
  private TrixelMap maps;

  @Override
  public void preload() {
    assets.load("maps:test.xml", TrixelMap.class);
    assets.load("charsets:charset.atlas", TextureAtlas.class);
  }

  @Override
  public void create() {
    this.camera             = new OrthographicCamera();
    this.maps               = assets.get("maps:test.xml", TrixelMap.class);
    this.entities           = new EntityManager(game, camera);

    entities.add.monster(5,5);
    entities.add.player(1,1);
  }

  @Override
  public void show() {
    entities.resume();
  }

  @Override
  public void render(float delta) {
    camera.update();
    maps.setView(camera);
    maps.render();
    entities.update(delta);
  }

  @Override
  public void resize(int width, int height) {
    camera.setToOrtho(false, width, height);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public boolean isDisposedAfterHide() {
    return true;
  }

  @Override
  public void hide() {
    entities.pause();
  }

  @Override
  public void dispose() {
    assets.unload("charsets:charset.atlas");
    assets.unload("maps:test.xml");
    entities.dispose();
    camera = null;
  }
}
