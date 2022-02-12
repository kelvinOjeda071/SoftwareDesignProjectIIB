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
    /* Image is saved on Cache memory */
    public static BufferedImage ImageLoader(String path){
        
        try {
            return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException ex) {
           /* Prints the stack trace error */
           ex.printStackTrace();
        }
        
        return null;
    }
}
