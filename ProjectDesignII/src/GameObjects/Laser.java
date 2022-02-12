/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import java.awt.Graphics;
import Math.Vector2D;
import State.GameState;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathan Puglla
 */
public class Laser extends Object{
    
    /* Constructor */
    public Laser(
            Vector2D position, 
            Vector2D velocity, 
            double maxVelocity, 
            double angle, 
            BufferedImage texture, 
            GameState gameState
    ) {
        /* Parent attributes */
        super(position, velocity, maxVelocity, texture, gameState);
        
        /* Sets the attributes of the constructor */
        this.angle = angle;
        this.velocity = velocity.scale(maxVelocity);
    }
    
    /* Methods */
    
    /* Update the position */
    @Override
    public void update() {
        /* Attributes */
        position = position.add(velocity);
      
        /* Deletes the laser */
        if
        (     
               position.getX() < 0 
            || position.getY() < 0 
            || position.getX() > Constants.WIDTH 
            || position.getY() > Constants.HEIGHT
        ) {
            /* Removes the laser */
            gameState.getMovingObjects().remove(this);
        }
    }
    
    /* Draws the object */
    @Override
    public void draw(Graphics g) {
        /* Graphics attribute */
        Graphics2D g2d = (Graphics2D)g;
        
        /* Position transformation*/
        at = AffineTransform.getTranslateInstance(
                position.getX() - width / 2, 
                position.getY()
        );
        
        /* Rotation */
        at.rotate(angle, width / 2, 0);
        
        /* Draws the texture */
        g2d.drawImage(texture, at, null);
    }
}
