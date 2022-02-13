/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author KelvinOjeda
 */
public class KeyBoard implements KeyListener{
    /* Attributes */
    private boolean[] keys = new boolean[256];
    public static boolean UP, LEFT, RIGHT;
    public static boolean SHOOT;
    
    /* Keyboard constructor */
    public KeyBoard(){
        UP=LEFT=RIGHT=SHOOT=false;
        
    }
    
    /* Methods */
    /* Updates the scenario */
    public void update(){
        UP=keys[KeyEvent.VK_UP];            // UP key code
        LEFT=keys[KeyEvent.VK_LEFT];        // LEFT key code
        RIGHT=keys[KeyEvent.VK_RIGHT];      // RIGHT key code
        SHOOT = keys[KeyEvent.VK_Q];        // SHOOTING key code
    }

    /* Key is pressed */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;
    }
    
    /* Key is released if not pressed */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
    }
    
    /* Key type event */
    @Override
    public void keyTyped(KeyEvent e){}
    
}
