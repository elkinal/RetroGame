package sample;

import java.awt.*;
import java.util.Random;

/**
 * Created by alxye on 29-Oct-18.
 */
public class Guns {
    public static float DAMAGE_MOD = 1;


    //Generating a normal bullet
    public static Bullet rifleBullet() {
        return new Bullet(4.0f * DAMAGE_MOD, Main.player.getX()+Main.player.getWidth()/2 - 10,
                Main.player.getY()+Main.player.getHeight()/2 - 10,
                (float) MouseInfo.getPointerInfo().getLocation().getX() + rand(-Main.player.getRecoil(),Main.player.getRecoil() * (Main.friendlies.size()+2)),
                (float) MouseInfo.getPointerInfo().getLocation().getY() + rand(-Main.player.getRecoil(),Main.player.getRecoil()) * (Main.friendlies.size()+2), 40.0f, 20, 20, "rifle", Main.display.rifleBullet);
    }

    //Generating shotgun pellets
    public static Bullet[] shotgunBullet(int bulletAmount) {
        Bullet[] bullets = new Bullet[bulletAmount];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet(10.0f * DAMAGE_MOD, Main.player.getX()+Main.player.getWidth()/2 - 5,
                    Main.player.getY()+Main.player.getHeight()/2 - 5,
                    (float) MouseInfo.getPointerInfo().getLocation().getX() + rand(-Main.player.getRecoil(),Main.player.getRecoil() * (Main.friendlies.size()+2)),
                    (float) MouseInfo.getPointerInfo().getLocation().getY() + rand(-Main.player.getRecoil(),Main.player.getRecoil()) * (Main.friendlies.size()+2), 45.0f, 10, 10, "shotgun", Main.display.rifleBullet);
        }
        return bullets;
    }
    //Generating sniper bullets
    public static Bullet sniperBullet() {
        return new Bullet(45.0f * DAMAGE_MOD, Main.player.getX()+Main.player.getWidth()/2 -12,
                Main.player.getY()+Main.player.getHeight()/2 - 12,
                (float) MouseInfo.getPointerInfo().getLocation().getX() + rand(-Main.player.getRecoil()/4,Main.player.getRecoil() * (Main.friendlies.size()+2)),
                (float) MouseInfo.getPointerInfo().getLocation().getY() + rand(-Main.player.getRecoil()/4,Main.player.getRecoil()) * (Main.friendlies.size()+2), 40.0f, 24, 24, "sniper", Main.display.rifleBullet);
    }

    //Generating missiles
    public static Bullet missile() {
        return new Bullet(30.0f * DAMAGE_MOD, Main.player.getX()+Main.player.getWidth()/2 - 25,
                Main.player.getY()+Main.player.getHeight()/2 - 25,
                (float) MouseInfo.getPointerInfo().getLocation().getX() + rand(-Main.player.getRecoil()/4,Main.player.getRecoil()),
                (float) MouseInfo.getPointerInfo().getLocation().getY() + rand(-Main.player.getRecoil()/4,Main.player.getRecoil()), 20.0f, 50, 50, "missile", Main.display.rifleBullet);
    }

    public static Bullet[] shrapnel(int bulletAmount, int x, int y) {
        Bullet[] bullets = new Bullet[bulletAmount];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet(30.0f * DAMAGE_MOD, x, y,
                    x + rand(-500, 500),
                    y + rand(-500, 500),
                    30.0f, 10, 10, "shotgun", Main.display.rifleBullet);
        }
        return bullets;
    }
    private static float rand(float min, float max) {
        return new Random().nextInt((int) (max - min + 1)) + min;
    }
}
