/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;

import Graphics.Assets;
import Input.KeyBoard;
import Math.Vector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author KelvinOjeda
 */
public class Player extends Object{
    // The direction vector
    private Vector2D heading;
    //The variation of velocity with respect to time
    private Vector2D acceleration; 
    // The scale of acceleration to get the acceleration vector
    private final double ACC=0.2; 
    private final double DELTAANGLE = 0.1;
    
    public Player(Vector2D position, Vector2D velocity, 
            double maxVelocity, BufferedImage texture) {
        super(position, velocity, maxVelocity, texture);
        heading= new Vector2D(0,1);
        acceleration = new Vector2D();
    }

    @Override
    public void update() {
        if(KeyBoard.RIGHT)
            angle += DELTAANGLE;
        if(KeyBoard.LEFT)
            angle -= DELTAANGLE;
        if(KeyBoard.UP){
            acceleration=heading.scale(ACC);
        }else{
            if(velocity.getMagnitude()!=0){
                acceleration=(velocity.scale(-1).normalize()).scale(ACC/2);
            }
        }
        //Acceleration is the variation of the velocity
        velocity = velocity.add(acceleration); 
        //Limited the velocity vector
        velocity.limit(maxVelocity); 
        
        heading=heading.setDirection(angle-Math.PI/2);
        //Velocity is the variation of the position
        position=position.add(velocity); 
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        at= AffineTransform.getTranslateInstance(position.getX(), 
                position.getY());
        //Rotate around the center, that why we get the width and height /2
        at.rotate(angle,Assets.player.getWidth()/2,Assets.player.getHeight()/2); 
        
        g2d.drawImage(Assets.player, at, null );
    }
    
}
