/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

/**
 *
 * @author Jonathan Puglla
 */
public class Chronometer {
    /* Attributes */
    
    /* Time */
    private long delta;
    private long lastTime;
    private long time;
    
    /* Check if running */
    private boolean running;
    
    /* Constructor */
    public Chronometer(){
        delta = 0;
        lastTime = System.currentTimeMillis();
        running = false;
    }
    
    /* Methods */
    public void run(long time){
        running = true;
        this.time = time;
    }
    
    /* Updates the chronometer status */
    public void update(){
        /* Getting the current delta time */
        if(running){
            delta += System.currentTimeMillis() - lastTime;
        }
        
        /* Delta is greater than time */
        if(delta >= time) {
            running = false;
            delta = 0;
        }
        
        /* Updates last frame buffer time */
        lastTime = System.currentTimeMillis();
    }
    
    /* Checks the running status of the program */
    public boolean isRunning(){
        return running;
    }
}
