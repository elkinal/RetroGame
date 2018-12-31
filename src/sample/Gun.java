package sample;

import javafx.scene.image.Image;

import java.util.Arrays;

/**
 * Created by alxye on 30-Oct-18.
 */
public class Gun {
    private String name;
    private Bullet[] bullets;
    private Image image;

    public Gun(String name, Image image ,Bullet ... bullets) {
        this.name = name;
        this.bullets = bullets;
        this.image = image;
    }

    //bullet methods
    public Bullet[] getBullet() {
        return bullets;
    }

    public void setBullet(Bullet... bullets) {
        this.bullets = bullets;
    }

    //name method
    public String getName() {
        return name;
    }

    //image methods
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }



    @Override
    public String toString() {
        return "Gun{" +
                "name='" + name + '\'' +
                ", bullets=" + Arrays.toString(bullets) +
                ", image=" + image +
                '}';
    }
}
