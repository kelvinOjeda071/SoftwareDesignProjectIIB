/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import Graphics.Assets;
import Input.KeyBoard;
import Main.Window;
import Math.Vector2D;
import State.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author KelvinOjeda
 */
public class Player extends Object{
    /* Attributes */
    
    // The direction vector
    private Vector2D heading;
    
    //The variation of velocity with respect to time
    private Vector2D acceleration; 
    
    private boolean accelerating = false;
    
    /* Fixing FPS shooting */
    private long time;
    private long lastTime;
    
    public Player(Vector2D position, Vector2D velocity, 
            double maxVelocity, BufferedImage texture,
            GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        
        heading= new Vector2D(0, 1);
        acceleration = new Vector2D();
        
        /* Time and last Time fixing */
        time = 0;
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        /* Time updates */
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        /* Shooting effect */
        if(KeyBoard.SHOOT && time > Constants.FIRERATE){
            gameState.getMovingObjects().add(0, new Laser(
                    getCenter().add(heading.scale(width)), 
                    heading, Constants.LASER_VEL, angle, Assets.greenLaser, 
                    gameState));
            
            /* Sets time to 0 */
            time = 0;
        }
        
        if(KeyBoard.RIGHT)
            angle += Constants.DELTAANGLE;
        if(KeyBoard.LEFT)
            angle -= Constants.DELTAANGLE;
        if(KeyBoard.UP){
            acceleration=heading.scale(Constants.ACC);
            accelerating=true;
        }else{
            if(velocity.getMagnitude()!=0){
                acceleration=(velocity.scale(-1).normalize()).scale(
                        Constants.ACC/2);
                accelerating=false;
            }
        }        
        velocity = velocity.add(acceleration); //Acceleration is the variation of the velocity
        
        velocity = velocity.limit(maxVelocity); //Limited the velocity vector
        
        heading=heading.setDirection(angle-Math.PI/2);
        
        position=position.add(velocity); //Velocity is the variation of the position
        
        if(position.getX() > Constants.WIDTH)
            position.setX(0);
        if(position.getY() > Constants.HEIGHT)
            position.setY(0);
        if(position.getX() < 0)
            position.setX(Constants.WIDTH);
        if(position.getY() < 0)
            position.setY(Constants.HEIGHT);
            
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        
        
        /* Effects */
        AffineTransform at1 = AffineTransform.getTranslateInstance(
                position.getX()+width/2 + 5, position.getY()+height/2 +10);
        
        AffineTransform at2 = AffineTransform.getTranslateInstance(
                position.getX() +5, position.getY()+height/2 +10);
        at1.rotate(angle,-5, -10);
        at2.rotate(angle, width/2 -5, 0-10);
        
        if(accelerating==true){
            g2d.drawImage(Assets.speed, at1, null);
            g2d.drawImage(Assets.speed, at2, null);
        }
        
        at= AffineTransform.getTranslateInstance(
                position.getX(), position.getY());
        
        //Rotate around the center, that why we get the width and height /2
        at.rotate(angle,width/2, height/2); 
        g2d.drawImage(Assets.player, at, null );
    }
    
    /* Obtains the center of the space ship */
    public Vector2D getCenter(){
        /* Local attributes */
        double posX = position.getX() + width / 2;
        double posY = position.getY() + height / 2;
        
        /* Returns the center */
        return new Vector2D(posX, posY);
    }
    
}
