/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Math;

/**
 *
 * @author KelvinOjeda
 */
public class Vector2D {
    /* Attributes */
    private double x;
    private double y;
    
    /* Constructor */
    public Vector2D() {
        x = 0;
        y = 0;
    }
    
    /* Constructor with attributes */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /* Methods */
    
    /* Calculates the vector magnitude */
    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }
    
    /* Gives the vector a direction */
    public Vector2D setDirection(double angle) {
        /* Attributes */
        double magnitude = getMagnitude();
        
        /* Returns the new vector */
        return new Vector2D(
            Math.cos(angle) * magnitude,
            Math.sin(angle) * magnitude
        );
    }
    /* Give the angle */
    public double getAngle(){
        return Math.asin(y/getMagnitude());
    }
    
    /* Addition operation between vectors */
    public Vector2D add(Vector2D v) {
        return new Vector2D(
            this.x + v.getX(),
            this.y + v.getY()
        );
    }
    
    /* Subtraction operation between vectors */
    public Vector2D subtract(Vector2D v) {
        return new Vector2D(
            this.x - v.getX(),
            this.y - v.getY()
        );
    }
    
    /* Resizes and scales the vector */
    public Vector2D scale(double value) { //Multiply by a scale
        return new Vector2D(this.x * value, this.y * value);
    }
    
    /* Limits the vector dimensions */
    public Vector2D limit(double value) {
        if(getMagnitude() > value){
            return this.normalize().scale(value);
        }
        return this;
    }
    
    /* Normalizes the vector */
    public Vector2D normalize(){
        /* Attributes */
        double magnitude = getMagnitude();
        
        return new Vector2D(
            x / magnitude, 
            y / magnitude
        );
    }
    
    /* Get */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    /* Set */
    public void setY(double y) {
        this.y = y;
    }
    
    public void setX(double x) {
        this.x = x;
    }

}
