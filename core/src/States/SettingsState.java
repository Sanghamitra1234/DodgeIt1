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
    private Vector3 touchpt;
    private Texture wb, bb, pp, yb;
    private Rectangle soun, musi, rese, achi;

    SettingsState(GameStateManager gameStateManager) {
        super(gameStateManager);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        fnt = new BitmapFont(Gdx.files.internal("new.fnt"));
        camera.setToOrtho(false, 480, 800);
        musi = new Rectangle(30, 563, 240, 50);
        soun = new Rectangle(30, 467, 240, 50);
        bb = new Texture("blackball.png");
        wb = new Texture("whiteball.png");
        pp = new Texture("playyy.png");
        yb = new Texture("yellowBar.png");
        achi = new Rectangle(30, 371, 450, 50);
        rese = new Rectangle(30, 275, 430, 50);
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
            if (OverlapTester.pointInRectangle(achi, touchpt.x, touchpt.y)) {
                DodgeIt.playServices.showAchievement();
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
        Gdx.gl.glClearColor(222 / 255f, 174 / 255f, 49 / 255f, 1);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        fnt.getData().setScale(1f);
        fnt.draw(spriteBatch, "SETTINGS", 35, 750);
        spriteBatch.draw(yb, 30, 670, 420, 5);
        fnt.getData().setScale(0.75f);
        fnt.draw(spriteBatch, "Music", 100, 605);
        fnt.draw(spriteBatch, "Sound", 100, 509);
        fnt.draw(spriteBatch, "Achievement", 100, 413);
        fnt.draw(spriteBatch, "Reset Score", 100, 317);
        spriteBatch.draw(wb, 30, 563, 50, 50);
        spriteBatch.draw(wb, 30, 467, 50, 50);
        spriteBatch.draw(pp, 30, 371, 50, 50);
        spriteBatch.draw(pp, 30, 275, 50, 50);
        if (DodgeIt.MUSICON == 1) {
            spriteBatch.draw(bb, 40, 573, 30, 30);
        }
        if (DodgeIt.SOUNDON == 1) {
            spriteBatch.draw(bb, 40, 477, 30, 30);
        }
        if (flag == 1) {
            fnt.draw(spriteBatch, "Done", 290, 230);
        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        fnt.dispose();
        wb.dispose();
        pp.dispose();
        yb.dispose();
        bb.dispose();
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
