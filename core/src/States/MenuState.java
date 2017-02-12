package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

public class MenuState extends state {
    public Sound mnu;
    private float startPlay = 0, startSett = 0, startCred = 0;
    private Vector3 touchpt;
    private BitmapFont mn;
    private int startHigh = 0;
    private int index = 0;
    private Texture playbtn;
    private int strt = 1;
    private float alpha = 1;
    private Rectangle playbtn1;
    private Texture red, blue, green, yellow, nm;
    private Texture highscoreicn;
    private Rectangle high;
    private Texture settingicon;
    private Rectangle sett;
    private Array<MenuBalls> bl;
    private Random random;
    public static DodgeIt game;

    public MenuState(GameStateManager gameStateManager) {

        super(gameStateManager);
        playbtn = new Texture("play3.png");
        mn = new BitmapFont(Gdx.files.internal("new.fnt"));
        red = new Texture("red.png");
        yellow = new Texture("yellow.png");
        green = new Texture("green.png");
        blue = new Texture("blue.png");
        bl = new Array<MenuBalls>();
        highscoreicn = new Texture("highscore.png");
        settingicon = new Texture("settings.png");
        high = new Rectangle(90, 50, 100, 100);
        if (DodgeIt.MUSICON == 1) {
            DodgeIt.music.play();
        }
        random = new Random();
        sett = new Rectangle(299, 50, 90, 90);
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
        touchpt = new Vector3();
        playbtn1 = new Rectangle(90, 280, 300, 300);
        Gdx.input.setCatchBackKey(false);
        mnu = Gdx.audio.newSound(Gdx.files.internal("mnu.wav"));

    }

    public MenuState(GameStateManager gameStateManager, DodgeIt game) {

        super(gameStateManager);
        this.game = game;
        playbtn = new Texture("play3.png");
        mn = new BitmapFont(Gdx.files.internal("new.fnt"));
        highscoreicn = new Texture("highscore.png");
        settingicon = new Texture("settings.png");
        random = new Random();
        bl = new Array<MenuBalls>();
        red = new Texture("red.png");
        yellow = new Texture("yellow.png");
        green = new Texture("green.png");
        blue = new Texture("blue.png");
        high = new Rectangle(90, 50, 100, 100);
        if (DodgeIt.MUSICON == 1) {
            DodgeIt.music.play();
        }
        random = new Random();
        sett = new Rectangle(299, 50, 90, 90);
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
        touchpt = new Vector3();
        playbtn1 = new Rectangle(90, 280, 300, 300);
        Gdx.input.setCatchBackKey(false);
        mnu = Gdx.audio.newSound(Gdx.files.internal("mnu.wav"));
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(playbtn1, touchpt.x, touchpt.y)) {
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new GameModes(gameStateManager));
            }
            if (OverlapTester.pointInRectangle(high, touchpt.x, touchpt.y)) {
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new HighscoreState(gameStateManager));
            }
            if ((OverlapTester.pointInRectangle(sett, touchpt.x, touchpt.y))) {
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new SettingsState(gameStateManager));
            }
        }
    }

    @Override
    public void update(float dt) {
        if (strt < 6) {
            bl.add(new MenuBalls());
            strt++;
        }
        if (random.nextInt(20) == 1) {
            bl.add(new MenuBalls());
        }
        index = 0;
        for (MenuBalls b1 : bl) {
            b1.updatePos();
            if (b1.getPos().x > 480 || b1.pos.y > 800 || b1.getPos().x < 0 || b1.getPos().y < 0) {
                if (index > 2) {
                    bl.removeIndex(index);
                }
            }
            index++;
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0.8705f, 0.8745f, 0.8078f, 1);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.setColor(1, 1, 1, 1);
        spriteBatch.begin();
        for (MenuBalls bl2 : bl) {
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
        spriteBatch.draw(playbtn, 90, 250, 300, 300);
        spriteBatch.draw(highscoreicn, 102, 83, 80, 80);
        spriteBatch.draw(settingicon, 299, 80, 90, 90);
        mn.draw(spriteBatch, "Dodge It", 65, 700);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        playbtn.dispose();
        highscoreicn.dispose();
        settingicon.dispose();
        mn.dispose();
        mnu.dispose();
    }
}