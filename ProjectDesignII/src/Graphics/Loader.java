package Graphics;

import java.awt.Font;
import java.awt.FontFormatException;
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
    /*Font is create*/
    public static Font loadFont(String path, int size) {
		try {
                    return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {				
                    e.printStackTrace();
               	    return null;
		}
	}
}
