package ui;

import objects.Tile;
import screen.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameState.MENU;
import static main.GameState.setGameState;

public class bottomBar {

    private int x, y, width, height;
    private Playing playing;
    private buttonClass bMenu, bSave;

    private Tile selectedTile;

    private ArrayList<buttonClass> tileBtn = new ArrayList<>();

    public bottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.playing = playing;
        initButton();
    }

    private void initButton() {
        bMenu = new buttonClass("Menu", 2, 642, 100, 30);
        bSave = new buttonClass("Save", 2, 674, 100, 30);

        int w = 50;
        int h = 50;
        int xStart = 100;
        int yStart = 640;
        int xOffset = (int) (w * 1.1);
        int i = 0;

        for (Tile tile : playing.getTileManager().tiles) {
            tileBtn.add(new buttonClass(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
            i++;
        }
    }

    private void drawBtn(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);
        drawTileBtn(g);
        drawSelectTile(g);
    }

    private void drawSelectTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 640, 50, 50, null);
            g.drawRect(550, 640, 50, 50);
        }
    }

    private void drawTileBtn(Graphics g) {
        for (buttonClass b : tileBtn) {

            // Image sprite
            g.drawImage(getBtnImg(b.getId()), b.x, b.y, b.width, b.height, null);

            // Mouse Over
            if (b.isMouseOver()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.YELLOW);
            }

            // Border
            g.drawRect(b.x, b.y, b.width, b.height);

            // Mouse Press
            if (b.isMousePress()) {
                g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
                g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(221, 33, 122));
        g.fillRect(x, y, width, height);

        drawBtn(g);
    }

    public BufferedImage getBtnImg(int id) {
        return playing.getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            saveLevel();
        } else {
            for (buttonClass b : tileBtn) {
                if (b.getBounds().contains(x, y)) {
                    selectedTile = playing.getTileManager().getTile(b.getId());
                    playing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    private void saveLevel() {
        playing.saveLevel();
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);

        for (buttonClass b : tileBtn)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
        }else {
            for (buttonClass b : tileBtn) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePress(true);
        } else if(bSave.getBounds().contains(x, y)) {
            bSave.setMousePress(true);
        }else {
            for (buttonClass b : tileBtn) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePress(true);
                    return;
                }
            }
        }
    }

    public void mouseRelease(int x, int y) {
        bMenu.resetBoolean();
        bSave.resetBoolean();
        for (buttonClass b : tileBtn) {
            b.resetBoolean();
        }
    }
}
