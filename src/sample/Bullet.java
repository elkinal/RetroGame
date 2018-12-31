package sample;

import javafx.scene.image.Image;

import java.awt.*;

/**
 * Created by alxye on 29-Oct-18.
 */
public class Bullet extends Entity {
    float damage;
    float sX;
    float sY;
    float eX;
    float eY;
    String name;
//    private int lifespan = 0;
//    private int maxLifeSpan = 100;

    public Bullet(float damage, float sX, float sY, float eX, float eY, float speed, float width, float height, String name, Image image) {
        super(sX, sY, width, height, image, speed);
        this.damage = damage;
        this.sX = sX;
        this.sY = sY;
        this.eX = eX;
        this.eY = eY;
        this.name = name;
    }

    public float getDamage() {
        return damage;
    }

    public String getName() {
        return this.name;
    }

    public void tick() {
        float deltaX = eX - sX;
        float deltaY = eY - sY;
        float angle = (float) Math.atan2( deltaY, deltaX );

        this.addX((float) (Main.deltaTime/10 * getSpeed() * Math.cos(angle)));
        this.addY((float) (Main.deltaTime/10 * getSpeed() * Math.sin(angle)));
        if (this.getX() < -getWidth() || this.getX() > Main.SCREENWIDTH || this.getY() < -getHeight() || this.getY() > Main.SCREENHEIGHT) {
            Main.friendlies.remove(this);
        }

        /*lifespan++;
        if(lifespan > maxLifeSpan) {
            Main.friendlies.remove(this);
        }
        else {
            if(getX() + getWidth() > Main.SCREENWIDTH)
                setX(0);
            if(getX() < 0)
                setX(Main.SCREENWIDTH - getWidth()+1);
        }*/



    }

}
