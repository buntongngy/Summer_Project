package screen;

import helperPackage.LoadedSave;
import main.Game;
import objects.Tile;
import ui.ActionBar;
import ui.ToolBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Editing extends GameScreen implements ScreenMethods{

    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private boolean drawSelect = false;

    private ToolBar toolBar;
    private int lastTileX, lastTileY, lastTileId, animeIndex, tick;


    public Editing(Game game) {
        super(game);
        loadDefaultLvl();
        toolBar = new ToolBar(0, 640, 640, 100, this);

    }

    private void loadDefaultLvl() {
        lvl = LoadedSave.getLevelData("new level");
        if (lvl == null) {
            System.err.println("Failed to load default level data.");
        }
    }

    @Override
    public void render(Graphics g) {
        updateTick();
        drawLvl(g);
        toolBar.draw(g);
        drawSelectTile(g);
    }

    private void updateTick() {
        tick++;
        if (tick >= 20) {
            tick = 0;
            animeIndex++;
            if (animeIndex >= 4) {
                animeIndex =0;
            }
        }
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

    private boolean isAnimation(int spriteId) {
        return game.getTileManager().isSpriteAnime(spriteId);
    }

    public BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    public BufferedImage getSprite(int spriteId, int animeIndex) {
        return game.getTileManager().getAnimationSprite(spriteId, animeIndex);
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

            if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) {
                return;
            }

            lastTileY = tileY;
            lastTileX = tileX;
            lastTileId = selectedTile.getId();

            lvl[tileY][tileX] = selectedTile.getId();
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
