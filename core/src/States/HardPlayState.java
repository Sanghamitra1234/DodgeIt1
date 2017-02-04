package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.sleepygamers.game.DodgeIt;

import java.util.Random;

/**
 * Created by Akash on 30-01-2017.
 */

public class HardPlayState extends state implements InputProcessor {
    private int FLAGtch = 0;
    private int cnt = 0, ppl = 0;
    public static int firstball = 0;
    private Array<ballsHard> bl;
    private int index = 0,lvl=150;
    private BitmapFont fnt;
    private int usrrctcnt = 0;
    private int Strt = 0;
    public float TimePassed = 0;
    public int score = 0, scoreDelay = 0;
    private static Vector3 touchpt;
    private Texture bg, userbtn, hardBall;
    private static Rectangle usr;
    private Random random;

    public static Rectangle getUsr() {
        return usr;
    }

    HardPlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        random = new Random();
        bg = new Texture("bg2.png");
        userbtn = new Texture("userbtn.jpg");
        bl = new Array<ballsHard>();
        hardBall = new Texture("userbtn.jpg");
        fnt = new BitmapFont(Gdx.files.internal("ourfont.fnt"));
        touchpt = new Vector3();
        firstball = 0;
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
    }

    @Override
    public void handleInput() {

    }

    public static Vector3 getTouchpt() {
        return touchpt;
    }

    @Override
    public void update(float dt) {
        if (FLAGtch > 2) {
            gameStateManager.set(new GameOver(gameStateManager, score,1));
        }
        if (FLAGtch == 2) {
            TimePassed += Gdx.graphics.getDeltaTime();
            score = (int) (TimePassed*5);
            if (firstball == 0) {
                bl.add(new ballsHard());
                firstball = 1;
            }
            for (ballsHard bl1 : bl) {
                bl1.updatePos();
                if (bl1.getRectangle().overlaps(usr)){
                    if(score>DodgeIt.preferences.getInteger("HighScoreHard")){DodgeIt.preferences.putInteger("HighScoreHard",score);}
                    DodgeIt.preferences.flush();
                    gameStateManager.set(new GameOver(gameStateManager,score,1));}
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, 0, 0, 480, 800);
        if (Strt == 0) {
            fnt.draw(spriteBatch, "HOLD TO PLAY", 80, 500);
        }
        if (FLAGtch == 2) {
            spriteBatch.draw(userbtn, touchpt.x, touchpt.y, 50, 50);
            for (ballsHard bl2 : bl) {
                spriteBatch.draw(hardBall, bl2.getPos().x, bl2.getPos().y, 50, 50);
            }
            fnt.draw(spriteBatch, Integer.toString(score), 400, 760);
        }
        fnt.draw(spriteBatch, Integer.toString(score), 400, 760);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        fnt.dispose();
        userbtn.dispose();
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
        FLAGtch = 2;
        Strt = 1;
        camera.unproject(touchpt.set(screenX, screenY, 0));
        if (usrrctcnt == 0) {
            usr = new Rectangle(touchpt.x, touchpt.y, 50, 50);
            usrrctcnt = 1;
        }
        if (usrrctcnt == 1) {
            usr.set(touchpt.x, touchpt.y, 50, 50);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        FLAGtch++;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        camera.unproject(touchpt.set(screenX, screenY, 0));
        if (usrrctcnt == 0) {
            usr = new Rectangle(touchpt.x, touchpt.y, 50, 50);
            usrrctcnt = 1;
        }
        if (usrrctcnt == 1) {
            usr.set(touchpt.x, touchpt.y, 50, 50);
        }
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
