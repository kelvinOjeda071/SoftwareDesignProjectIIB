/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import java.awt.Graphics;

/**
 *
 * @author HOME
 */
public abstract class State {
    /**actual state of play */
    private static State currentState = null;
    
    public static void changeState(State newState) {
        currentState = newState;
    }
    public static State getCurrentState() {
        return currentState;
    }
    
    
	public abstract void update();
	public abstract void draw(Graphics g); 
    
}
