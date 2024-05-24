package ui;

import objects.Tile;
import screen.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameState.MENU;
import static main.GameState.setGameState;

public class bottomBar {

    private int x, y ,width, height;
    private Playing playing;
    private buttonClass bMenu;

    private ArrayList<buttonClass> tileBtn = new ArrayList<>();

    public bottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.height =height;
        this.width = width;
        this.playing = playing;
        initButton();
    }

    private void initButton() {
        bMenu = new buttonClass("Menu", 2, 642, 100, 30);

        int w = 50;
        int h = 50;
        int xStart = 100;
        int yStart = 640;
        int xOffset = (int)(w*1.1);
        int i =0;

        for (Tile tile : playing.getTileManager().tiles) {
            tileBtn.add(new buttonClass(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
            i++;
        }

    }

    private void drawBtn(Graphics g) {
        bMenu.draw(g);
        drawTileBtn(g);

    }

    private void drawTileBtn(Graphics g) {
        for (buttonClass b: tileBtn) {
            g.drawImage(getBtnImg(b.getId()), b.x, b.y, b.width, b.height, null);
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
        if(bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePress(true);
        }
    }

    public void mouseRelease(int x, int y) {
        bMenu.resetBoolean();
    }

}
