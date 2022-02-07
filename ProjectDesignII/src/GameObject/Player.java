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

    private Vector2D heading;// The direction vector
    private Vector2D acceleration; // The variation of velocity with respect to time
    private final double ACC=0.2; // The scale of acceleration to gt the acceleraion vector
    private final double DELTAANGLE = 0.1;
    
    public Player(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture) {
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
        
        velocity = velocity.add(acceleration); //Acceleration is the variation of the velocity
        velocity.limit(maxVelocity); //Limited the velocity vector
        
        heading=heading.setDirection(angle-Math.PI/2);
        
        position=position.add(velocity); //Velocity is the variation of the position
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        at= AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle,Assets.player.getWidth()/2, Assets.player.getHeight()/2); //Rotate around the center, that why we get the width and height /2
        
        g2d.drawImage(Assets.player, at, null );
    }
    
}
