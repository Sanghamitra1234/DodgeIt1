package States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Akash on 30-01-2017.
 */

public abstract class state {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;
    state(GameStateManager gameStateManager)
    {
        this.gameStateManager = gameStateManager;
        camera= new OrthographicCamera();
        mouse = new Vector3();
    }
    public abstract void handleInput();
    public abstract void  update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
