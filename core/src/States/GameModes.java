package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sleepygamers.game.DodgeIt;

/**
 * Created by Akash on 02-02-2017.
 */

public class GameModes extends state implements InputProcessor {
    private Texture bg, classic, hard, timeattack, question;
    private Rectangle cl, hrd, tme, q1, q2, q3;
    private Vector3 touchpt;
    public Sound mnu;

    public GameModes(GameStateManager gameStateManager) {
        super(gameStateManager);
        bg = new Texture("bg7.jpg");
        classic = new Texture("classic.png");
        question = new Texture("question.png");
        touchpt = new Vector3();
        hard = new Texture("hardcore.png");
        cl = new Rectangle(140, 550, 200, 200);
        q1 = new Rectangle(330, 580, 150, 150);
        hrd = new Rectangle(140, 50, 200, 200);
        q2 = new Rectangle(330, 80, 150, 150);
        tme = new Rectangle(140, 300, 200, 200);
        q3 = new Rectangle(330, 330, 150, 150);
        mnu = Gdx.audio.newSound(Gdx.files.internal("mnu.wav"));
        timeattack = new Texture("TimeAttack.png");
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(cl, touchpt.x, touchpt.y)) {
                DodgeIt.music.stop();
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new PlayState(gameStateManager));
            }

        }
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(hrd, touchpt.x, touchpt.y)) {
                DodgeIt.music.stop();
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new HardPlayState(gameStateManager));
            }


            if (Gdx.input.justTouched()) {
                camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
                if (OverlapTester.pointInRectangle(tme, touchpt.x, touchpt.y)) {
                    DodgeIt.music.stop();
                    if (DodgeIt.SOUNDON == 1)
                        mnu.play();
                    gameStateManager.set(new TimeAttackPlayState(gameStateManager));
                }
            }
        }
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(q1, touchpt.x, touchpt.y)) {
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new ClassicHint(gameStateManager));

            }
        }
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(q3, touchpt.x, touchpt.y)) {
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new TimeAttackHint(gameStateManager));

            }
        }
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(q2, touchpt.x, touchpt.y)) {
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new HardCoreHint(gameStateManager));

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
        spriteBatch.draw(hard, 140, 50, 200, 200);
        spriteBatch.draw(timeattack, 140, 300, 200, 200);
        spriteBatch.draw(classic, 140, 550, 200, 200);
        spriteBatch.draw(question, 330, 580, 150, 150);
        spriteBatch.draw(question, 330, 80, 150, 150);
        spriteBatch.draw(question, 330, 330, 150, 150);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        classic.dispose();
        question.dispose();
        hard.dispose();
        timeattack.dispose();
        mnu.dispose();

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
