package States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sleepygamers.game.DodgeIt;

/**
 * Created by Akash on 30-01-2017.
 */

public class SplashState extends state {
    int FLAG =0;
    private Texture logo;
    public SplashState(GameStateManager gameStateManager) {
        super(gameStateManager);
        logo = new Texture("logo.JPG");
        camera.setToOrtho(false, DodgeIt.WIDTH,DodgeIt.HIGHT);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt){
        if(FLAG==1){
    try {
        Thread.sleep(1000);
    }catch (InterruptedException e) {
        e.printStackTrace();
    }
        gameStateManager.set(new MenuState(gameStateManager));
    }}

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
        spriteBatch.draw(logo,camera.position.x-(logo.getWidth()/2),camera.position.y-(logo.getHeight()/2));
    spriteBatch.end();
        FLAG = 1;
    }

    @Override
    public void dispose() {logo.dispose();

    }
}
