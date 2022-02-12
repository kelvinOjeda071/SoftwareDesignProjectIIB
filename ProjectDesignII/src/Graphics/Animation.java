/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graphics;

import Math.Vector2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathan Puglla
 */
public class Animation {
    /* Attributes */
    private BufferedImage[] frames;
    private int velocity;
    private boolean isRunning;
    private int index;
    private Vector2D position;
    
    /* Local Chronometer */
    private long time;
    private long lastTime;
    
    /* Constructor */
    public Animation(BufferedImage[] frames, int velocity, Vector2D position) {
        this.frames = frames;
        this.velocity = velocity;
        this.position = position;
        index = 0;
        isRunning = true;
        time = 0;
        lastTime = System.currentTimeMillis();
    }
    
    /* Methods */
    
    /* Updates the frames within the given velocity */
    public void update(){
        /* Delta Timing */
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        /* Changes the current frame */
        if(time > velocity){
            /* Sets the attributes values */
            time = 0;
            index ++;
            
            /* Changes the running status */
            if(index >= frames.length){
                isRunning = false;
            }
        }
    }
    
    /* Returns the position of the frame index */
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    /* Get */
    
    /* Returns the position */
    public boolean isIsRunning() {
        return isRunning;
    }
    
    /* Returns the frame position */
    public Vector2D getPosition() {
        return position;
    }
}
