/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import Math.Vector2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author KelvinOjeda
 */
public abstract class GameObject {
    /* Attributes */
    protected BufferedImage texture;    // the texture is a buffered image
    protected Vector2D position;        // the position is a vector
    
    /* Constructor */
    public GameObject(Vector2D position, BufferedImage texture){
        /* Defines its attributes */
        this.position = position;
        this.texture = texture;
    }
    
    /* Methods */
    
    /* Update method */
    public abstract void update();
    
    /* Draw method */
    public abstract void draw(Graphics g);
    
    /* Get */
    
    /* Obtains the position of the desired object */
    public Vector2D getPosition() {
        return position;
    }
    
    /* Set */
    
    /* Locates the position vector */
    public void setPosition(Vector2D position) {
        this.position = position;
    }
    
}
