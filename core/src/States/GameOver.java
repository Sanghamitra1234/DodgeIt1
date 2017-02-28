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
    public static int score1;
    private Texture home, replay, bl;
    private Vector3 touchpt;
    private Rectangle homeRec, replayRec;
    private int gamee, ypt = -620;
    private BitmapFont fnt, fnt1;

    GameOver(GameStateManager gameStateManager, int score, int gme) {
        super(gameStateManager);
        score1 = score;
        gamee = gme;
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(false);
        home = new Texture("home.png");
        touchpt = new Vector3();
        bl = new Texture("blueBar.png");
        replay = new Texture("replay.png");
        fnt = new BitmapFont(Gdx.files.internal("new2.fnt"));
        fnt1 = new BitmapFont(Gdx.files.internal("new1.fnt"));
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);

        homeRec = new Rectangle(110, 75, 95, 95);
        replayRec = new Rectangle(290, 70, 100, 100);
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
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    gameStateManager.set(new PlayState(gameStateManager));
                } else if (gamee == 1) {
                    HardPlayState.mus.stop();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    gameStateManager.set(new HardPlayState(gameStateManager));
                } else {
                    TimeAttackPlayState.mus.stop();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    gameStateManager.set(new TimeAttackPlayState(gameStateManager));
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        if (ypt <= 180) ypt += 60;
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bl, 0, ypt, 480, 620);
        if (ypt >= 180) {
            spriteBatch.draw(home, 110, 75, 95, 95);
            spriteBatch.draw(replay, 290, 70, 100, 100);
            fnt.draw(spriteBatch, "SCORE", 120, 660);

            if (score1 < 10) {
                fnt.draw(spriteBatch, Integer.toString(score1), 230, 570);
            } else if (score1 < 100) {
                fnt.draw(spriteBatch, Integer.toString(score1), 210, 570);
            } else if (score1 < 1000) {
                fnt.draw(spriteBatch, Integer.toString(score1), 190, 570);
            } else {
                fnt.draw(spriteBatch, Integer.toString(score1), 170, 570);
            }
            if (gamee == 0 && (score1 >= DodgeIt.preferences.getInteger("HighScore"))) {
                fnt1.draw(spriteBatch, "High Score", 120, 400);
                DodgeIt.preferences.putInteger("HighScore", score1);
                DodgeIt.preferences.flush();
                DodgeIt.playServices.submitScore(DodgeIt.preferences.getInteger("HighScore"));
            }
            if (gamee != 1 && gamee != 0 && (score1 >= DodgeIt.preferences.getInteger("HighScoreTime"))) {
                fnt1.draw(spriteBatch, "High Score", 110, 400);
                DodgeIt.preferences.putInteger("HighScoreTime", score1);
                DodgeIt.preferences.flush();
                DodgeIt.playServices.submitScore(DodgeIt.preferences.getInteger("HighScoreTime"));
            }
            if (gamee == 1 && (score1 >= DodgeIt.preferences.getInteger("HighScoreHard"))) {
                fnt1.draw(spriteBatch, "High Score", 110, 400);
                DodgeIt.preferences.putInteger("HighScoreHard", score1);
                DodgeIt.preferences.flush();
                DodgeIt.playServices.submitScore(DodgeIt.preferences.getInteger("HighScoreHard"));
            }
        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        fnt.dispose();
        fnt1.dispose();
        PlayState.mus.dispose();
        TimeAttackPlayState.mus.dispose();
        home.dispose();
        replay.dispose();
        HardPlayState.mus.dispose();
        bl.dispose();
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
