/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author KelvinOjeda
 */
public class Loader {
    public static BufferedImage ImageLoader(String path){ //Permite cargar imagenes en memoria
        try {
        return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        return null;
    }
}
