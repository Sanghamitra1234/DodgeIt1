package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public static Music mus;
    public static Sound gameover;
    private int cnt = 0, ppl = 0;
    public static int firstball = 0;
    private Array<ballsHard> bl;
    private int index = 0, lvl = 150;
    private BitmapFont fnt;
    private int usrrctcnt = 0;
    private int Strt = 0;
    public float TimePassed = 0, tm = 0;
    public int score = 0, scoreDelay = 0;
    private static Vector3 touchpt;
    private Texture userbtn, hardBall;
    private TextureAtlas hrdbl;
    private Animation animation;
    private static Rectangle usr;
    private Random random;

    public static Rectangle getUsr() {
        return usr;
    }

    HardPlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        random = new Random();
        userbtn = new Texture("userbtn.jpg");
        hrdbl = new TextureAtlas(Gdx.files.internal("bl.atlas"));
        animation = new Animation(1 / 10f, hrdbl.getRegions());
        bl = new Array<ballsHard>();
        hardBall = new Texture("userbtn.jpg");
        fnt = new BitmapFont(Gdx.files.internal("new1.fnt"));
        touchpt = new Vector3();
        firstball = 0;
        gameover = Gdx.audio.newSound(Gdx.files.internal("gameover.wav"));
        if (DodgeIt.MUSICON == 1) {
            mus = Gdx.audio.newMusic(Gdx.files.internal("mus.mp3"));
            mus.play();
            mus.setLooping(true);
        }
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
            if (DodgeIt.SOUNDON == 1)
                gameover.play();
            gameStateManager.set(new GameOver(gameStateManager, score, 1));
        }
        if (FLAGtch == 2) {
            TimePassed += Gdx.graphics.getDeltaTime();
            score = (int) (TimePassed * 5);
            if (firstball == 0) {
                bl.add(new ballsHard());
                firstball = 1;
            }
            for (ballsHard bl1 : bl) {
                bl1.updatePos();
                if (bl1.getRectangle().overlaps(usr)){
                    if (DodgeIt.MUSICON == 1) {
                        mus.stop();
                    }
                    if (DodgeIt.SOUNDON == 1)
                        gameover.play();
                    gameStateManager.set(new GameOver(gameStateManager, score, 1));
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0.1294f, 0.1254f, 0.1607f, 1);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        if (Strt == 0) {
            fnt.draw(spriteBatch, "HOLD TO PLAY", 40, 500);
        }
        if (FLAGtch == 2) {
            spriteBatch.draw(userbtn, touchpt.x, touchpt.y, 50, 50);
            for (ballsHard bl2 : bl) {
                // spriteBatch.draw(hardBall, bl2.getPos().x, bl2.getPos().y, 50, 50);
                spriteBatch.draw((TextureRegion) animation.getKeyFrame(TimePassed, true), bl2.getPos().x, bl2.getPos().y, 100, 100);
            }

            if (score < 1000) {
                if (score < 100) {
                    if (score < 10) {
                        fnt.draw(spriteBatch, Integer.toString(score), 430, 760);
                    } else {
                        fnt.draw(spriteBatch, Integer.toString(score), 410, 760);
                    }
                } else {
                    fnt.draw(spriteBatch, Integer.toString(score), 390, 760);
                }
            } else
                fnt.draw(spriteBatch, Integer.toString(score), 370, 760);

        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        fnt.dispose();
        userbtn.dispose();
        gameover.dispose();
        hrdbl.dispose();
        hardBall.dispose();
        mus.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            if (DodgeIt.MUSICON == 1) {
                mus.stop();
            }
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
