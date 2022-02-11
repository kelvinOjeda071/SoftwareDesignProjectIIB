/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import GameObject.Object;
import GameObject.Player;
import Graphics.Assets;
import Math.Vector2D;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author KelvinOjeda
 */
public class GameState {
    /* Attributes */
    private Player player;
    private ArrayList<Object> movingObjects = 
            new ArrayList<Object>();
    
    /* Constructor */
    public GameState(){

        player= new Player(new Vector2D(100,500), new Vector2D(), 7.5, Assets.player);
        movingObjects.add(player);
    }
    
    /* Methods */
    
    public void update(){
        for(int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }
    }
    
    public void draw(Graphics g){
        for(int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }
    }
    
    /* Get */
    
    /* Array List access */
    public ArrayList<Object> getMovingObjects() {
        return movingObjects;
    }
    
}
