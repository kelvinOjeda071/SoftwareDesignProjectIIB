package Main;

import Graphics.Assets;
import State.GameState;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

    public static final int WIDTH = 800, HEIGHT = 600;
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private final int FPS = 60; //Frames in the game that we want to implement
    private double TARGETTIME = 1000000000 / FPS; //Time in nanoseconds
    private double delta = 0; //Save temporate time 
    private int AVERAGEFPS = FPS; //Average frame per second in the game
    
    private GameState gameState;

    public Window() {
        setTitle("Asteroid Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);

        add(canvas);//Adding canvas in the current windows

    }

    public static void main(String[] args) {
        new Window().start();
    }

    private void update() {
        gameState.update();
    }

    private void draw() {
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3); //3 Is the number of buffer that a canvas need
            return;
        }
        g = bs.getDrawGraphics();
        //-----------Space to draw Figure into Windows-----------------
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        gameState.draw(g);
        //-------------------------------------------------------------
        g.dispose();
        bs.show();
    }

    private void init() {
        gameState= new GameState();
    }

    @Override
    public void run() {

        long now = 0;
        long lastTime = System.nanoTime(); //Getting the time in nanoseconds
        int frames = 0;
        long time = 0;
        init();
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
                System.out.println(frames);
            }
            if (time >= 1000000000) {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;

            }

        }

        stop();
    }

    private void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
