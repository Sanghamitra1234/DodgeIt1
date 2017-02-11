package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.sleepygamers.game.DodgeIt;

import java.util.Random;

import static com.sleepygamers.game.DodgeIt.ggg;

/**
 * Created by Akash on 30-01-2017.
 */

public class PlayState extends state implements InputProcessor {
    private int FLAGtch = 0;
    private int cnt = 0, ppl = 0;
    private int newballcnt = 0;
    private Array<balls> bl;
    private int index = 0;
    private BitmapFont fnt;
    private int usrrctcnt = 0;
    private int Strt = 0;
    public float TimePassed = 0;
    public int score = 0;
    private Vector3 touchpt;
    private Texture userbtn, red, blue, green, yellow;
    private Rectangle usr;
    public static Music mus;
    private Random random;
    public static Sound gameover;

    PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        random = new Random();
        userbtn = new Texture("userbtn.jpg");
        red = new Texture("red.png");
        blue = new Texture("blue.png");
        bl = new Array<balls>();
        fnt = new BitmapFont(Gdx.files.internal("new1.fnt"));
        yellow = new Texture("yellow.png");
        green = new Texture("green.png");
        touchpt = new Vector3();
        Gdx.input.setInputProcessor(this);
        gameover = Gdx.audio.newSound(Gdx.files.internal("gameover.wav"));
        if (DodgeIt.MUSICON == 1) {
            mus = Gdx.audio.newMusic(Gdx.files.internal("mus.mp3"));
            mus.play();
            mus.setLooping(true);
        }
        Gdx.input.setCatchBackKey(true);
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        if (FLAGtch > 2) {
            if (DodgeIt.SOUNDON == 1)
                gameover.play();
            gameStateManager.push(new GameOver(gameStateManager, score, 0));
        }
        if (FLAGtch == 2) {
            TimePassed += Gdx.graphics.getDeltaTime();
            score = (int) (TimePassed * 5);
            if (score >= 1000) {
                ggg = 5;
                MenuState.game.playServices.unlockAchievement();
            } else if (score >= 500) {
                ggg = 4;
                MenuState.game.playServices.unlockAchievement();
            } else if (score >= 300) {
                ggg = 3;
                MenuState.game.playServices.unlockAchievement();
            } else if (score >= 100) {
                ggg = 2;
                MenuState.game.playServices.unlockAchievement();
            } else if (score >= 50) {
                ggg = 1;
                MenuState.game.playServices.unlockAchievement();
            }

            newballcnt++;


            if (newballcnt == 10) {
                if (random.nextInt(3) == 1) {
                    bl.add(new balls());
                }
                newballcnt = 0;
            }
            index = 0;
            for (balls bl2 : bl) {
                bl2.updatePos();
                ppl = 0;
                if (bl2.pos.x > 480 || bl2.pos.y > 800 || bl2.pos.x < 0 || bl2.pos.y < 0) {
                    bl.removeIndex(index);
                }
                if (bl2.getRectangle().overlaps(usr)) {
                    if (DodgeIt.MUSICON == 1) {
                        mus.stop();
                    }
                    if (DodgeIt.SOUNDON == 1)
                        gameover.play();
                    gameStateManager.set(new GameOver(gameStateManager, score, 0));
                }
                index++;
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
            //spriteBatch.draw(userbtn,100,400,50,50);
        }
        if (FLAGtch == 2) {
            spriteBatch.draw(userbtn, touchpt.x, touchpt.y, 50, 50);
            for (balls bl2 : bl) {
                if (bl2.getClr() == 0) {
                    spriteBatch.draw(yellow, bl2.getPos().x, bl2.getPos().y, 50, 50);
                }
                if (bl2.getClr() == 1) {
                    spriteBatch.draw(green, bl2.getPos().x, bl2.getPos().y, 50, 50);
                }
                if (bl2.getClr() == 2) {
                    spriteBatch.draw(blue, bl2.getPos().x, bl2.getPos().y, 50, 50);
                }
                if (bl2.getClr() == 3) {
                    spriteBatch.draw(red, bl2.getPos().x, bl2.getPos().y, 50, 50);
                }
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
        red.dispose();
        blue.dispose();
        green.dispose();
        yellow.dispose();
        userbtn.dispose();
        gameover.dispose();

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
        camera.unproject(touchpt.set(screenX, screenY, 0));
        if (usrrctcnt == 0) {
            usr = new Rectangle(touchpt.x, touchpt.y, 50, 50);
            usrrctcnt = 1;
        }
        if (usrrctcnt == 1) {
            usr.set(touchpt.x, touchpt.y, 50, 50);
        }
        Strt = 1;
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
