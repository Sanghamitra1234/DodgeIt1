package States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sleepygamers.game.DodgeIt;

/**
 * Created by Akash on 30-01-2017.
 */

public class SplashState extends state {
    int FLAG =0;
    private Texture splash;
    private DodgeIt gm;

    public SplashState(GameStateManager gameStateManager, DodgeIt dodgeIt) {
        super(gameStateManager);
        gm = dodgeIt;
        splash = new Texture("splash.png");
        camera.setToOrtho(false, DodgeIt.WIDTH,DodgeIt.HIGHT);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt){
        if(FLAG==1){
    try {
        Thread.sleep(2000);
    }catch (InterruptedException e) {
        e.printStackTrace();
    }
            gameStateManager.set(new MenuState(gameStateManager, gm));
    }}

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(splash, camera.position.x - (splash.getWidth() / 2), camera.position.y - (splash.getHeight() / 2), splash.getWidth(), splash.getHeight());
        spriteBatch.end();
        FLAG = 1;
    }

    @Override
    public void dispose() {
        splash.dispose();
    }
}
