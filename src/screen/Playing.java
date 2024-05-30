package screen;

import helperPackage.LoadedSave;
import main.Game;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Playing extends GameScreen implements ScreenMethods {

    private int[][] lvl;
    private ActionBar bottomBar;
    private int mouseX, mouseY;

    public Playing(Game game) {
        super(game);

        loadDefaultLvl();

        bottomBar = new ActionBar(0, 640, 640, 100, this);

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

    @Override
    public void render(Graphics g) {
        drawLvl(g);

        if (lvl == null) {
            System.err.println("Level data is null, cannot render");
            return; // Prevent further rendering logic if lvl is null
        }

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
        bottomBar.draw(g);

    }

    public void setLvl(int[][] lvl) {
        this.lvl =lvl;
    }

    private void drawLvl(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    public BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);

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
