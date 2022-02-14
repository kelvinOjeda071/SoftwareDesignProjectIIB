/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

/**
 *
 * @author Jonathan Puglla
 */
public class Constant {

    /* Static Attributes */

 /* Dimensions */
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    /* Game properties */
    public static final int FIRERATE = 300;
    public static final double DELTAANGLE = 0.1;
    public static final double ACC = 0.2;
    public static final double PLAYER_MAX_VEL = 7.0;
   
    public  static  final  long  FLICKER_TIME  =  200 ;
    public  static  final  long  SPAWNING_TIME  =  3000 ;
    public static final long GAME_OVER_TIME = 3000;
    /* Laser properties */
    public static final double LASER_VEL = 15.0;

    /* Asteroid properties */
    public static final double ASTEROID_VEL = 2.0;
    public static final int ASTEROID_SCORE = 20;

    /*Ufo properties*/
    public static final int NODE_RADIUS = 160;
	
	public static final double UFO_MASS = 60;
	
	public static final int UFO_MAX_VEL = 3;
	
	public static long UFO_FIRE_RATE = 1000;
	
	public static double UFO_ANGLE_RANGE = Math.PI / 2;
	
	public static final int UFO_SCORE = 40;
        public static final long UFO_SPAWN_RATE = 10000;
        public static final String PLAY = "PLAY";
        public static final String EXIT = "EXIT";
        public static final int LOADING_BAR_WIDTH = 500;
	public static final int LOADING_BAR_HEIGHT = 50;
}
