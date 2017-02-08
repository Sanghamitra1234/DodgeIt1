package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sleepygamers.game.DodgeIt;

/**
 * Created by Akash on 30-01-2017.
 */

public class HighscoreState extends state implements InputProcessor {
    private BitmapFont fnt;
    private Texture bg;
    int high;

    HighscoreState(GameStateManager gameStateManager) {
        super(gameStateManager);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
        bg = new Texture("HighScoreS.png");
        fnt = new BitmapFont(Gdx.files.internal("cour.fnt"));
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, 0, 0, 480, 800);
        fnt.draw(spriteBatch, Integer.toString(DodgeIt.preferences.getInteger("HighScore")), 350, 640);
        fnt.draw(spriteBatch, Integer.toString(DodgeIt.preferences.getInteger("HighScoreTime")), 350, 558);
        fnt.draw(spriteBatch, Integer.toString(DodgeIt.preferences.getInteger("HighScoreHard")), 350, 478);
        spriteBatch.end();
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
