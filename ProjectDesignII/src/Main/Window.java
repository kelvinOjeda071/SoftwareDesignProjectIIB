package Main;

import Graphics.Assets;
import Input.KeyBoard;
import State.GameState;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import GameObjects.Constants;
import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {
    /* Attributes */
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    
    /* Frames per second displayed on screen */
    private final int FPS = 60;
    private double TARGETTIME = 1000000000 / FPS;
    private double delta = 0;
    
    /* Average FPS displayed */
    private int AVERAGEFPS = FPS;
    
    /* Game status and keyboard object */
    private GameState gameState;
    private KeyBoard keyBoard;

    public Window() {
        /* Attributes */
        
        /* Window Title */
        setTitle("Atari Asteroids");
        
        /* Window attributes */
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        
        /* Canvas and Keyboard objects */
        canvas = new Canvas();
        keyBoard = new KeyBoard();
        
        /* Sets the window dimensions */
        canvas.setPreferredSize(
                new Dimension(Constants.WIDTH, Constants.HEIGHT)
        );
        canvas.setMaximumSize(
                new Dimension(Constants.WIDTH, Constants.HEIGHT)
        );
        canvas.setMinimumSize(
                new Dimension(Constants.WIDTH, Constants.HEIGHT)
        );
        
        canvas.setFocusable(true);
        
        /* Adds the canvas to the current window */
        add(canvas);
        
        /* IO events */
        canvas.addKeyListener(keyBoard);
        setVisible(true);
    }

    public static void main(String[] args) {
        /* Creates the window type object */
        new Window().start();
    }
    
    /* Methods */
    private void update() {
        keyBoard.update();
        gameState.update();
    }

    private void draw() {
        /* Sets the buffer */
        bs = canvas.getBufferStrategy();
        
        if (bs == null) {
            /* The canva need 3 buffers */
            canvas.createBufferStrategy(3);
            
            return;
        }
        
        g = bs.getDrawGraphics();
        
        /* Draws the window */
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        gameState.draw(g);
        
        /* Shows the buffer */
        g.dispose();
        bs.show();
    }

    private void init() {
        Assets.init();
        gameState= new GameState();
    }

    @Override
    public void run() {
        /* Attributes */
        long now = 0;
        
        /* Time and frames */
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;
        init();
        
        /* While program is not closed */
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / TARGETTIME;
            time += (now - lastTime);
            lastTime = now;
                
            if (delta >= 1) {
                update();
                draw();
                delta--;
                frames++;
                
            }
            if (time >= 1000000000) {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;

            }

        }
        
        /* Stops the proccess */
        stop();
    }
    
    /* Starts the proccess */
    private void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }
    
    /* Stops the current proccess */
    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            /* Prints the error */
            e.printStackTrace();
        }
    }

}
