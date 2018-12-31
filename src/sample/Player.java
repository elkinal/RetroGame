package sample;

import javafx.scene.image.Image;

/**
 * Created by alxye on 29-Oct-18.
 */
public class Player extends Entity {
    private int scoreMod = 1;
    private float score;
    private float velX;
    private float velY;
    private float maxSpeed = 5.0f;
    private float recoil = 10.0f;
    private SelectedWeapon selectedWeapon = SelectedWeapon.RIFLE;
    private long lifespan = 0;

    public Player(float x, float y, float width, float height, Image image, float speed) {
        super(x, y, width, height, image, speed);
    }

    //Score methods
    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void addScore(float score) {
        this.score += score;
    }

    //X velocity methods
    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void addVelX(float velX) {
        if(this.velX + velX <= maxSpeed && this.velX + velX >= -maxSpeed) this.velX += velX;
    }

    //Y velocity methods
    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void addVelY(float velY) {
        if(this.velY + velY <= maxSpeed && this.velY + velY >= -maxSpeed) this.velY += velY;
    }

    //recoil getters and setters
    public float getRecoil() {
        return recoil;
    }

    public void setRecoil(float recoil) {
        this.recoil = recoil;
    }

    //getters and setters for the SelectedWeapon enum


    public SelectedWeapon getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setSelectedWeapon(SelectedWeapon selectedWeapon) {
        this.selectedWeapon = selectedWeapon;
    }

    //resetting the player's position when he dies
    public void resetPosition() {
        setX(Main.SCREENWIDTH/2 - 35/2);
        setY(Main.SCREENHEIGHT/2 - 35/2);
    }
    //This runs every frame
    public void tick() {
        addX((float) (Main.deltaTime/10 * getVelX()));
        addY((float) (Main.deltaTime/10 * getVelY()));
//        setSize(getY() * 1/(Main.SCREENWIDTH) * 200, getY() * 1/(Main.SCREENWIDTH) * 200);
        //checking if the player has gone out of bounds
        if(getX() + getWidth() > Main.SCREENWIDTH)
            setX(0);
        if(getX() < 0)
            setX(Main.SCREENWIDTH - getWidth()+1);
        if(getY() < 50)
            setY(50);
        if(getY() > Main.SCREENHEIGHT - getHeight())
            setY(Main.SCREENHEIGHT - getHeight());

    }

    public float getScoreMod() {
        return scoreMod;
    }

    public void setScoreMod(int scoremod) {
        this.scoreMod = scoremod;
    }

    public void shrink() {
        setSize(30, 30);
    }
    public void expand() {
        setSize(60, 60);
    }




    public void increaseDamage() {
        Guns.DAMAGE_MOD += 0.5;
    }
}
