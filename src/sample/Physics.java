package sample;

import java.util.ArrayList;

/**
 * Created by alxye on 29-Oct-18.
 */
public class Physics {
    // TODO: 14-Nov-18 Check if this can be removed ---------------------------------------
    /*public static boolean collision(Entity player, ArrayList<Entity> hostiles) {
        for (int i = 0; i < hostiles.size(); i++) {
            if(player.getCollider().intersects(hostiles.get(i).getCollider().getBoundsInLocal())) {
                return true;
            }
        }
        return false;
    }*/
    public static boolean collision(Entity player, ArrayList<Entity> hostiles) {
        for (int i = 0; i < hostiles.size(); i++) {
            if(player.getCollider().intersects(hostiles.get(i).getCollider().getBoundsInLocal())) {
                if(((Enemy)hostiles.get(i)).getEnemyType() == EnemyType.POWERUP)
                    if(((PowerUp)hostiles.get(i)).getPowerUpType() == PowerUpType.SIZE) {
                        ((Player)player).increaseDamage();
                        Main.hostiles.remove(i);
                        return false;
                    }
                return true;
            }
        }
        return false;
    }


    // STOPSHIP: 10-Nov-18 create a ternary operator
    public static int[] collision(ArrayList<Entity> friendlies, ArrayList<Entity> hostiles) {
        for (int i = 0; i < hostiles.size(); i++) {
            for (int j = 0; j < friendlies.size(); j++) {
                if(hostiles.get(i).getCollider().intersects(friendlies.get(j).getCollider().getBoundsInLocal())) {
                    //if the bullet is a missile, do this...
                    if(((Bullet)friendlies.get(j)).getName().equals("missile"))
                        return new int[] {i,j, 1}; //1: enemy id, 2: bullet id, is it a missile 1=true, 0=false
                    return new int[] {i,j, 0};
                }
            }
        }
        return new int[] {-1, -1, -1}; //only int the condition of an error occurring - will never run ;)
    }
}
