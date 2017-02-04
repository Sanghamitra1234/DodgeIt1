package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Akash on 30-01-2017.
 */

public class CreditsState extends state implements InputProcessor {
    private BitmapFont fnt;
    private Texture bg;
    private int y = 0;
    private int y1 = -50;
    private int y2 = -100;
    private int y3 = -150;

    CreditsState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bg = new Texture("blackbg.png");
        fnt = new BitmapFont(Gdx.files.internal("gmnm.fnt"));
        fnt.getData().setScale(0.6f, 0.6f);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {

        y += 1;
        y1 += 1;
        y2 += 1;
        y3 += 1;
        if (y > 600)
            y = 0;
        if (y1 > 600)
            y1 = 0;
        if (y2 > 600)
            y2 = 0;
        if (y3 > 600)
            y3 = 0;

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(1, 1, 1, 1);
        spriteBatch.begin();
        spriteBatch.draw(bg, 0, 0, 480, 800);
        fnt.draw(spriteBatch, "CREDITS", 130, 770);
        if (y >= 0)
            fnt.draw(spriteBatch, "Developed By:", 90, y);
        if (y1 >= 0)
            fnt.draw(spriteBatch, "Akash", 150, y1);
        if (y2 >= 0)
            fnt.draw(spriteBatch, "Assistant Developer:", 20, y2);
        if (y3 >= 0)
            fnt.draw(spriteBatch, "Asish", 150, y3);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        fnt.dispose();
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
