package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Akash on 03-02-2017.
 */

public class ballsHard {
    public Rectangle rectangle,Big1;
    public Vector2 pos;
    public Vector2 ac;
    private float Timepassed1 = 0, tp2 = 0;
    private int xval,yval;
    public Vector2 vel;
    private Random rand;

    public ballsHard() {
        rand = new Random();
        if (HardPlayState.firstball == 0) {

            pos = new Vector2(0, 750);
            ac = new Vector2(rand.nextInt(5), -(rand.nextInt(5)));
            vel = new Vector2(0, 0);
            rectangle = new Rectangle(pos.x, pos.y, 100, 100);
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void updatePos() {
        tp2 += Gdx.graphics.getDeltaTime();
        int ylow = (int) (pos.y) - 240;
        int yhigh = (int) (pos.y + 290);
        if (ylow <= 0) {
            ylow = 0;
        }
        if (yhigh >= 800) {
            yhigh = 800;
        }
        Big1 = new Rectangle(0, ylow, 480, yhigh - ylow);
        Timepassed1 += Gdx.graphics.getDeltaTime();
        if (Big1.overlaps(HardPlayState.getUsr())){
            if (HardPlayState.getTouchpt().x-pos.x>0){xval=1;}else {xval=(-1);}
            ac.x = (6 + rand.nextInt(4)) * (xval);
            if (HardPlayState.getTouchpt().y-pos.y>0){yval=1;}else {yval=(-1);}
            ac.y = ac.x *((pos.y-(HardPlayState.getTouchpt().y))/(pos.x-(HardPlayState.getTouchpt().x)));
            if (ac.y>0){}else {ac.y*=-1;}
            ac.y*=yval;
            Timepassed1 = 0;
        }
        if (Timepassed1 > 2) {
            if (HardPlayState.getTouchpt().x-pos.x>0){xval=1;}else {xval=(-1);}
            ac.x = (3+rand.nextInt(5))*(xval);
            if (HardPlayState.getTouchpt().y-pos.y>0){yval=1;}else {yval=(-1);}
            ac.y = ac.x *((pos.y-(HardPlayState.getTouchpt().y))/(pos.x-(HardPlayState.getTouchpt().x)));
            if (ac.y>0){}else {ac.y*=-1;}
            ac.y*=yval;
        }
        if (ac.x==0&&ac.y==0){ac.x = rand.nextInt(5);
        ac.y = rand.nextInt(5);}
        vel.add(ac);
        vel.scl(Gdx.graphics.getDeltaTime());
        pos.add(vel);

        vel.scl(1/(Gdx.graphics.getDeltaTime()));
        if(pos.x<=0){pos.x=0;
        ac.x*=-1;
        vel.x=0;}
        if (pos.x >= 380) {
            pos.x = 380;
            ac.x*=-1;
            vel.x=0;}
        if (pos.y<=0){
            pos.y=0;
            ac.y*=-1;
            vel.y=0;
        }
        if (pos.y >= 700) {
            pos.y = 700;
            ac.y*=-1;
            vel.y=0;}
        rectangle.set(pos.x, pos.y, 100, 100);
    }
    public Vector2 getPos() {
        return pos;
    }
}
