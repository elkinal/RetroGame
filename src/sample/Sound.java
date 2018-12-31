package sample;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.*;

/**
 * Created by alxye on 27-May-18.
 */
public class Sound {
    private static File rifleSound = new File("C:\\Users\\alxye\\Desktop\\2DShooter\\audio\\rifleSound.wav");
    private static File shotgunSound = new File("C:\\Users\\alxye\\Desktop\\2DShooter\\audio\\shotgunSound.wav");
    private static File sniperSound = new File("C:\\Users\\alxye\\Desktop\\2DShooter\\audio\\sniperSound.wav");
    private static File missileSound = new File("C:\\Users\\alxye\\Desktop\\2DShooter\\audio\\missileSound.wav");
    private static File deathSound = new File("C:\\Users\\alxye\\Desktop\\2DShooter\\audio\\deathSound.wav");
    private static File backgroundMusic = new File("C:\\Users\\alxye\\Desktop\\2DShooter\\audio\\ambiance.wav");


    public static void playSound(File file, boolean start) {
        InputStream in;
        try {
            in = new FileInputStream(file);
            AudioStream audios = new AudioStream(in);
            AudioPlayer.player.start(audios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void playRifleSound() {
        playSound(rifleSound, true);
    }
    public static void playShotgunSound() {
        playSound(shotgunSound, true);
    }
    public static void playSniperSound() {
        playSound(sniperSound, true);
    }
    public static void playMissileSound() {
        playSound(missileSound, true);
    }
    public static void playDeathSound() {playSound(deathSound, true);}
    public static void playAmbiantSound() {playSound(backgroundMusic, true);}
    public static void stopMenuSound() {playSound(backgroundMusic, false);}




}
