package screen;

import helperPackage.LevelBuild;
import helperPackage.LoadedSave;
import main.Game;
import manager.TileManager;
import objects.Tile;
import ui.bottomBar;

import java.awt.*;

import static main.GameState.PLAYING;
import static main.GameState.setGameState;

public class Playing extends GameScreen implements ScreenMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private bottomBar bottomBar;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private boolean drawSelect = false;

    private int lastTileX, lastTileY, lastTileId;

    public Playing(Game game) {
        super(game);
        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new bottomBar(0, 640, 640, 100, this);

       // LoadedSave.createFile();
       // LoadedSave.writeToFile();
       // LoadedSave.readFromFile();

        createDefaultLvl();
    }

    private void createDefaultLvl() {
        int[] arr = new int[400];
        for(int i = 0; i<arr.length; i++) {
            arr[i] = 0;

        }
        LoadedSave.createLevel("new level", arr);
    }

    @Override
    public void render(Graphics g) {
        for(int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }
        bottomBar.draw(g);
        drawSelectTile(g);
    }

    public void drawSelectTile(Graphics g){
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    private void changeTile(int x, int y) {

        if (selectedTile != null) {

        int tileX = x / 32;
        int tileY = y / 32;

        if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) {
            return;
        }

        lastTileY = tileY;
        lastTileX = tileX;

        lvl[tileY][tileX] = selectedTile.getId();
    }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
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
        if (y >= 640) {
            // Handle bottomBar drag events if necessary
        } else {
            changeTile(x, y);
        }
    }
}
