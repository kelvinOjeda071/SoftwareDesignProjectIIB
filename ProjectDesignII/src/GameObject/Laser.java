/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;

import java.awt.Graphics;
import Math.Vector2D;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathan Puglla
 */
public class Laser extends Object{
    
    /* Constructor */
    public Laser(Vector2D position, Vector2D velocity, 
            double maxVelocity, double angle, BufferedImage texture) {
        super(position, velocity, maxVelocity, texture);
        this.angle = angle;
        this.velocity = velocity.scale(maxVelocity);
    }
    
    /* Method */
    /* Update the position */
    @Override
    public void update() {
        position = position.add(velocity);
    }
    
    /* Draws the object */
    @Override
    public void draw(Graphics g) {
        /* Graphics attribute */
        Graphics2D g2d = (Graphics2D)g;
        
        /* Position transformation*/
        at = AffineTransform.getTranslateInstance(position.getX(), 
                position.getY());
        
        /* Rotation */
        at.rotate(angle);
        
        /* Draws the texture */
        g2d.drawImage(texture, at, null);
    }
}
