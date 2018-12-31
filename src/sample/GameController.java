package sample;

import java.util.ArrayList;

/**
 * Created by alxye on 11-Nov-18.
 */
public class GameController {
    //wave number + wave data + ammo ?
    public static int waveNumber = 1;
    public static ArrayList<Entity> wave1 = new ArrayList<>();
    public static ArrayList<Entity> wave2 = new ArrayList<>();
    public static ArrayList<Entity> wave3 = new ArrayList<>();
    public static final ArrayList<ArrayList<Entity>> waves = new ArrayList<>();

    static {
        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);
    }

}
