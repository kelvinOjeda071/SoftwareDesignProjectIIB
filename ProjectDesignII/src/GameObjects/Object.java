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

/**
 *
 * @author KelvinOjeda
 */

public abstract class Object extends GameObject {
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
    public Object(Vector2D position, Vector2D velocity, 
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
    
    /* Update method */
    @Override
    public void update() {
        
    }
    
    /* Draw method */
    @Override
    public void draw(Graphics g) {
        
    }
    
}
