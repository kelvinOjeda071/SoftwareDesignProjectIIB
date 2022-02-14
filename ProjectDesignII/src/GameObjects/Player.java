/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import Graphics.Asset;
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
public class Player extends MovingObject{
    /* Attributes */
    
    /* Direction vector */
    private Vector2D heading;

    /* Variation of acceleration */
    private Vector2D acceleration; 
    private boolean accelerating = false;
    
    /* Delta Time fixing */
    private Chronometer fireRate;
    /*Determine the period of time in which the player will be spawning */
    private boolean spawning; 
    //know when to draw or not to draw the ship
    private boolean visible;
    //control the change between visible and not visible
    private Chronometer spawnTime, flickerTime;
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
        spawnTime = new Chronometer();
        flickerTime = new Chronometer();
    }
    
    /* Methods */
    @Override
    public void update() {
        //Initialize values when the ship is not spawning
        if(!spawnTime.isRunning()) {
			spawning = false;
			visible = true;
		}
        //produce the flickering effect
	if(spawning) {	
            if(!flickerTime.isRunning()) {
		flickerTime.run(Constant.FLICKER_TIME);
		visible = !visible;
            }	
	}
        /* Shooting effect */
        if(KeyBoard.SHOOT &&  !fireRate.isRunning() && !spawning){
            gameState.getMovingObjects().add(0, 
                new Laser(
                    getCenter().add(heading.scale(width)), 
                    heading, 
                    Constant.LASER_VEL, 
                    angle,
                    Asset.greenLaser, 
                    gameState
                )
            );
            
            /* Running */
            fireRate.run(Constant.FIRERATE);
        }
        
        /* Space ship moves to the RIGHT */
        if(KeyBoard.RIGHT){
            angle += Constant.DELTAANGLE;
        }
        
        /* Space ship moves to the LEFT */
        if(KeyBoard.LEFT) {
            angle -= Constant.DELTAANGLE;
        }
        
        /* Space ship moves to the TOP */
        if(KeyBoard.UP){
            /* Moves the ship */
            acceleration = heading.scale(Constant.ACC);
            accelerating = true;
            
        }else{
            if(velocity.getMagnitude() != 0){
                acceleration = (
                    velocity.scale(-1).normalize()
                ).scale(Constant.ACC/2);
                
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
        if(position.getX() > Constant.WIDTH) {
            position.setX(0);
        }
            
        if(position.getY() > Constant.HEIGHT) {
            position.setY(0);
        }
            
        if(position.getX() < 0) {
           position.setX(Constant.WIDTH); 
        }
            
        if(position.getY() < 0) {
            position.setY(Constant.HEIGHT);
        }
        
        /* Updates the chronometer */
        fireRate.update();
        spawnTime.update();
        flickerTime.update();
        
        /* Detects a collition */
        collidesWith();
       
        
    }
    
    /*Cuando se destruya reaparezca pero titilando*/
    @Override
    public void destroy() {
        //ship starts flashing
	spawning = true;
	spawnTime.run(Constant.SPAWNING_TIME);
        //Reset values position
	resetValues();
        //remove a player attempt
        gameState.subtractLife();
	}
    //reset ship position values
    private void resetValues() {	
		angle = 0;
		velocity = new Vector2D();
		position = new Vector2D(Constant.WIDTH/2 - Asset.player.getWidth()/2, Constant.HEIGHT/2 - Asset.player.getHeight()/2);
	}
    @Override
    public void draw(Graphics g) {
        //en el caso de no ser visible no se dibuje
        if (!visible){
            return;
        }
        /* Sets the graphics object */
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
            g2d.drawImage(Asset.speed, at1, null);
            g2d.drawImage(Asset.speed, at2, null);
        }
        
        at= AffineTransform.getTranslateInstance(
            position.getX(), position.getY()
        );
        
        /* Allows the center rotation of the ship */
        at.rotate(angle, width / 2, height / 2); 
        g2d.drawImage(texture, at, null );
    }
    //Para que los demas objetos no disparen
    public boolean isSpawning() {
        return spawning;
    }
}
