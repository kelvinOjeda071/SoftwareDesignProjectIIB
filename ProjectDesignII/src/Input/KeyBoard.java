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
    
    public KeyBoard(){
        UP=LEFT=RIGHT=false;
        SHOOT = false;
    }
    
    public void update(){
        UP=keys[KeyEvent.VK_UP]; //Getting the key code of UP key
        LEFT=keys[KeyEvent.VK_LEFT]; //Getting the key code of LEFT key
        RIGHT=keys[KeyEvent.VK_RIGHT]; //Getting the key code of RIGHT key
        SHOOT = keys[KeyEvent.VK_Q];
    }

    @Override
    public void keyPressed(KeyEvent e) { //When the key is pressed
        keys[e.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {//Whe the key is not pressed,is released
        keys[e.getKeyCode()]=false;
    }
    
    @Override
    public void keyTyped(KeyEvent e){}
    
}
