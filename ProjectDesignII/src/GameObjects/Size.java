/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package GameObjects;

import Graphics.Asset;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathan Puglla
 */
public enum Size{
    /* Asteroid destruction */
    BIG(2, Asset.meds), 
    MED(2, Asset.smalls), 
    SMALL(2, Asset.tinies), 
    TINY(0, null);
    
    /* Asteroid quantity after their destruction */
    public int quantity;
    
    /* Buffered object */
    public BufferedImage[] textures;
    
    /* Inits the asteroid */
    private Size(int quantity, BufferedImage[] textures){
        this.quantity = quantity;
        this.textures = textures;
    }
}
