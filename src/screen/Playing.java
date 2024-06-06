package screen;

import helperPackage.LoadedSave;
import main.Game;
import manager.EnemyManager;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Playing extends GameScreen implements ScreenMethods {

    private int[][] lvl;
    private ActionBar bottomBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManger;

    public Playing(Game game) {
        super(game);

        loadDefaultLvl();

        bottomBar = new ActionBar(0, 640, 640, 100, this);
        enemyManger = new EnemyManager(this);

        // Check if the level data is still null after loading
        if (lvl == null) {
            System.err.println("Level data is null after initialization. Initializing with default empty data.");
            lvl = new int[20][20]; // Initialize with default empty level data
        }
    }

    private void loadDefaultLvl() {
        lvl = LoadedSave.getLevelData("new level");
        if (lvl == null) {
            System.err.println("Failed to load default level data.");
        }
    }

    public void update() {
        updateTick();
        enemyManger.update();
    }

    @Override
    public void render(Graphics g) {
        drawLvl(g);
        bottomBar.draw(g);
        enemyManger.draw(g);

    }

    public void setLvl(int[][] lvl) {
        this.lvl =lvl;
    }

    private void drawLvl(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animeIndex), x * 32, y * 32, null);
                } else {
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
                }
            }
        }
    }

    public int getTileType(int x, int y) {

        int xCord = x/32;
        int yCord = y/32;

        if (xCord < 0 || xCord > 19) {
            return 0;
        }

        if (yCord < 0 || yCord > 19) {
            return 0;
        }

        int id = lvl[y/32][x/32];
        return game.getTileManager().getTile(id).getTileType();
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            enemyManger.addEnemies(x,y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x, y);
        } else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseRelease(int x, int y) {
        bottomBar.mouseRelease(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
