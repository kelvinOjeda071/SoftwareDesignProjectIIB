package Graphics;

import java.awt.image.BufferedImage;

/*
 * @author KelvinOjeda
 */
public class Assets {
    
    //Ship
    public static BufferedImage player;
    
    //effects
    public static BufferedImage speed;
    
    /* Lasser */
    public static BufferedImage greenLaser;
    
    public static void init(){
        player = Loader.ImageLoader("/Figure/Ships/player.png");
        speed= Loader.ImageLoader("/Figure/Effects/fire01.png");
        /* Laser */
        greenLaser = Loader.ImageLoader("/Figure/Effects/laserGreen.png");
        
    }
}
