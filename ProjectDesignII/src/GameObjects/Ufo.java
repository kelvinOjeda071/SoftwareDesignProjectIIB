/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import Graphics.Asset;
import Math.Vector2D;
import State.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author KelvinOjeda
 */
public class Ufo extends MovingObject {

    private ArrayList<Vector2D> path;
    /*This is a referents to the current node*/
    private Vector2D currentNode;
    /*This is a index to represent in the node in the array in the nodes*/
    private int index;
    /*Boolean to represetn the path of the UFO*/
    private boolean following;
    /* The time that the UFO can shoot*/
    private Chronometer fireRate;

    public Ufo(Vector2D position, 
            Vector2D velocity, 
            double maxVelocity,
            BufferedImage texture, 
            ArrayList<Vector2D> path, 
            GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        this.path = path;
        index = 0;
        following = true;
        fireRate= new Chronometer();
        fireRate.run(Constant.UFO_FIRE_RATE);

    }
    /*Manage all the UFO's path */
    private Vector2D pathFollowing(){
        currentNode = path.get(index);
        double distanceToNode = currentNode.subtract(getCenter()).
                getMagnitude();
        if(distanceToNode<Constant.NODE_RADIUS){
            index++;
            if(index>=path.size()){
                following=false;
            }
        }
        return seekForce(currentNode);
    }
    
    /*Vector that represent the force*/
    private Vector2D seekForce(Vector2D target){
        Vector2D desiredVelocity = target.subtract(getCenter());
        desiredVelocity= desiredVelocity.normalize().scale(maxVelocity);
        return desiredVelocity.subtract(velocity);
    }
    
    @Override
    public void update() {
        Vector2D pathFollowing;
        if(following)
            pathFollowing = pathFollowing();
        else
            pathFollowing = new Vector2D();
        pathFollowing= pathFollowing.scale(1/Constant.UFO_MASS);
        velocity=velocity.add(pathFollowing);
        
        velocity= velocity.limit(maxVelocity);
        
        position=position.add(velocity);
        
        if(position.getX()>Constant.WIDTH || position.getY()> Constant.HEIGHT
                || position.getX()<0 || position.getY()<0)
            destroy();
        /* Shoot */
        if(!fireRate.isRunning()){
            Vector2D toPlayer=gameState.getPlayer().getCenter().
                    subtract(getCenter());
            toPlayer=toPlayer.normalize();
            double currentAngle = toPlayer.getAngle();
            currentAngle+=Math.random()*Constant.UFO_ANGLE_RANGE-Constant.UFO_ANGLE_RANGE/2;
           if(toPlayer.getX()<0)
               currentAngle = -currentAngle+Math.PI;
            toPlayer = toPlayer.setDirection(currentAngle);
            Laser laser = new Laser(getCenter().add(toPlayer.scale(width)),
                    toPlayer, Constant.LASER_VEL, currentAngle+Math.PI/2, 
                    Asset.greenLaser, gameState);    
            gameState.getMovingObjects().add(0, laser);
            fireRate.run(Constant.UFO_FIRE_RATE);
        }
        angle +=0.05;
        /*Because the UFO also detect collitions with other elements in the 
        widows*/
        collidesWith();
        fireRate.update();
        
    }
    @Override
    public void destroy(){
        gameState.addScore(Constant.UFO_SCORE, position);
        super.destroy();
    }
    
    
    @Override
    public void draw(Graphics g) {
       Graphics2D g2d= (Graphics2D) g;
       at = AffineTransform.getTranslateInstance(
               position.getX(), 
               position.getY());
       at.rotate(angle, width/2, height/2);
       g2d.drawImage(texture, at, null);
       
    
       
    }

    

}
