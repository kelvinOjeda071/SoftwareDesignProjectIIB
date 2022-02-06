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
    public static BufferedImage ImageLoader(String path){ //Is the form that java save image into cache
        try {
        return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException ex) {
           ex.printStackTrace();// Return whatever error in the proccess to update the image
        }
        return null;
    }
}
