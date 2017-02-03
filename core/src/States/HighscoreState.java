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
        bg = new Texture("bg7.jpg");
        fnt = new BitmapFont(Gdx.files.internal("high.fnt"));
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
        fnt.draw(spriteBatch, "HIGH SCORE", 100, 700);
        fnt.draw(spriteBatch,"CLASSIC :    "+ Integer.toString(DodgeIt.preferences.getInteger("HighScore")), 20, 600);
        fnt.draw(spriteBatch,"TIME ATTACK :"+ Integer.toString(DodgeIt.preferences.getInteger("HighScoreTime")), 20, 450);
        fnt.draw(spriteBatch,"HARDCORE :   "+ Integer.toString(DodgeIt.preferences.getInteger("HighScoreHard")), 20, 300);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

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
