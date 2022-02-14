/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import Graphics.Asset;
import Math.Vector2D;
import State.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathan Puglla
 */
public class Asteroid extends MovingObject{
    /* Attributes */
    private Size size;
    
    /* Constructor */
    public Asteroid(
        Vector2D position, 
        Vector2D velocity, 
        double maxVelocity, 
        BufferedImage texture, 
        GameState gameState,
        Size size
    ) {
        /* Parent attributes */
        super(position, velocity, maxVelocity, texture, gameState);
        
        /* Meteor is destroyed */
        this.size = size;
        this.velocity = velocity.scale(maxVelocity);
    }
    
    /* Methods */
    
    /* Update the asteroid status */
    @Override
    public void update() {
        /* Position attribute */
        position = position.add(velocity);
        
        /* Sets the movement limit across the SCREEN DIMENSIONS */
        if(position.getX() > Constant.WIDTH) {
            position.setX(-width);
        }
            
        if(position.getY() > Constant.HEIGHT) {
            position.setY(-height);
        }
            
        if(position.getX() < -width) {
           position.setX(Constant.WIDTH); 
        }
            
        if(position.getY() < -height) {
            position.setY(Constant.HEIGHT);
        }
        
        /* Angle rotation */
        angle += Constant.DELTAANGLE / 2;
    }
    
    /* Destroys the asteroid */
    @Override
    public void destroy(){
        gameState.divideAsteroid(this);
        gameState.addScore(Constant.ASTEROID_SCORE, position);
        super.destroy();
    }
    
    /* Draws the asteroid */
    @Override
    public void draw(Graphics g){
        /* Graphics object */
        Graphics2D g2d = (Graphics2D)g;
        
        /* Translation of the object */
        at= AffineTransform.getTranslateInstance(
            position.getX(), position.getY()
        );
        
        /* Allows the center rotation of the ship */
        at.rotate(angle, width / 2, height / 2); 
        g2d.drawImage(texture, at, null );
    }
    
    /* Get */
    
    /* Size */
    public Size getSize() {
        return size;
    }
}
