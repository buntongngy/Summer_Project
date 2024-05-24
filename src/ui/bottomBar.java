package ui;

import objects.Tile;
import screen.Playing;

import java.awt.*;
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

        for (int i = 0; i<10; i++) {

        }

        for (Tile tile : playing.getTileManager().tiles) {
            tileBtn.add(new buttonClass("", x, y, width, height));
        }

    }

    private void drawBtn(Graphics g) {
        bMenu.draw(g);
    }

    public void draw(Graphics g) {


        g.setColor(new Color(221, 33, 122));
        g.fillRect(x, y, width, height);

        drawBtn(g);
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
