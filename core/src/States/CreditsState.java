package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Akash on 30-01-2017.
 */

public class CreditsState extends state implements InputProcessor {
    private BitmapFont fnt;
    private Texture bg;
    CreditsState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bg = new Texture("creditsState.png");
        fnt = new BitmapFont(Gdx.files.internal("gmnm.fnt"));
        fnt.getData().setScale(0.6f, 0.6f);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        MenuState.game.playServices.showScore();
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(1, 1, 1, 1);

    }

    @Override
    public void dispose() {
        fnt.dispose();
        bg.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            gameStateManager.set(new MenuState(gameStateManager));
        }
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
