package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Created by alxye on 29-Oct-18.
 */
public class Entity {
    private float x;
    private float y;
    private float width;
    private float height;
    private Image image;
    private float speed;

    public Entity(float x, float y, float width, float height, Image image, float speed) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.image = image;
        this.speed = speed;
    }
    //X methods
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void addX(float x) {
        this.x += x;
    }

    //Y methods
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addY(float y) {
        this.y += y;
    }

    //Width and Height methods
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    //Image methods
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    //Speed methods
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    //This is needed for collision detection
    public Rectangle getCollider() {
        return new Rectangle(x, y, width, height);
    }

    public void setSize(float x, float y) {
        this.width = x;
        this.height = y;
    }


    //This is needed to display the image on the screen
    public void draw(GraphicsContext graphics) {
        graphics.drawImage(image, x, y, width, height);
    }



    //This will be updated every frame
    //This makes the enemies follow the player unless they are touching

}
