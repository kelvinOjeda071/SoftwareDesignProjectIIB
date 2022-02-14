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
import GameObjects.Message;
import GameObjects.Size;
import GameObjects.Ufo;
import Graphics.Animation;
import Graphics.Text;
import Main.MenuState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author KelvinOjeda
 */
public class GameState extends State{

    /* Attributes */
    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    private int asteroid;
    private ArrayList<Animation> explosions = new ArrayList<Animation>();
    private ArrayList<Message> message = new ArrayList<Message>();

    private int score = 0;

    /*player's life*/
    private int lives = 3;

    //number of waves
    private int waves = 1;

    /* Constructor */
    public GameState() {
        /* Attributes */

        int wPos = 0;
        int hPos = 0;

        /* Calculates the vector x and y attributes */
        wPos = Constant.WIDTH / 2 - Asset.player.getWidth() / 2;
        hPos = Constant.HEIGHT / 2 - Asset.player.getHeight() / 2;

        player = new Player(
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
    public void addScore(int value, Vector2D pos) {
        score += value;
        /*Pos is the position of the object that was destroyed */
        message.add(new Message(pos, true, "+"+value+" score", Color.WHITE, false, Asset.fontMed, this));
    }

    /* Increases the number of asteroids if destroyed */
    public void divideAsteroid(Asteroid myAsteroid) {
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

        for (i = 0; i < size.quantity; i++) {
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
    private void startWave() {
        /*draw the message*/
         message.add(new Message(new Vector2D(Constant.WIDTH/2, Constant.HEIGHT/2), false, "WAVE "+waves, Color.WHITE, true, Asset.fontBig, this));
        /* Asteroid position */
        double x;
        double y;
        double myAngle;

        /* Counter */
        int i = 0;

        /* Starts the wave*/
        for (i = 0; i < asteroid; i++) {
            x = i % 2 == 0 ? Math.random() * Constant.WIDTH : 0;
            y = i % 2 == 0 ? 0 : Math.random() * Constant.HEIGHT;

            /* Texture */
            BufferedImage texture
                    = Asset.bigs[(int) (Math.random() * Asset.bigs.length)];

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
        asteroid++;
        spawnUfo();
    }

    /* Plays the explosion animation */
    public void playExplosion(Vector2D position) {

        explosions.add(
                new Animation(Asset.explosions, 50, position.subtract(new Vector2D(
                        Asset.explosions[0].getWidth() / 2,
                        Asset.explosions[0].getHeight() / 2)))
        );
    }

    private void spawnUfo() {
        int rand = (int) (Math.random() * 2);
        double x = rand % 2 == 0 ? Math.random() * Constant.WIDTH : 0;
        double y = rand % 2 == 0 ? 0 : Math.random() * Constant.HEIGHT;
        ArrayList<Vector2D> path = new ArrayList<Vector2D>();
        double posX, posY;
        /*Top Left*/
        posX = Math.random() * (Constant.WIDTH / 2);
        posY = Math.random() * Constant.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));
        /*Top right*/
        posX = Math.random() * (Constant.WIDTH / 2) + Constant.WIDTH / 2;
        posY = Math.random() * Constant.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));
        /*Lower left*/
        posX = Math.random() * (Constant.WIDTH / 2);
        posY = Math.random() * (Constant.HEIGHT / 2) + Constant.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));
        /*Lower right*/
        posX = Math.random() * (Constant.WIDTH / 2) + Constant.WIDTH / 2;
        posY = Math.random() * (Constant.HEIGHT / 2) + Constant.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));

        movingObjects.add(new Ufo(new Vector2D(x, y),
                new Vector2D(),
                Constant.UFO_MAX_VEL,
                Asset.ufo,
                path, this));
    }

    /* Updates the object status */
    public void update() {
        /* Index iteration attribute */
        int i = 0;

        for (i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }

        /* Updates the explosions animation */
        for (i = 0; i < explosions.size(); i++) {
            Animation myAnimation = explosions.get(i);
            myAnimation.update();

            /* Checks if animation is still running */
            if (!myAnimation.isIsRunning()) {
                /* Deletes the animation */
                explosions.remove(i);
            }
        }

        /* Decides if a new wave is necessary */
        for (i = 0; i < movingObjects.size(); i++) {
            if (movingObjects.get(i) instanceof Asteroid) {
                return;
            }
        }

        /* Starts the new wave */
        startWave();
    }

    private void drawScore(Graphics g) {
        Vector2D pos = new Vector2D(850, 25);
        String scoreToString = Integer.toString(score);
        for (int i = 0; i < scoreToString.length(); i++) {
            g.drawImage(Asset.numbers[Integer.parseInt(scoreToString.
                    substring(i, i + 1))], (int) pos.getX(),
                    (int) pos.getY(), null);
            pos.setX(pos.getX() + 20);
        }
    }

    private void drawLives(Graphics g) {
        if (lives<1){
           State.changeState(new MenuState());// message.add(new Message(new Vector2D(Constant.WIDTH/2, Constant.HEIGHT/2), false, "GAME OVER "+waves, Color.WHITE, true, Asset.fontBig, this));
            return;
        }

        Vector2D livePosition = new Vector2D(25, 25);

        g.drawImage(Asset.life, (int) livePosition.getX(),
                (int) livePosition.getY(), null);

        g.drawImage(Asset.numbers[10], (int) livePosition.getX() + 40,
                (int) livePosition.getY() + 5, null);

        String livesToString = Integer.toString(lives);

        Vector2D pos = new Vector2D(livePosition.getX(),
                livePosition.getY());

        for (int i = 0; i < livesToString.length(); i++) {
            int number = Integer.parseInt(livesToString.
                    substring(i, i + 1));

            if (number <= 0) {
                break;
            }
            g.drawImage(Asset.numbers[number],
                    (int) pos.getX() + 60,
                    (int) pos.getY() + 5, null);
            pos.setX(pos.getX() + 20);
        }

    }

    /* Draws the desired object */
    public void draw(Graphics g) {
        
        /* Anti aliasing */
        Graphics2D g2d = (Graphics2D) g;

        /* Bilinear interpolation */
        g2d.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );

        /* Draws the object */
        int i = 0;
        //Draw the message
        for(i = 0; i < message.size(); i++){
            message.get(i).draw(g2d);
        }
        
        for (i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }

        /* Draws the animation */
 /* Updates the explosions animation */
        for (i = 0; i < explosions.size(); i++) {
            Animation myAnimation = explosions.get(i);
            g2d.drawImage(
                    myAnimation.getCurrentFrame(),
                    (int) myAnimation.getPosition().getX(),
                    (int) myAnimation.getPosition().getY(),
                    null
            );

        }
        drawScore(g);
        drawLives(g);
    }

    /* Get */
 /* Array List access */
    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
    //array of possible messages
    public ArrayList<Message> getMessage() {
        return message;
    }

    /* For getting the position of the player in Ufo*/
    public Player getPlayer() {
        return player;
    }

    //subtract one life from the ship
    public void subtractLife() {
        lives--;
    }

    public ArrayList<Message> getMessages() {
		return message;
	}
}
