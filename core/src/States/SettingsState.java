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

public class SettingsState extends state implements InputProcessor {
    int flag = 0;
    private BitmapFont fnt;
    private Texture bg;
    private Vector3 touchpt;
    private Rectangle soun, musi, rese;

    SettingsState(GameStateManager gameStateManager) {
        super(gameStateManager);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        bg = new Texture("SettingsState.png");
        fnt = new BitmapFont(Gdx.files.internal("cour.fnt"));
        camera.setToOrtho(false, 480, 800);
        musi = new Rectangle(300, 570, 80, 40);
        soun = new Rectangle(300, 490, 80, 40);
        rese = new Rectangle(300, 395, 80, 40);
        touchpt = new Vector3(0, 0, 0);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            flag = 2;
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(musi, touchpt.x, touchpt.y)) {
                if (DodgeIt.MUSICON == 1) {
                    DodgeIt.MUSICON = 0;
                    DodgeIt.music.stop();
                } else {
                    DodgeIt.MUSICON = 1;
                    DodgeIt.music.play();
                }
            }
            if (OverlapTester.pointInRectangle(soun, touchpt.x, touchpt.y)) {
                if (DodgeIt.SOUNDON == 1) {
                    DodgeIt.SOUNDON = 0;
                } else {
                    DodgeIt.SOUNDON = 1;
                }
            }
            if (OverlapTester.pointInRectangle(rese, touchpt.x, touchpt.y)) {
                flag = 1;
                DodgeIt.preferences.putInteger("HighScoreTime", 0);
                DodgeIt.preferences.putInteger("HighScoreHard", 0);
                DodgeIt.preferences.putInteger("HighScore", 0);
                DodgeIt.preferences.flush();
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, 0, 0, 480, 800);
        if (DodgeIt.MUSICON == 1) {
            fnt.draw(spriteBatch, "ON", 310, 605);
        } else {
            fnt.draw(spriteBatch, "OFF", 300, 605);
        }
        if (DodgeIt.SOUNDON == 1)
            fnt.draw(spriteBatch, "ON", 310, 509);
        else
            fnt.draw(spriteBatch, "OFF", 300, 509);
        if (flag == 1) {
            fnt.draw(spriteBatch, "OK", 308, 413);
        } else {
            fnt.draw(spriteBatch, " ", 308, 413);
        }
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
