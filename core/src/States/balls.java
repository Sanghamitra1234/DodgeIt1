package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Akash on 31-01-2017.
 */

public class balls {
    public Rectangle rectangle;
    public Vector2 pos;
    public Vector2 ac;
    public Vector2 vel;
    private int chk;
    private int chk2;
    private int clr;
    private Random random;
    public balls() {
        random = new Random();
        chk = random.nextInt(4);
        if (chk==0) {
            chk2 = random.nextInt(800);
            if (chk2>400){ac = new Vector2(random.nextInt(5),-(random.nextInt(5)) );}else{ac = new Vector2(random.nextInt(5),random.nextInt(5) );}
            pos = new Vector2(0, chk2);
        }
        if (chk==1) {
            chk2 = random.nextInt(480);
            if (chk2>240){ac = new Vector2(-(random.nextInt(10)),random.nextInt(10) );}else{ac = new Vector2(random.nextInt(10),random.nextInt(10) );}
            pos = new Vector2(chk2, 0);
        }
        if (chk==2) {
            chk2 = random.nextInt(800);
            if (chk2>400){ac = new Vector2(-(random.nextInt(5)),-(random.nextInt(5)) );}else{ac = new Vector2(-(random.nextInt(5)),random.nextInt(5) );}
            pos = new Vector2(480, chk2);
        }
        if (chk==3) {

            chk2 = random.nextInt(480);
            if (chk2>240){ac = new Vector2(-(random.nextInt(10)),-(random.nextInt(10)) );}else{ac = new Vector2(random.nextInt(10),-(random.nextInt(10)) );}
            pos = new Vector2(chk2, 800);
        }
        if (ac.x==0&&ac.y==0){
            ac.add(random.nextInt(10),random.nextInt(10));
        }
            vel = new Vector2(0,0);
            rectangle = new Rectangle(pos.x, pos.y, 50, 50);
        clr = random.nextInt(4);
    }
    public void updatePos()
    {
        vel.add(ac.x,ac.y);
        vel.scl(Gdx.graphics.getDeltaTime());
        pos.add(vel.x,vel.y);
        vel.scl(1/(Gdx.graphics.getDeltaTime()));
        rectangle.set(pos.x, pos.y, 50, 50);
    }

    public int getClr() {
        return clr;
    }

    public Rectangle getRectangle() {
        return rectangle;

    }

    public Vector2 getPos() {

        return pos;
    }

    }
