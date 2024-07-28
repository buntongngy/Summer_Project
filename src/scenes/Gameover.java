package scenes;

import main.Game;
import ui.MyButton;

import java.awt.*;

public class Gameover extends GameScene implements SceneMethods{

    private MyButton bReplay, bMenu;

    public Gameover(Game game) {
        super(game);
        initButton();
    }

    private void initButton() {

        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 300;
        int yOffset = 100;

        bMenu = new MyButton("Menu", x,y,w,h);
        bReplay = new MyButton("Replay", x, y+yOffset, w, h);

    }

    @Override
    public void render(Graphics g) {

        g.setFont(new Font("LucidaSans", Font.BOLD, 50));
        g.setColor(Color.red);
        g.drawString("Game Over!", 170, 80);

        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        bMenu.draw(g);
        bReplay.draw(g);

    }

    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBounds().contains(x,y)) {

            bMenu.setMouseOver(true);

        } else if (bReplay.getBounds().contains(x,y)) {

            bReplay.setMouseOver(true);

        }

    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {

            bMenu.setMousePressed(true);

        } else if (bReplay.getBounds().contains(x,y)) {

            bReplay.setMousePressed(true);

        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
