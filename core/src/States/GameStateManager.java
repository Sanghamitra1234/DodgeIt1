package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Akash on 30-01-2017.
 */

public class GameStateManager {
    public Stack<state> states;
    public GameStateManager()
    {
        states=new Stack<state>();
    }
    public void push(state state)
    {
        states.push(state);
    }
    public void pop()
    {
        states.pop().dispose();
    }
    public void set(state state)
    {
        states.pop();
        states.push(state);
    }
    public void update(float dt) {states.peek().update(dt);
    }
    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }

}
