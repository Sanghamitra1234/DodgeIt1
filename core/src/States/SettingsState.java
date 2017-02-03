package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Akash on 30-01-2017.
 */

public class SettingsState extends state implements InputProcessor {
    private BitmapFont fnt;
    SettingsState(GameStateManager gameStateManager) {
        super(gameStateManager);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        fnt = new BitmapFont();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {spriteBatch.begin();
        fnt.draw(spriteBatch,"SETTINGS",100,100);
        spriteBatch.end();

    }

    @Override
    public void dispose() {fnt.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode== Input.Keys.BACK){gameStateManager.set(new MenuState(gameStateManager));}
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
