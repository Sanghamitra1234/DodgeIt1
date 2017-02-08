package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sleepygamers.game.DodgeIt;

/**
 * Created by Akash on 30-01-2017.
 */

public class MenuState extends state {
    private Texture bg;
    public Sound mnu;
    private float startPlay = 0, startSett = 0, startCred = 0;
    private Vector3 touchpt;
    private BitmapFont mn;
    private int startHigh = 0;
    private Texture playbtn;
    private Texture credits;
    private Rectangle credits1;
    private float alpha = 1;
    private Rectangle playbtn1;
    private Texture highscoreicn;
    private Rectangle high;
    private Texture settingicon;
    private Rectangle sett;
    public static DodgeIt game;
    public MenuState(GameStateManager gameStateManager) {

        super(gameStateManager);
        bg = new Texture("bg6.jpg");
        playbtn = new Texture("play3.png");
        mn = new BitmapFont(Gdx.files.internal("gmnm.fnt"));
        highscoreicn = new Texture("highscore.png");
        settingicon = new Texture("settings.png");
        credits = new Texture("credits.png");
        credits1 = new Rectangle(190, 50, 100, 100);
        high = new Rectangle(50, 50, 100, 100);
        if (DodgeIt.MUSICON == 1) {
            DodgeIt.music.play();
        }
        sett = new Rectangle(330, 50, 90, 90);
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
        touchpt = new Vector3();
        playbtn1 = new Rectangle(90, 280, 300, 300);
        Gdx.input.setCatchBackKey(false);
        mnu = Gdx.audio.newSound(Gdx.files.internal("mnu.wav"));

    }

    public MenuState(GameStateManager gameStateManager, DodgeIt game) {

        super(gameStateManager);
        this.game = game;
        bg = new Texture("bg6.jpg");
        playbtn = new Texture("play3.png");
        mn = new BitmapFont(Gdx.files.internal("gmnm.fnt"));
        highscoreicn = new Texture("highscore.png");
        settingicon = new Texture("settings.png");
        credits = new Texture("credits.png");
        credits1 = new Rectangle(190, 50, 100, 100);
        high = new Rectangle(50, 50, 100, 100);
        if (DodgeIt.MUSICON == 1) {
            DodgeIt.music.play();
        }
        sett = new Rectangle(330, 50, 90, 90);
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
            if (OverlapTester.pointInRectangle(credits1, touchpt.x, touchpt.y)) {
                if (DodgeIt.SOUNDON == 1)
                    mnu.play();
                gameStateManager.set(new CreditsState(gameStateManager));
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
        spriteBatch.setColor(1, 1, 1, 1);
        spriteBatch.begin();
        spriteBatch.draw(bg, 0, 0, 480, 800);
        spriteBatch.draw(playbtn, 90, 280, 300, 300);
        spriteBatch.draw(highscoreicn, 52, 53, 80, 80);
        spriteBatch.draw(credits, 190, 50, 100, 100);
        spriteBatch.draw(settingicon, 339, 50, 90, 90);
        mn.draw(spriteBatch, "DODGE IT", 65, 700);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        playbtn.dispose();
        highscoreicn.dispose();
        settingicon.dispose();
        mn.dispose();
        credits.dispose();
        mnu.dispose();
    }
}