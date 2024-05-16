package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame {

    private GameScreen screen;

    private BufferedImage img;
    private double timePerFrame;
    private long lastFrame;
    private double timerPerUpdate;
    private long lastUpdate;

    private int update;
    private long lastTimeUpdate;


    public Game() {

        importImg();
        timePerFrame = 1000000000.0 /120.0;
        timerPerUpdate = 1000000000.0 / 60.0;
        setSize(640, 640);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        screen = new GameScreen(img);
        add(screen);
    }



    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/TD_sheet.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loopGame() {

        while (true) {

            if (System.nanoTime() - lastUpdate >= timerPerUpdate) {
                updateGame();

                callFPS();
            }

            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                repaint();
            } else {

            }
        }
    }

    private void callFPS() {
        if (System.currentTimeMillis() - lastTimeUpdate >=  1000) {
            System.out.println("Update: " + update);
            update = 0;
            lastTimeUpdate = System.currentTimeMillis();
        }
    }

    public void updateGame() {
        update++;
        lastUpdate = System.nanoTime();
        System.out.println("Game has update");
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.loopGame();
    }
}