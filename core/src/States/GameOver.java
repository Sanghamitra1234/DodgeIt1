package States;

import com.badlogic.gdx.Gdx;
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

public class GameOver extends state implements InputProcessor {
    private Texture bg, chalkBoard, home, replay;
    private Vector3 touchpt;
    private Rectangle homeRec, replayRec;
    public static int score1;
    private int gamee;
    private BitmapFont fnt, fnt1;

    GameOver(GameStateManager gameStateManager, int score, int gme) {
        super(gameStateManager);
        bg = new Texture("blackbg.png");
        score1 = score;
        gamee = gme;
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(false);
        chalkBoard = new Texture("chalkboard.jpg");
        home = new Texture("home.png");
        homeRec = new Rectangle(120, 200, 70, 70);
        replayRec = new Rectangle(300, 200, 70, 70);

        touchpt = new Vector3();
        replay = new Texture("replay.png");
        fnt = new BitmapFont(Gdx.files.internal("gmnm.fnt"));
        fnt1 = new BitmapFont(Gdx.files.internal("cour.fnt"));
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
        homeRec = new Rectangle(120, 200, 70, 70);
        replayRec = new Rectangle(300, 200, 70, 70);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(homeRec, touchpt.x, touchpt.y)) {
                if (DodgeIt.MUSICON == 1) {
                    if (gamee == 0) {
                        PlayState.mus.stop();
                    } else if (gamee == 1) {
                        HardPlayState.mus.stop();
                    } else {
                        TimeAttackPlayState.mus.stop();
                    }
                }
                gameStateManager.set(new MenuState(gameStateManager));
            }
            if (OverlapTester.pointInRectangle(replayRec, touchpt.x, touchpt.y)) {
                if (gamee == 0) {
                    PlayState.mus.stop();
                    gameStateManager.set(new PlayState(gameStateManager));
                } else if (gamee == 1) {
                    HardPlayState.mus.stop();
                    gameStateManager.set(new HardPlayState(gameStateManager));
                } else {
                    TimeAttackPlayState.mus.stop();
                    gameStateManager.set(new TimeAttackPlayState(gameStateManager));
                }
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
        spriteBatch.draw(chalkBoard, 80, 300, 320, 400);
        spriteBatch.draw(home, 120, 200, 70, 70);
        spriteBatch.draw(replay, 300, 200, 80, 80);
        fnt.draw(spriteBatch, "SCORE:", 120, 660);
        if (score1 < 100) {
            fnt.draw(spriteBatch, Integer.toString(score1), 200, 570);
        } else if (score1 < 1000) {
            fnt.draw(spriteBatch, Integer.toString(score1), 180, 570);
        } else {
            fnt.draw(spriteBatch, Integer.toString(score1), 160, 570);
        }
        if (gamee == 0 && (score1 >= DodgeIt.preferences.getInteger("HighScore"))) {
            fnt1.draw(spriteBatch, "High Score", 110, 400);
            DodgeIt.preferences.putInteger("HighScore", score1);
            DodgeIt.preferences.flush();
            MenuState.game.playServices.submitScore(score1);
        }
        if (gamee != 1 && gamee != 0 && (score1 >= DodgeIt.preferences.getInteger("HighScoreTime"))) {
            fnt1.draw(spriteBatch, "High Score", 110, 400);
            DodgeIt.preferences.putInteger("HighScoreTime", score1);
            DodgeIt.preferences.flush();
        }
        if (gamee == 1 && (score1 >= DodgeIt.preferences.getInteger("HighScoreHard"))) {
            fnt1.draw(spriteBatch, "High Score", 110, 400);
            DodgeIt.preferences.putInteger("HighScoreHard", score1);
            DodgeIt.preferences.flush();
        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        fnt.dispose();
        fnt1.dispose();
        bg.dispose();
        chalkBoard.dispose();
        home.dispose();
        replay.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
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
