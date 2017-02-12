package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sleepygamers.game.DodgeIt;

/**
 * Created by Akash on 30-01-2017.
 */

public class HighscoreState extends state implements InputProcessor {
    private BitmapFont fnt;
    private Texture yb, cl, hard, time;
    private Rectangle cl1, time1, hard1;
    private Vector3 touchpt;
    int high;

    HighscoreState(GameStateManager gameStateManager) {
        super(gameStateManager);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
        fnt = new BitmapFont(Gdx.files.internal("new.fnt"));
        yb = new Texture("yellowBar.png");
        cl = new Texture("cl.png");
        time = new Texture("time.png");
        hard = new Texture("hard.png");
        cl1 = new Rectangle(30, 200, 100, 100);
        time1 = new Rectangle(175, 200, 100, 100);
        hard1 = new Rectangle(330, 200, 100, 100);
        touchpt = new Vector3(0, 0, 0);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(cl1, touchpt.x, touchpt.y)) {
                DodgeIt.chk5 = 1;
                MenuState.game.playServices.showScore();

            }
            if (OverlapTester.pointInRectangle(time1, touchpt.x, touchpt.y)) {
                DodgeIt.chk5 = 2;
                MenuState.game.playServices.showScore();
            }
            if (OverlapTester.pointInRectangle(hard1, touchpt.x, touchpt.y)) {
                DodgeIt.chk5 = 3;
                MenuState.game.playServices.showScore();
            }
        }
    }
    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0f, 142 / 255f, 214 / 255f, 1);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        fnt.getData().setScale(0.8f);
        fnt.draw(spriteBatch, "HIGH SCORE", 35, 750);
        spriteBatch.draw(yb, 30, 670, 410, 5);
        fnt.getData().setScale(0.75f);
        fnt.draw(spriteBatch, "Classic", 15, 620);
        fnt.draw(spriteBatch, "Time Attack", 15, 518);
        fnt.draw(spriteBatch, "Hard Core", 15, 408);
        fnt.draw(spriteBatch, Integer.toString(DodgeIt.preferences.getInteger("HighScore")), 365, 620);
        fnt.draw(spriteBatch, Integer.toString(DodgeIt.preferences.getInteger("HighScoreTime")), 365, 518);
        fnt.draw(spriteBatch, Integer.toString(DodgeIt.preferences.getInteger("HighScoreHard")), 365, 408);
        spriteBatch.draw(cl, 30, 220, 100, 100);
        spriteBatch.draw(time, 175, 220, 100, 100);
        spriteBatch.draw(hard, 330, 220, 100, 100);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        fnt.dispose();
        yb.dispose();
        cl.dispose();
        hard.dispose();
        time.dispose();
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
