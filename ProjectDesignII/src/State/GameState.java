/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import GameObjects.Object;
import GameObjects.Player;
import Graphics.Assets;
import Math.Vector2D;
import java.awt.Graphics;
import java.util.ArrayList;
import GameObjects.Constants;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
/**
 *
 * @author KelvinOjeda
 */
public class GameState {
    /* Attributes */
    private Player player;
    private ArrayList<Object> movingObjects = new ArrayList<Object>();
    
    /* Constructor */
    public GameState(){
        /* Attributes */
        
        int wPos = 0;
        int hPos = 0;
        
        /* Calculates the vector x and y attributes */
        wPos = Constants.WIDTH / 2 - Assets.player.getWidth() / 2;
        hPos = Constants.HEIGHT / 2 - Assets.player.getHeight() / 2;
        
        player= new Player(
            new Vector2D(wPos, hPos), 
            new Vector2D(), 
            Constants.PLAYER_MAX_VEL, 
            Assets.player, this
        );
        
        /* Adds the player as a moving object */
        movingObjects.add(player);
    }
    
    /* Methods */
    
    /* Updates the object status */
    public void update(){
        for(int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }
    }
    
    /* Draws the desired object */
    public void draw(Graphics g){
        /* Anti aliasing */
        Graphics2D g2d = (Graphics2D)g;
        
        /* Bilinear interpolation */
        g2d.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        
        /* Draws the object */
        int i = 0;

        for(i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }
    }
    
    /* Get */
    
    /* Array List access */
    public ArrayList<Object> getMovingObjects() {
        return movingObjects;
    }
    
}
