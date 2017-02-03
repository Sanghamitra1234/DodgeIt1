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

public class TimeAttackPlayState extends state implements InputProcessor {
    private int FLAGtch = 0;
    private int cnt = 0, ppl = 0;
    private int newballcnt = 0;
    private Array<balls> bl;
    private int index = 0;
    private BitmapFont fnt;
    private int usrrctcnt = 0;
    private int Strt = 0;
    public float TimePassed = 0, timeDelay = 0;
    public int score = 0, time = 0;
    private int currentColor = 0;
    private Vector3 touchpt;
    private Texture bg, userbtn, red, blue, green, yellow, yellowBar, greenBar, blueBar, redBar;
    private Rectangle usr;
    private Random random;

    TimeAttackPlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        random = new Random();
        bg = new Texture("bg7.jpg");
        userbtn = new Texture("userbtn.jpg");
        red = new Texture("red.png");
        blue = new Texture("blue.png");
        redBar = new Texture("redBar.png");
        blueBar = new Texture("blueBar.png");
        bl = new Array<balls>();
        fnt = new BitmapFont(Gdx.files.internal("ourfont.fnt"));
        yellow = new Texture("yellow.png");
        green = new Texture("green.png");
        yellowBar = new Texture("yellowBar.png");
        greenBar = new Texture("greenBar.png");
        touchpt = new Vector3();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        if (FLAGtch > 2) {
            gameStateManager.set(new GameOver(gameStateManager, score, 3));
        }
        if (FLAGtch == 2) {
            TimePassed += Gdx.graphics.getDeltaTime();
            timeDelay += Gdx.graphics.getDeltaTime();
            time = (int) (60 - TimePassed);
            if (random.nextInt(50) == 1) {
                if (timeDelay > 5) {
                    currentColor = random.nextInt(4);
                    timeDelay = 0;
                }
            }
            if (time == 0) {
                if (score > DodgeIt.preferences.getInteger("HighScoreTime")) {
                    DodgeIt.preferences.putInteger("HighScoreTime", score);
                    DodgeIt.preferences.flush();
                }
                gameStateManager.set(new GameOver(gameStateManager, score, 3));
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
                    if (currentColor == bl2.getClr()) {
                        score += 1;
                        bl.removeIndex(index);
                    } else {
                        if (score > DodgeIt.preferences.getInteger("HighScoreTime")) {
                            DodgeIt.preferences.putInteger("HighScoreTime", score);
                            DodgeIt.preferences.flush();}
                            gameStateManager.set(new GameOver(gameStateManager, score, 3));
                        }
                    }
                    index++;
                }
            }
        }

        @Override
        public void render (SpriteBatch spriteBatch){
            spriteBatch.setProjectionMatrix(camera.combined);
            spriteBatch.begin();
            spriteBatch.setColor(1, 1, 1, 1);
            spriteBatch.draw(bg, 0, 0, 480, 800);
            if (Strt == 0) {
                fnt.draw(spriteBatch, "HOLD TO PLAY", 80, 500);
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
                fnt.draw(spriteBatch, "0:" + Integer.toString(time), 10, 760);
                fnt.draw(spriteBatch, Integer.toString(score), 400, 760);
                if (currentColor == 0) {
                    spriteBatch.draw(yellowBar, 0, 790, 480, 10);
                    spriteBatch.draw(yellowBar, 0, 0, 480, 10);
                } else if (currentColor == 1) {
                    spriteBatch.draw(greenBar, 0, 790, 480, 10);
                    spriteBatch.draw(greenBar, 0, 0, 480, 10);
                } else if (currentColor == 2) {
                    spriteBatch.draw(blueBar, 0, 790, 480, 10);
                    spriteBatch.draw(blueBar, 0, 0, 480, 10);
                } else {
                    spriteBatch.draw(redBar, 0, 790, 480, 10);
                    spriteBatch.draw(redBar, 0, 0, 480, 10);
                }
            }
            spriteBatch.end();

        }

        @Override
        public void dispose () {
            fnt.dispose();
            red.dispose();
            blue.dispose();
            green.dispose();
            yellow.dispose();
            userbtn.dispose();
            bg.dispose();

        }

        @Override
        public boolean keyDown ( int keycode){
            if (keycode == Input.Keys.BACK) {
                gameStateManager.set(new MenuState(gameStateManager));
            }
            return false;
        }

        @Override
        public boolean keyUp ( int keycode){
            return false;
        }

        @Override
        public boolean keyTyped ( char character){
            return false;
        }

        @Override
        public boolean touchDown ( int screenX, int screenY, int pointer, int button){
            FLAGtch = 2;
            Strt = 1;
            return false;
        }

        @Override
        public boolean touchUp ( int screenX, int screenY, int pointer, int button){
            FLAGtch++;
            return false;
        }

        @Override
        public boolean touchDragged ( int screenX, int screenY, int pointer){
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
        public boolean mouseMoved ( int screenX, int screenY){
            return false;
        }

        @Override
        public boolean scrolled ( int amount){
            return false;
        }
    }
