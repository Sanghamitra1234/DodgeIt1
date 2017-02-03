package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sleepygamers.game.DodgeIt;

/**
 * Created by Akash on 30-01-2017.
 */

public class GameOver extends state {
    private Texture bg, chalkBoard, home, replay;
    private Vector3 touchpt;
    private Rectangle homeRec, replayRec;
    public static int score1;
    private int gamee;
    private BitmapFont fnt;

    GameOver(GameStateManager gameStateManager, int score,int gme) {
        super(gameStateManager);
        bg = new Texture("blackbg.png");
        score1 = score;
        gamee=gme;
        chalkBoard = new Texture("chalkboard.jpg");
        home = new Texture("home.png");
        homeRec = new Rectangle(120, 200, 70, 70);
        replayRec = new Rectangle(300, 200, 70, 70);

        touchpt = new Vector3();
        replay = new Texture("replay.png");
        fnt = new BitmapFont(Gdx.files.internal("gmnm.fnt"));
        camera.setToOrtho(false, DodgeIt.WIDTH, DodgeIt.HIGHT);
        homeRec = new Rectangle(120, 200, 70, 70);
        replayRec = new Rectangle(300, 200, 70, 70);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchpt.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(homeRec, touchpt.x, touchpt.y)) {
                gameStateManager.set(new MenuState(gameStateManager));
            }
            if (OverlapTester.pointInRectangle(replayRec, touchpt.x, touchpt.y)) {
                if (gamee==0){gameStateManager.set(new PlayState(gameStateManager));}
                else if (gamee==1){gameStateManager.set(new HardPlayState(gameStateManager));}
                else {gameStateManager.set(new TimeAttackPlayState(gameStateManager));}
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
        fnt.draw(spriteBatch, Integer.toString(score1), 200, 600);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
