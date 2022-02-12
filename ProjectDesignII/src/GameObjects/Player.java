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
    
    /* Direction vector */
    private Vector2D heading;

    /* Variation of acceleration */
    private Vector2D acceleration; 
    private boolean accelerating = false;
    
    /* Delta Time fixing */
    private Chronometer fireRate;
    
    /* Constructor */
    public Player(
        Vector2D position, 
        Vector2D velocity, 
        double maxVelocity, 
        BufferedImage texture,
        GameState gameState
    ) {
        /* Parent attributes */
        super(position, velocity, maxVelocity, texture, gameState);
        
        /* Sets the object attributes */
        fireRate = new Chronometer();
        heading= new Vector2D(0, 1);
        acceleration = new Vector2D();
    }
    
    /* Methods */
    @Override
    public void update() {
        /* Shooting effect */
        if(KeyBoard.SHOOT &&  !fireRate.isRunning()){
            gameState.getMovingObjects().add(
                0, 
                new Laser(
                    getCenter().add(heading.scale(width)), 
                    heading, 
                    Constants.LASER_VEL, 
                    angle,
                    Assets.greenLaser, 
                    gameState
                )
            );
            
            /* Running */
            fireRate.run(Constants.FIRERATE);
        }
        
        /* Space ship moves to the RIGHT */
        if(KeyBoard.RIGHT){
            angle += Constants.DELTAANGLE;
        }
        
        /* Space ship moves to the LEFT */
        if(KeyBoard.LEFT) {
            angle -= Constants.DELTAANGLE;
        }
        
        /* Space ship moves to the TOP */
        if(KeyBoard.UP){
            /* Moves the ship */
            acceleration = heading.scale(Constants.ACC);
            accelerating = true;
            
        }else{
            if(velocity.getMagnitude() != 0){
                acceleration = (
                    velocity.scale(-1).normalize()
                ).scale(Constants.ACC/2);
                
                /* Ship is not moving */
                accelerating = false;
            }
        }
        
        /* Acceleration is the velocity variation */
        velocity = velocity.add(acceleration); 
        velocity = velocity.limit(maxVelocity);
        heading = heading.setDirection(angle - Math.PI / 2);
        
        /* Velocity is the position variation */
        position = position.add(velocity);
        
        /* Sets the movement limit across the SCREEN DIMENSIONS */
        if(position.getX() > Constants.WIDTH) {
            position.setX(0);
        }
            
        if(position.getY() > Constants.HEIGHT) {
            position.setY(0);
        }
            
        if(position.getX() < 0) {
           position.setX(Constants.WIDTH); 
        }
            
        if(position.getY() < 0) {
            position.setY(Constants.HEIGHT);
        }
        
        /* Updates the chronometer */
        fireRate.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        
        
        /* Effects */
        AffineTransform at1 = AffineTransform.getTranslateInstance(
                position.getX() + width / 2 + 5,
                position.getY() + height / 2 + 10
        );
        
        AffineTransform at2 = AffineTransform.getTranslateInstance(
                position.getX() + 5, 
                position.getY() + height / 2 + 10
        );
        
        at1.rotate(angle, -5, -10);
        at2.rotate(angle, width/2 -5, 0-10);
        
        if(accelerating == true){
            g2d.drawImage(Assets.speed, at1, null);
            g2d.drawImage(Assets.speed, at2, null);
        }
        
        at= AffineTransform.getTranslateInstance(
            position.getX(), position.getY()
        );
        
        /* Allows the center rotation of the ship */
        at.rotate(angle, width / 2, height / 2); 
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
