package screen;

import helperPackage.LoadedSave;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.ActionBar;
import ui.ToolBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static helperPackage.Constants.Tile.ROAD_TILE;

public class Editing extends GameScreen implements ScreenMethods{

    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private boolean drawSelect = false;

    private ToolBar toolBar;
    private int lastTileX, lastTileY, lastTileId;

    private PathPoint start, end;


    public Editing(Game game) {
        super(game);
        loadDefaultLvl();
        toolBar = new ToolBar(0, 640, 640, 160, this);

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
        toolBar.draw(g);
        drawSelectTile(g);
        drawPathPoint(g);
    }

    private void drawPathPoint(Graphics g) {

        if(start != null) {
            g.drawImage(toolBar.getStartPathImg(),start.getXCord() * 32, start.getYCord() * 32, 32, 32, null );
        }

        if (end != null) {
            g.drawImage(toolBar.getEndPathImg(),end.getXCord() * 32, end.getYCord() * 32, 32, 32, null );
        }

    }

    public void update() {
        updateTick();
    }



    private void drawLvl(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animeIndex), x * 32, y * 32, null);
                } else
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }



    public void saveLevel() {
        LoadedSave.saveLevel("new level", lvl);
        game.getPlaying().setLvl(lvl);
    }

    public void drawSelectTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;

            if (selectedTile.getId() >= 0) {
                if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                    return;

                lastTileX = tileX;
                lastTileY = tileY;
                lastTileId = selectedTile.getId();

                lvl[tileY][tileX] = selectedTile.getId();
            } else {
                int id = lvl[tileY][tileX];
                if (game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
                    if (selectedTile.getId() == -1)
                        start = new PathPoint(tileX, tileY);
                    else
                        end = new PathPoint(tileX, tileY);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {

        if (y >= 640) {
            toolBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolBar.mouseMoved(x, y);
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
            toolBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseRelease(int x, int y) {
        toolBar.mouseRelease(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {
            // Handle bottomBar drag events if necessary
        } else {
            changeTile(x, y);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            toolBar.rotateSprite();
        }
    }
}
