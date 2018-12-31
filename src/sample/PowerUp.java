package sample;

import javafx.scene.image.Image;

/**
 * Created by alxye on 05-Dec-18.
 */
public class PowerUp extends Enemy {
    private PowerUpType powerUpType;

    public PowerUp(float x, float y, float width, float height, EnemyType enemyType, PowerUpType powerUpType) {
        super(x, y, width, height, enemyType);
        this.powerUpType = powerUpType;
    }

    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    public void setPowerUpType(PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }

    public void effect() {
        if(powerUpType == PowerUpType.SIZE) {
            setImage(Main.display.powerUpImages[0]);
            Main.player.setSize(30, 30);
        }
        if(powerUpType == PowerUpType.MINE) {
            setImage(Main.display.powerUpImages[0]);
            System.out.println(";lsakdgjj;asldlkgh");
        }
    }
}
