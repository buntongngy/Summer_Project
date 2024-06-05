package main;

import helperPackage.LoadedSave;
import input.myKeyListener;
import input.myMouseListener;
import manager.TileManager;
import screen.Editing;
import screen.Menu;
import screen.Playing;
import screen.Setting;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

    private GameScreen screen;

    private final double FPS_Set = 120.0;
    private final double UPS_Set = 60.0;

    private int update;
    private long lastTimeUpdate;


    public TileManager tileManager;
    private Render render;
    private Menu menu;
    private Playing playing;
    private Setting setting;
    private Editing editing;

    private Thread gameThread;


    public Game() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initClasses();
        createDefaultLvl();

        add(screen);

        pack();
        setVisible(true);
    }

    private void initClasses() {

        tileManager = new TileManager();
        render = new Render(this);
        screen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        setting = new Setting(this);
        editing = new Editing(this);

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
      switch (GameState.gameState) {
          case EDIT:
              break;
          case MENU:
              break;
          case PLAYING:
              playing.update();
              break;
          case SETTINGS:
              break;
           default:
              break;
      }
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.screen.initInput();
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

    private void createDefaultLvl() {
        int[] arr = new int[400]; // 20x20 level with all tiles set to 0
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        LoadedSave.createLevel("new level", arr);
    }

    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Setting getSetting() {
        return setting;
    }

    public Editing getEditing() {
        return  editing;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Editing getEditor() {
        return editing;
    }
}