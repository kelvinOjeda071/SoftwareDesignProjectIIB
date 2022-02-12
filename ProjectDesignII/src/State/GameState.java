/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import GameObjects.Asteroid;
import GameObjects.Object;
import GameObjects.Player;
import Graphics.Asset;
import Math.Vector2D;
import java.awt.Graphics;
import java.util.ArrayList;
import GameObjects.Constant;
import GameObjects.Size;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
/**
 *
 * @author KelvinOjeda
 */
public class GameState {
    /* Attributes */
    private Player player;
    private ArrayList<Object> movingObjects = new ArrayList<Object>();
    private int meteors;
    
    /* Constructor */
    public GameState(){
        /* Attributes */
        
        int wPos = 0;
        int hPos = 0;
        
        /* Calculates the vector x and y attributes */
        wPos = Constant.WIDTH / 2 - Asset.player.getWidth() / 2;
        hPos = Constant.HEIGHT / 2 - Asset.player.getHeight() / 2;
        
        player= new Player(
            new Vector2D(wPos, hPos), 
            new Vector2D(), 
            Constant.PLAYER_MAX_VEL, 
            Asset.player, this
        );
        
        /* Adds the player as a moving object */
        movingObjects.add(player);
        
        /* Meteors quantity */
        meteors = 1;
        
        /* Starts the asteroids wave */
        startWave();
    }
    
    /* Methods */
    
    /* Deploys the asteroids */
    private void startWave(){
        /* Asteroid position */
        double x;
        double y;
        double myAngle;
        
        /* Counter */
        int i = 0;
        
        /* Starts the wave*/
        for(i = 0; i < meteors; i++){
            x = i % 2 == 0 ? Math.random() * Constant.WIDTH : 0;
            y = i % 2 == 0 ? 0 : Math.random() * Constant.WIDTH;
            
            /* Texture */
            BufferedImage texture = 
                Asset.bigs[
                    (int) (Math.random() * Asset.bigs.length)
                ];
            
            /* Calculates the angle*/
            myAngle = Math.random() * Math.PI * 2;
            
            /* Adds the asteroid as a moving object */
            movingObjects.add(
                    new Asteroid(
                        new Vector2D(x, y),
                        new Vector2D(0, 1).setDirection(myAngle),
                        Constant.METEOR_VEL * Math.random() + 1,
                        texture, 
                        this, 
                        Size.BIG
                    )
            );
        }
        
        /* Increases game difficulty */
        meteors ++;
        
    }
    
    /* Updates the object status */
    public void update(){
        for(int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }
        
        /* Decides if a new wave is necessary */
        for(int i = 0; i < movingObjects.size(); i++) {
            if(movingObjects.get(i) instanceof Asteroid){
               return; 
            }
        }
        
        /* Starts the new wave */
        startWave();
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
