package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alxye on 29-Oct-18.
 */
public class Display {
    private Image gunSelector;
    private Image weaponImages;

    public Image rifleBullet;
    public Image shotgunBullet;
    public Image sniperBullet;
    public Image missileBullet;

    public Image[] gameOverImages = new Image[2];
//    public Image gameBackground;
    private Image[] playingField = new Image[2];
    public Image[] powerUpImages = new Image[2];

    //Player Skins
    public Image pauseScreen;

    public boolean showHelp = false;
    private Image helpMenuImage;
    public Image cursor;

    public static Image[][] bullets = new Image[25][4];
    private Image[] startingMenuFrames = new Image[2];
    public static Image[] bossImages = new Image[21];
    private long menuTicks = 0;

    private Image[] weaponsMenu = new Image[4];
    public Image pauseMenuSelector;
    private Image[] pauseMenu = new Image[4];
    private int selectedPauseMenuItem = 0;


    public Image[] enemyImages = new Image[20];
    // STOPSHIP: 19-Nov-18 Remember to ask Mr. Huxley about unblocking GitHub and related services. Do this as quickly as possible

    public Display() {
        try {
            //Possibly redundant - consider removing this
            rifleBullet = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\guns\\rifleBullet.png"));

            //Not currently being used - only implement it if it actually looks decent
            cursor = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\cursor.png"));

            //Help Menu
            helpMenuImage = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\mainMenu\\helpMenu.png"));

            //Weapons Menu Images
            // TODO: 03-Dec-18 Consider changing these images - they are quite bad and do not fit in the with background
            weaponsMenu[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\guns\\rifle.png"));
            weaponsMenu[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\guns\\shotgun.png"));
            weaponsMenu[2] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\guns\\sniper.png"));
            weaponsMenu[3] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\guns\\rocket.png"));

            //Pause Menu Images
            pauseMenu[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\pauseMenu\\option1.png"));
            pauseMenu[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\pauseMenu\\option2.png"));
            pauseMenu[2] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\pauseMenu\\option3.png"));
            pauseMenu[3] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\pauseMenu\\option4.png"));
            // TODO: 03-Dec-18 Make this controlled by a loop - the current approach is very inefficient

            //Boss Images
            //This reads all of the images in enemies/bosses/ and fills the bossImages array with them
            // TODO: 06-Dec-18 Create an Image auto-loader
            ArrayList<Image> controlArray = new ArrayList<>();
            Files.walk(Paths.get("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses"))
                    .filter(Files::isRegularFile)
                    .forEach(p -> {
                        try {
                            controlArray.add(new Image(new FileInputStream(p.toString())));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
            bossImages = controlArray.toArray(new Image[controlArray.size()]);

            // TODO: 04-Dec-18 Add <homeDir> dependencies

            /*bossImages[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss01.png"));
            bossImages[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss02.png"));
            bossImages[2] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss03.png"));
            bossImages[3] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss04.png"));
            bossImages[4] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss05.png"));
            bossImages[5] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss06.png"));
            bossImages[6] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss07.png"));
            bossImages[7] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss08.png"));
            bossImages[8] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss09.png"));
            bossImages[9] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss10.png"));
            bossImages[10] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss11.png"));
            bossImages[11] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss12.png"));
            bossImages[12] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss13.png"));
            bossImages[13] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss14.png"));
            bossImages[14] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss15.png"));
            bossImages[15] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss16.png"));
            bossImages[16] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss17.png"));
            bossImages[17] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss18.png"));
            bossImages[18] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss19.png"));
            bossImages[19] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\bosses\\boss20.png"));*/
            // TODO: 03-Dec-18 Make this controlled by a loop - the current approach is very inefficient

            //Bullets for normal enemies
            bullets[10][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet1.png"));
            bullets[10][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet2.png"));
            bullets[12][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet3.png"));
            bullets[12][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet4.png"));
            bullets[13][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet3.png"));
            bullets[13][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet4.png"));
            bullets[14][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet5.png"));
            bullets[14][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet6.png"));
            bullets[15][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet7.png"));
            bullets[15][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet8.png"));
            bullets[16][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet9.png"));
            bullets[16][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet10.png"));
            bullets[17][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet11.png"));
            bullets[17][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet12.png"));
/*            bullets[18][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet13.png"));
            bullets[18][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet14.png"));*/

            //Bullets for the final boss at the end of the game
            bullets[18][0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet15.png"));
            bullets[18][1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet16.png"));
            bullets[18][2] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet17.png"));
            bullets[18][3] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\bullets\\bullet18.png"));




            /*playingField[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\sprite\\background1.png"));
            playingField[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\sprite\\background2.png"));
            playingField[2] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\sprite\\background3.png"));
            playingField[3] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\sprite\\background-1.png"));
            playingField[4] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\sprite\\background-2.png"));
            playingField[5] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\sprite\\background-3.png"));*/

            //enemy images
            enemyImages[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\generic\\worm.png"));
            enemyImages[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\generic\\enemy.png"));
            enemyImages[2] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\generic\\enemyFastest.png"));
            enemyImages[3] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\generic\\enemyObese.png"));
            enemyImages[4] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\generic\\kamikaze.png"));
            enemyImages[5] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\enemies\\generic\\behemoth.png"));

            startingMenuFrames[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\themes\\menuScreen1.png"));
            startingMenuFrames[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\themes\\menuScreen2.png"));


            //Power up images
            powerUpImages[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\powerUps\\sizePowerUp.png"));
            powerUpImages[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\powerUps\\minePowerUp.png"));


            pauseMenuSelector = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\pauseMenu\\pauseMenuSelector.png"));

            gameOverImages[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\themes\\gameOverScreen1.png"));
            gameOverImages[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\themes\\gameOverScreen2.png"));
//            gameBackground = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\themes\\background.png"));
            playingField[0] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\themes\\menuScreen.png"));
            playingField[1] = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\themes\\finalBoss.png"));



            gunSelector = new Image(new FileInputStream("C:\\Users\\alxye\\Desktop\\2DShooter\\selector.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void drawScore(GraphicsContext graphics) {
        graphics.setFill(Color.PALEVIOLETRED);
        graphics.setFont(new Font("Verdana", 24));
        graphics.fillText("Score " + (int)Main.player.getScore(), Main.SCREENWIDTH-170, 40);
    }

    /*public void drawCursor(GraphicsContext graphics) {
        graphics.drawImage(cursor, MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY(), 26, 26);
    }*/
    public void changeSelectedPauseMenuItem(int n) {
        if(selectedPauseMenuItem+n <= 3 && selectedPauseMenuItem+n >= 0 && Main.gamePaused)
            selectedPauseMenuItem += n;
    }
    public void setSelectedPauseMenuItem(int n) {
        selectedPauseMenuItem = n;
    }
    public int getSelectedPauseMenuItem() {
        return selectedPauseMenuItem;
    }
    public void drawText(GraphicsContext graphics, float x, float y, String text) {
        graphics.setFill(Color.PALEVIOLETRED);
        graphics.setFont(new Font("Verdana", 24));
        graphics.fillText("Score " + text, x, y);
    }

    public void drawPauseMenuOptions(GraphicsContext graphics) {
        for (int i = 0; i < pauseMenu.length; i++) {
            graphics.drawImage(pauseMenu[i], Main.SCREENWIDTH/2-200, Main.SCREENHEIGHT/2 + i * 100 - 200, 400, 100);
        }
        graphics.drawImage(pauseMenuSelector, Main.SCREENWIDTH/2 + 150, Main.SCREENHEIGHT/2 + selectedPauseMenuItem * 100 - 200, 50, 100);
    }

    public void drawGameOverScreen(GraphicsContext graphics) {
        menuTicks++;
        if(menuTicks % 20 > 9)
            graphics.drawImage(gameOverImages[0], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);
        else
            graphics.drawImage(gameOverImages[1], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);
    }

    /*public void drawGameField(GraphicsContext graphics) {
        menuTicks++;
        if(menuTicks % 20 > 9)
            graphics.drawImage(playingField[0], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);
        else
            graphics.drawImage(playingField[1], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);
    }*/
    public void drawPauseScreen(GraphicsContext graphics) {
        graphics.drawImage(pauseScreen, 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);
        drawPauseMenuOptions(graphics);
    }

    public void drawMenuScreen(GraphicsContext graphics) {
        menuTicks++;
        if(menuTicks % 20 > 9)
            graphics.drawImage(startingMenuFrames[0], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);
        else
            graphics.drawImage(startingMenuFrames[1], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);

    }



    public void drawSelectedWeapon(GraphicsContext graphics) {
        /*for (int i = 0; i < SelectedWeapon.values().length; i++) {
            graphics.fillRect(i * 150 + Main.SCREENWIDTH/2 - SelectedWeapon.values().length/2 * 150, Main.SCREENHEIGHT-150, 150, 150);
        }*/


        /*graphics.drawImage(image1, Main.SCREENWIDTH / 2 - SelectedWeapon.values().length/2 * 150, Main.SCREENHEIGHT-150, 150, 150);
        graphics.drawImage(image2, 150 + Main.SCREENWIDTH/2 - SelectedWeapon.values().length/2 * 150, Main.SCREENHEIGHT-150, 150, 150);
        graphics.drawImage(image3, 300 + Main.SCREENWIDTH/2 - SelectedWeapon.values().length/2 * 150, Main.SCREENHEIGHT-150, 150, 150);
        graphics.drawImage(image4, 450 + Main.SCREENWIDTH/2 - SelectedWeapon.values().length/2 * 150, Main.SCREENHEIGHT-150, 150, 150);
        graphics.drawImage(gunSelector, Arrays.asList(SelectedWeapon.values()).indexOf(Main.player.getSelectedWeapon()) * 150 + Main.SCREENWIDTH/2 -
                        SelectedWeapon.values().length/2 * 150,
                Main.SCREENHEIGHT-150, 150, 150);*/

        for (int i = 0; i < weaponsMenu.length; i++) {
            graphics.drawImage(weaponsMenu[i], 0, Main.SCREENHEIGHT - 400 + i * 100, 300, 100);
        }
        graphics.drawImage(pauseMenuSelector, 300, Main.SCREENHEIGHT - 400 + Arrays.asList(SelectedWeapon.values()).indexOf(Main.player.getSelectedWeapon()) * 100, 50, 100);

        /*graphics.drawImage(gunSelector, 0, Main.SCREENHEIGHT - 400 + Arrays.asList(SelectedWeapon.values()).indexOf(Main.player.getSelectedWeapon()) * 100, 300, 100);
        graphics.drawImage(weaponImages, 0, Main.SCREENHEIGHT - 400, 300, 400);*/

    }

    public void drawPlayingField(GraphicsContext graphics) {
        if(Main.bossNumber == 18)
            graphics.drawImage(playingField[1], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);
        else
            graphics.drawImage(playingField[0], 0, 0, Main.SCREENWIDTH, Main.SCREENHEIGHT);

    }



    public void drawHelp(GraphicsContext graphics) {
        if(showHelp) {
            graphics.drawImage(helpMenuImage,  Main.SCREENWIDTH/2 - 250, Main.SCREENHEIGHT/2 - 250, 500, 500);
        }
    }
}
