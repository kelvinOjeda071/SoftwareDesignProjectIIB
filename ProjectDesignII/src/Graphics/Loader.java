package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * @author KelvinOjeda
 */
public class Loader {
     //Is the form that java save image into cache
    public static BufferedImage ImageLoader(String path){
        try {
        return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException ex) {
            // Return whatever error in the proccess to update the image
           ex.printStackTrace();
        }
        return null;
    }
}
