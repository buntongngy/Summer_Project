package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

    private GameScreen screen;

    private BufferedImage img;

    private final double FPS_Set = 120.0;
    private final double UPS_Set = 60.0;

    private int update;
    private long lastTimeUpdate;

    private Thread gameThread;


    public Game() {

        importImg();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        screen = new GameScreen(img);
        add(screen);

        pack();
        setVisible(true);
    }

    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/TD_sheet.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void start() {
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    private void callUPS() {
        if (System.currentTimeMillis() - lastTimeUpdate >=  1000) {
            System.out.println("Update: " + update);
            update = 0;
            lastTimeUpdate = System.currentTimeMillis();
        }
    }

    public void updateGame() {
        update++;
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.start();

    }
    @Override
    public void run() {
         double timePerFrame = 1000000000.0 /FPS_Set;
         double timePerUpdate = 1000000000.0/UPS_Set;

         long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        long lastTimeCheck = System.currentTimeMillis();

        int frame = 0;
        int update = 0;

        long now;

        while (true){

            now  = System.nanoTime();

        if (now - lastFrame >= timePerFrame) {
            lastFrame = now;
            repaint();
            frame++;
        }

        if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                update++;

        }
        if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
            System.out.println("FPS: " + frame + " UPS: " + update);
            frame = 0;
            update = 0;
            lastTimeCheck = System.currentTimeMillis();
        }
        }


    }
}