package ui;

import java.awt.*;

import static main.GameState.MENU;
import static main.GameState.setGameState;

public class bottomBar {

    private int x, y ,width, height;
    private buttonClass bMenu;

    public bottomBar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height =height;
        this.width = width;
        initButton();
    }

    private void initButton() {
        bMenu = new buttonClass("Menu", 2, 2, 100, 30);
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
