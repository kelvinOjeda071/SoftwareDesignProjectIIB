/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;

import Math.Vector2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author KelvinOjeda
 */
public abstract class Object extends GameObject {
    
    protected Vector2D velocity;
    protected AffineTransform at;//Helping to rotating the ship
    //All moving object has an angle to direct where the ship is going 
    protected double angle; 
    protected double maxVelocity;

    public Object(Vector2D position, Vector2D velocity, 
            double maxVelocity,BufferedImage texture) {
        super(position, texture);
        this.velocity=velocity;
        this.maxVelocity= maxVelocity;
        this.angle=0;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        
    }
    
}
