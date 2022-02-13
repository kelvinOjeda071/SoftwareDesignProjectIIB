/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import GameObjects.Asteroid;
import GameObjects.MovingObject;
import GameObjects.Player;
import Graphics.Asset;
import Math.Vector2D;
import java.awt.Graphics;
import java.util.ArrayList;
import GameObjects.Constant;
import GameObjects.Size;
import GameObjects.Ufo;
import Graphics.Animation;
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
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    private int asteroid;
    private ArrayList<Animation> explosions = new ArrayList<Animation>();
    
    
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
        asteroid = 1;
        
        /* Starts the asteroids wave */
        startWave();
    }
    
    /* Methods */
    
    /* Increases the number of asteroids if destroyed */
    public void divideAsteroid(Asteroid myAsteroid){
        /* Gets the meteor enumerator size */
        Size size = myAsteroid.getSize();
        
        /* Buffered image */
        BufferedImage[] textures = size.textures;
        
        /* Sets the new meteor enumerator size */
        Size newSize = null;
        
        /* Updates the asteroid size */
        switch (size) {
            case BIG:
                newSize = Size.MED;
                break;
            case MED:
                newSize = Size.SMALL;
                break;
            case SMALL:
                newSize = Size.TINY;
                break;
            default:
                return;
        }
        
        /* Ateroids split iteration */
        int i = 0;
        
        /* Calculates the angle*/
        double myAngle = Math.random() * Math.PI * 2;
            
        for(i = 0; i < size.quantity; i++){
            movingObjects.add(
                    new Asteroid(
                        myAsteroid.getPosition(),
                        new Vector2D(0, 1).setDirection(myAngle),
                        Constant.ASTEROID_VEL * Math.random() + 1,
                        textures[(int) (Math.random() * textures.length)], 
                        this, 
                        newSize
                    )
            );
        }
    }
    
    /* Deploys the asteroids */
    private void startWave(){
        /* Asteroid position */
        double x;
        double y;
        double myAngle;
        
        /* Counter */
        int i = 0;
        
        /* Starts the wave*/
        for(i = 0; i < asteroid; i++){
            x = i % 2 == 0 ? Math.random() * Constant.WIDTH : 0;
            y = i % 2 == 0 ? 0 : Math.random() * Constant.HEIGHT;
            
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
                        Constant.ASTEROID_VEL * Math.random() + 1,
                        texture, 
                        this, 
                        Size.BIG
                    )
            );
        }
        
        /* Increases game difficulty */
        asteroid ++; 
        spawnUfo();
    }
    
    /* Plays the explosion animation */
    public void playExplosion(Vector2D position){
        
        explosions.add(
            new Animation(Asset.explosions, 50, position.subtract(new Vector2D(
                          Asset.explosions[0].getWidth()/2, 
                          Asset.explosions[0].getHeight()/2)))
        );
    }
    
    
    private void spawnUfo(){
        int rand = (int) (Math.random()*2);
        double x = rand % 2 == 0 ? Math.random() * Constant.WIDTH : 0;
        double y = rand % 2 == 0 ? 0 : Math.random() * Constant.HEIGHT;
        ArrayList<Vector2D> path= new ArrayList<Vector2D>();
            double posX, posY;
            /*Top Left*/
            posX= Math.random()*(Constant.WIDTH/2);
            posY= Math.random()*Constant.HEIGHT/2;
            path.add(new Vector2D(posX, posY));
            /*Top right*/
            posX= Math.random()*(Constant.WIDTH/2)+Constant.WIDTH/2;
            posY= Math.random()*Constant.HEIGHT/2;
            path.add(new Vector2D(posX, posY));
            /*Lower left*/
            posX= Math.random()*(Constant.WIDTH/2);
            posY= Math.random()*(Constant.HEIGHT/2)+Constant.HEIGHT/2;
            path.add(new Vector2D(posX, posY));
            /*Lower right*/
            posX= Math.random()*(Constant.WIDTH/2)+Constant.WIDTH/2;
            posY= Math.random()*(Constant.HEIGHT/2)+Constant.HEIGHT/2;
            path.add(new Vector2D(posX, posY));
            
            movingObjects.add(new Ufo(new Vector2D(x,y), 
                    new Vector2D(), 
                    Constant.UFO_MAX_VEL, 
                    Asset.ufo, 
                    path, this));
    }
    
    /* Updates the object status */
    public void update(){
        /* Index iteration attribute */
        int i = 0;
        
        for(i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }
        
        /* Updates the explosions animation */
        for(i = 0; i < explosions.size(); i++){
            Animation myAnimation = explosions.get(i);
            myAnimation.update();
            
            /* Checks if animation is still running */
            if(!myAnimation.isIsRunning()){
                /* Deletes the animation */
                explosions.remove(i);
            }
        }
        
        /* Decides if a new wave is necessary */
        for(i = 0; i < movingObjects.size(); i++) {
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
        
        /* Draws the animation */
        /* Updates the explosions animation */
        for(i = 0; i < explosions.size(); i++){
            Animation myAnimation = explosions.get(i);
            g2d.drawImage(
                myAnimation.getCurrentFrame(), 
                (int)myAnimation.getPosition().getX(), 
                (int)myAnimation.getPosition().getY(),
                null
            );
            
        }
    }
    
    /* Get */
    
    /* Array List access */
    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
    
    /* For getting the position of the player in Ufo*/
    public Player getPlayer(){
        return player;
    }
    
}
