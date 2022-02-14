/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import Math.Vector2D;
import State.GameState;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author KelvinOjeda
 */

public abstract class MovingObject extends GameObject {
    /* Attributes */
    
    /* Velocity is set as a 2D Vector */
    protected Vector2D velocity;
    
    /* Rotates the ship */
    protected AffineTransform at;
    
    /* All moving objects have an angle to set the ship direction */
    protected double angle; 
    protected double maxVelocity;
    protected int width;
    protected int height;
    
    /* Game State object */
    protected GameState gameState;
    
    /* Constructor */
    public MovingObject(Vector2D position, Vector2D velocity, 
            double maxVelocity,BufferedImage texture, 
            GameState gameState) {
        super(position, texture);

        this.velocity=velocity;
        this.maxVelocity= maxVelocity;
        this.gameState = gameState;
        width= texture.getWidth();
        height= texture.getHeight();
        angle = 0;
    }
    
    /* Methods */
    /* Obtains the center of the desired game object */
    protected Vector2D getCenter(){
        /* Local attributes */
        double posX = position.getX() + width / 2;
        double posY = position.getY() + height / 2;
        
        /* Returns the center */
        return new Vector2D(posX, posY);
    }
    
    /* Colliding method */
    protected void collidesWith(){
        /* Attributes */
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();
        double distance;
        
        /* Index pointer */
        int i = 0;
        
        for(i = 0; i < movingObjects.size(); i++){
            MovingObject myGameObject = movingObjects.get(i);
            
            /* A game object cannot collide itself */
            if(myGameObject.equals(this)){
                continue;
            }
            
            /* Calculates the distance between the object */
            distance = 
                myGameObject.
                getCenter().
                subtract(getCenter()).
                getMagnitude();
            
            /* Condition statement so objects can collide */
            if(
                distance < myGameObject.width / 2 +  width / 2
                    && movingObjects.contains(this)
            ) {
                objectCollition(myGameObject, this);
            }
        }
    }
    
    /* Destroys both of the asteroids */
    private void objectCollition(MovingObject firstO, MovingObject secondO){
        //Check if the ship is blinking 
        if(firstO instanceof Player && ((Player)firstO).isSpawning()) {
			return;
		}
		if(secondO instanceof Player && ((Player)secondO).isSpawning()) {
			return;
		}
                
        if(!(firstO instanceof Asteroid && secondO instanceof Asteroid)){
            /* Plays the explosion animation */
            gameState.playExplosion(getCenter());
            
            firstO.destroy();
            secondO.destroy();
        }
    }
    /* Removes the game object if this collides */
    protected void destroy(){
        gameState.getMovingObjects().remove(this);
    }
    
    /* Update method */
    @Override
    public void update() {
        
    }
    
    /* Draw method */
    @Override
    public void draw(Graphics g) {
        
    }
    
}
