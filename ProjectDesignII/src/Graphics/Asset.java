package Graphics;

import java.awt.image.BufferedImage;

/*
 * @author KelvinOjeda
 */
public class Asset {
    
    /* Ship texture attribute */
    public static BufferedImage player;
    
    /* Movement texture attribute */
    public static BufferedImage speed;
    
    /* Laser texture attribute */
    public static BufferedImage greenLaser;
    
    /* Meteros */
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    
    /* Explosion */
    public static BufferedImage[] explosions = new BufferedImage[9];
    /* Ufo */
    public static BufferedImage ufo;
    
    public static void init(){
        /* Ship texture */
        player = Loader.ImageLoader("/Figure/Ships/player.png");
        
        /* Movement effect texture */
        speed= Loader.ImageLoader("/Figure/Effects/fire01.png");
        
        /* Laser texture */
        greenLaser = Loader.ImageLoader("/Figure/Effects/laserGreen.png");
        
        /* Loads the meteors using a for bucle */
        int x;
        
        
        
        /* Big asteroids */
        for (x = 0; x < bigs.length; x++){
            bigs[x] = Loader.ImageLoader(
                "/Figure/Asteroids/big" + (x + 1) +".png"
            );
        }
        
        /* Medium asteroids */
        for (x = 0; x < meds.length; x++){
            meds[x] = Loader.ImageLoader(
                "/Figure/Asteroids/med" + (x + 1) +".png"
            );
        }
        
        /* Small asteroids */
        for (x = 0; x < smalls.length; x++){
            smalls[x] = Loader.ImageLoader(
                "/Figure/Asteroids/small" + (x + 1) +".png"
            );
        }
        
        /* Tiny asteroids */
        for (x = 0; x < tinies.length; x++){
            tinies[x] = Loader.ImageLoader(
                "/Figure/Asteroids/tiny" + (x + 1) +".png"
            );
        }
        
        /* Explosion */
        for (x = 0; x < explosions.length; x++){
            explosions[x] = Loader.ImageLoader(
                "/Figure/Explosion/explosion" + x + ".png"
            );
        }
        /*UFO*/
        ufo = Loader.ImageLoader("/Figure/Ships/ufo.png");
        
    }
}
