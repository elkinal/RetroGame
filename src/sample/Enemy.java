package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by alxye on 29-Oct-18.
 */
public class Enemy extends Entity implements Hostile {

    private float health;
    private float maxSize;

    private float radianMod;
    private boolean expendable;

    private long bulletTicks = 0;

    Image[] bulletImages = new Image[2];

    private float initHealth;
    private long tickCount = 5;
    public boolean spawners = false;
    public boolean guns = false;
    private EnemyType enemyType;
    private boolean initPos = false;
    float angle;

    public Enemy(float x, float y, float width, float height, EnemyType enemyType) {
        super(x, y, width, height, null, 1.0f);


        //The bullets are having issues with spawning because they spawn at (x,y) (0, 0) of the enemy

        //initialize the images available to enemies


        //This will work for all of the default enemy types
        maxSize = 200;
        //creating different enemy types
        if(enemyType == EnemyType.POWERUP) {
            setImage(Main.display.powerUpImages[0]);
            setSpeed(0);
            this.health = 100;
        }
        if(enemyType == EnemyType.NORMAL) {
            setImage(Main.display.enemyImages[0]);
            setSpeed(2.0f);
            this.health = 15;
        }
        if(enemyType == EnemyType.FAST) {
            setImage(Main.display.enemyImages[1]);
            setSpeed(3.0f);
            this.health = 10;
        }
        if(enemyType == EnemyType.TOUGH) {
            setImage(Main.display.enemyImages[2]);
            setSpeed(1.5f);
            this.health = 20;
        }
        if(enemyType == EnemyType.OBESE) {
            setImage(Main.display.enemyImages[3]);
            setSpeed(1.0f);
            this.health = 100;
            spawners = true;
        }
        if(enemyType == EnemyType.KAMIKAZE) {
            setImage(Main.display.enemyImages[4]);
            setSpeed(4.0f);
            this.health = 1;
        }
        if(enemyType == EnemyType.BEHEMOTH) {
            setImage(Main.display.enemyImages[5]);
            setSpeed(0.5f);
            this.health = 1000;
            spawners = true;
        }
        if(enemyType == EnemyType.SHOOTER) {
            setImage(Display.bossImages[0]);
            setSpeed(Main.rand(1.0f, 3.5f));
            maxSize = 100;
            this.health = 100;
            guns = true;
        }
        if(enemyType == EnemyType.BOSS) {
            if(Main.bossNumber == 1) {
                setImage(Display.bossImages[0]);
                setSpeed(2.45f);
                this.health = 500;
                guns = true;
                maxSize = 400;
            }
            if(Main.bossNumber == 2) {
                setImage(Display.bossImages[1]);
                setSpeed(2.45f);
                this.health = 700;
                spawners = true;
                maxSize = 300;
            }
            if(Main.bossNumber == 3) {
                setImage(Display.bossImages[2]);
                setSpeed(5.0f);
                this.health = 700;
                maxSize = 100;
            }
            if(Main.bossNumber == 4) {
                setImage(Display.bossImages[3]);
                setSpeed(3.0f);
                this.health = 700;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 5) {
                setImage(Display.bossImages[4]);
                setSpeed(3.0f);
                this.health = 1000;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 6) {
                setImage(Display.bossImages[5]);
                setSpeed(1.5f);
                this.health = 2000;
                maxSize = 200;
                guns = true;
            }
            if(Main.bossNumber == 7) {
                setImage(Display.bossImages[6]);
                setSpeed(4.0f);
                this.health = 2000;
                maxSize = 400;
                guns = true;
            }
            if(Main.bossNumber == 8) {
                setImage(Display.bossImages[7]);
                setSpeed(2.0f);
                this.health = 2000;
                maxSize = 400;
                guns = true;
            }
            if(Main.bossNumber == 9) {
                setImage(Display.bossImages[8]);
                setSpeed(0.0f);
                this.health = 2500;
                maxSize = 400;
                guns = true;
            }
            if(Main.bossNumber == 10) {
                setImage(Display.bossImages[9]);
                setSpeed(0.0f);
                this.health = 3000;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 11) {
                setImage(Display.bossImages[10]);
                setSpeed(2.0f);
                this.health = 3000;
                maxSize = 500;
                spawners = true;
            }
            if(Main.bossNumber == 12) {
                setImage(Display.bossImages[11]);
                setSpeed(2.0f);
                this.health = 3000;
                maxSize = 500;
                spawners = true;
            }
            if(Main.bossNumber == 13) {
                setImage(Display.bossImages[12]);
                setSpeed(2.0f);
                this.health = 3000;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 14) {
                setImage(Display.bossImages[13]);
                setSpeed(2.0f);
                this.health = 3000;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 15) {
                setImage(Display.bossImages[14]);
                setSpeed(0);
                this.health = 4000;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 16) {
                setImage(Display.bossImages[15]);
                setSpeed(0);
                this.health = 2000;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 17) {
                setImage(Display.bossImages[16]);
                setSpeed(0);
                this.health = 4500;
                maxSize = 500;
                guns = true;
            }
            if(Main.bossNumber == 18) {
                setImage(Display.bossImages[17]);
                setSpeed(0);
                this.health = 5000;
                maxSize = 500;
                guns = true;
            }
        }

        this.enemyType = enemyType;
        initHealth = health;
    }

    //second constructor facilitates the creation of bullets
    public Enemy(float x, float y, float width, float height, float speed, float radianMod, boolean expendable) {
        super(x, y, width, height, null, 1.0f);
        setSpeed(speed);
        this.enemyType = EnemyType.BULLET;
        setBulletImages(getBulletImages());

        setWidth(10);
        setHeight(10);
        this.radianMod = radianMod;
        this.expendable = expendable;
    }

    public Image[] getBulletImages() {

        if(Main.bossNumber < 10) {
            return this.bulletImages = Display.bullets[12];
        }
        else {
            return this.bulletImages = Display.bullets[Main.bossNumber];
        }
    }

    public void setBulletImages(Image[] bulletImages) {
        this.bulletImages = bulletImages;
    }

    public void tick() {

        /**creating the illusion that the enemies are getting closer to the player
        (in reality they are simple getting larger when they are getting closer to the edge of the screen)

         This part needs to be first in order to avoid generating a retarded bullet - the getWidth methods need
         to refer to the latest possible version of the Boss's width*/
        if(enemyType != EnemyType.BULLET)
            setSize(getY() * 1/(Main.SCREENWIDTH) * maxSize + 10, getY() * 1/(Main.SCREENWIDTH) * maxSize + 10);
        //movement
        if(enemyType != EnemyType.BULLET) {
            float deltaX = Main.player.getX() + Main.player.getWidth() / 2 - this.getHeight() / 2 - getX();
            float deltaY = Main.player.getY() + Main.player.getHeight() / 2 - this.getHeight() / 2 - getY();
            float angle = (float) Math.atan2(deltaY, deltaX);

            addX((float) (Main.deltaTime / 10 * getSpeed() * Math.cos(angle)));
            addY((float) (Main.deltaTime / 10 * getSpeed() * Math.sin(angle)));

            //checking if the enemy has run out of health
            if (health <= 0) {
                Main.hostiles.remove(this);
            }


            int speedMod = 100;
            if(enemyType == EnemyType.BOSS) {
                if (Main.player.getSelectedWeapon() == SelectedWeapon.RIFLE)
                    speedMod = 30;
                if (Main.player.getSelectedWeapon() == SelectedWeapon.SHOTGUN)
                    speedMod = 10;
                if (Main.player.getSelectedWeapon() == SelectedWeapon.SNIPER)
                    speedMod = 15;
                if (Main.player.getSelectedWeapon() == SelectedWeapon.MISSILE)
                    speedMod = 5;

                if(spawners && tickCount % (speedMod*3) == 0) {
                    if(Main.bossNumber == 2)
                        Main.addEnemies(getEnemyByType(getX(), getY(), EnemyType.KAMIKAZE));
                    if(Main.bossNumber == 11)
                        Main.addEnemies(Main.addGenericEnemy(getX() + Main.rand(0, getWidth()), getY() + Main.rand(0, getHeight())));
                    if(Main.bossNumber == 12)
                        Main.addEnemies(getEnemyByType(getX(), getY(), EnemyType.SHOOTER));
                }
            }
            else if(spawners && tickCount % speedMod == 0) {
                Main.addEnemies(getEnemyByType(getX(), getY(), EnemyType.KAMIKAZE));
            }

            //boss bullet behaviors
            if(guns && tickCount % speedMod == 0 && enemyType == EnemyType.BOSS) {
                //shoot a projectile
                if(Main.bossNumber == 1) {
                    Main.addEnemies(getBullet(getX() + getWidth() / 2 - 5, getY() + getHeight() / 2 - 5, 5.0f, 0, false)); // TODO: 01-Dec-18 solution
                }
                if(Main.bossNumber == 4) {
                    for (int i = 0; i < 3; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, 0, false));
                    }
                }
                if(Main.bossNumber == 5) {
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, 0, false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, -5.0f, 0, false));
                }
                if(Main.bossNumber == 6) {
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, 0, false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, -5.0f, 0, false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, (float) (Math.PI/2), false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, -5.0f, (float) (Math.PI/2), false));
                }
                if(Main.bossNumber == 7) {
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 0, 0, true));
                }
                if(Main.bossNumber == 8) {
                    for (int i = 1; i < 6; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, (float) (Math.PI/2.5)*i, false));
                    }
                }
                if(Main.bossNumber == 9) {
                    for (int i = 1; i < 7; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, (float) (Math.PI/3)*i, false));
                    }
                }
                if(Main.bossNumber == 10) {
                    for (int i = 1; i < 9; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, (float) (Math.PI/4)*i, false));
                    }
                }
                if(Main.bossNumber == 14) {
                    for (int i = 1; i < 9; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, (float) (Math.PI/4)*i, false));
                    }
                }
                if(Main.bossNumber == 15) {
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*1.05), false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*1), false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*0.95), false));
                }
                if(Main.bossNumber == 16) {
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*1.05), false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*1.03), false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*1), false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*0.97), false));
                    Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) ((Math.PI * 2)*0.94), false));
                }
                if(Main.bossNumber == 17) {
                    for (int i = -1; i <= 28; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 10.0f, (float) (Math.PI/18)*i, false));
                    }
                }
                if(Main.bossNumber == 18) {
                    for (int i = 1; i < 13; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, Main.rand(5, 10), (float) (Math.PI/6)*i, false));
                    }
                }


            }
            else if(enemyType == EnemyType.SHOOTER && tickCount % speedMod == 0) {
                Main.addEnemies(getBullet(getX() + getWidth()/2 - 5, getY() + getHeight()/2 - 5, 5.0f, 0, false));
            }
            if(guns && tickCount % (speedMod*30) == 0) {
                if(Main.bossNumber == 13) {
                    for (int i = -3; i < 80; i++) {
                        Main.addEnemies(getBullet(getX() + getWidth() / 2 - 5, getY() + getHeight() / 2 - 5, 10.0f, (float) (Math.PI / 50) * i, false));
                    }
                }
            }

        } else {
            if(!initPos) {
                //need to initialize these variables here to avoid any errors. These values won's actually ever be used
                float targetX = 0;
                float targetY = 0;


                if(Main.bossNumber == 4) {
                    targetX = Main.player.getX() + Main.rand(-100, 100);
                    targetY = Main.player.getY() + Main.rand(-100, 100);
                }
                else {
                    targetX = Main.player.getX();
                    targetY = Main.player.getY();
                }

                float deltaX = targetX - getX();
                float deltaY = targetY - getY();
                angle = (float) Math.atan2(deltaY, deltaX);
                //aiming bullets perpendicular to the enemy is useful in the creation of certain bosses
                angle += radianMod;

                initPos = true;
            }

            this.addX((float) (Main.deltaTime/10 * getSpeed() * Math.cos(angle)));
            this.addY((float) (Main.deltaTime/10 * getSpeed() * Math.sin(angle)));

            if (this.getX() < -getWidth() || this.getX() > Main.SCREENWIDTH || this.getY() < -getWidth() || this.getY() > Main.SCREENHEIGHT) {
                Main.hostiles.remove(this);
            }

            if(tickCount > 500)
                Main.hostiles.remove(this);
        }

            //experimental development
            tickCount++;

    }

    private Enemy getBullet(float sX, float sY, float bulletSpeed, float radianMod, boolean expendable) {
        return new Enemy(sX, sY , 50, 50, bulletSpeed, radianMod, expendable);
    }

    public static int[] generateNewPosition() {
        if(Main.rand(0,1) == 0) {
            return new int[] {(int) (Main.rand(0, Main.SCREENWIDTH) - 50), 0};
        }
        else {
            return new int[] {(int) (Main.rand(0, Main.SCREENHEIGHT) - 50), 0};
        }
    }


    public Enemy getEnemyByType(float x, float y, EnemyType enemyType) {
        return new Enemy(x, y, 50, 50, enemyType);
    }

    public void draw(GraphicsContext graphics) {
        graphics.drawImage(this.getImage(), getX(), getY(), getWidth(), getHeight());

        //implement a health-bar above each enemy
        if(health < initHealth && enemyType != EnemyType.BULLET) {
            graphics.setFill(Color.BLACK);
            graphics.fillRect(getX()-1, getY()-1, getWidth(), 5);
            float healthRatio = (health / initHealth);
            if(healthRatio < 0.3)
                graphics.setFill(Color.DEEPPINK);
            else if(healthRatio >= 0.3 && healthRatio < 0.7)
                graphics.setFill(Color.BLUEVIOLET);
            else
                graphics.setFill(Color.CYAN);
            graphics.fillRect(getX(), getY(), healthRatio * getWidth(), 3);
        }
        if(enemyType == EnemyType.BULLET) {
            bulletTicks++;
            if(Main.bossNumber == 18) {
                if (bulletTicks % 40 > 29)
                    graphics.drawImage(getBulletImages()[0], getX(), getY(), getWidth(), getHeight());
                if (bulletTicks % 40 <= 29 && bulletTicks % 40 > 19)
                    graphics.drawImage(getBulletImages()[1], getX(), getY(), getWidth(), getHeight());
                if (bulletTicks % 40 <= 19 && bulletTicks % 40 > 9)
                    graphics.drawImage(getBulletImages()[2], getX(), getY(), getWidth(), getHeight());
                if (bulletTicks % 40 <= 9)
                    graphics.drawImage(getBulletImages()[3], getX(), getY(), getWidth(), getHeight());

            }
            else {
                if (bulletTicks % 20 > 9)
                    graphics.drawImage(getBulletImages()[0], getX(), getY(), getWidth(), getHeight());
                else
                    graphics.drawImage(getBulletImages()[1], getX(), getY(), getWidth(), getHeight());
            }
        }
//        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public void removeHealth(float damage) {
        this.health -= damage;
    }

    public float getHealth() {
        return health;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
