package sample;

import java.util.ArrayList;

/**
 * Created by alxye on 24-Nov-18.
 */
public class Level {
    //This is a prototype. The work is still in progress
    private int enemyAmount;
    public ArrayList<Entity> enemies = new ArrayList<>();

    public Level(int enemyAmount) {
        this.enemyAmount = enemyAmount;
        for (int i = 0; i < enemyAmount; i++) {
            enemies.add(Main.addGenericEnemy(Main.rand(0, Main.SCREENWIDTH), 50));
        }
    }

    public int getEnemyAmount() {
        return enemyAmount;
    }

    public ArrayList<Entity> getEnemies() {
        return enemies;
    }
}
