package Graphics;

import java.awt.image.BufferedImage;

/*
 * @author KelvinOjeda
 */
public class Assets {
    public static BufferedImage player;
    public static void init(){
        player = Loader.ImageLoader("/Figure/Ships/player.png");
    }
}
