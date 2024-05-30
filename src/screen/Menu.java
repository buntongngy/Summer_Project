package screen;

import helperPackage.LoadedSave;
import main.Game;
import ui.buttonClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import static main.GameState.*;

public class Menu extends GameScreen implements ScreenMethods{

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;
    private Random random;
    private buttonClass bPlay, bSetting, bQuit, bEdit;

    public Menu(Game game) {
        super(game);
        random = new Random();
        img = LoadedSave.getSpriteAtlas();
        initButtons();
    }

    private void initButtons() {

        int w = 150;
        int h = w/3;
        int x = 640/2 - w/2;
        int y = 150;
        int yOffset = 100;

        bPlay = new buttonClass("Play", x, y, w, h);
        bEdit = new buttonClass("Edit", x,y + yOffset,w,h );
        bSetting = new buttonClass("Setting", x, y + yOffset * 2, w, h);
        bQuit = new buttonClass("Quit", x, y+ yOffset * 3, w, h);

    }

    @Override
    public void render(Graphics g) {
        drawButton(g);
    }

    @Override
    public void mouseClicked(int x, int y) {

        if (bPlay.getBounds().contains(x, y)) {
            setGameState(PLAYING);
        } else if(bEdit.getBounds().contains(x, y)) {
            setGameState(EDIT);
        } else if(bSetting.getBounds().contains(x, y)) {
            setGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }

    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlay.setMouseOver(false);
        bSetting.setMouseOver(false);
        bQuit.setMouseOver(false);
        bEdit.setMouseOver(false);

        if (bPlay.getBounds().contains(x, y)) {
            bPlay.setMouseOver(true);
        } else if(bEdit.getBounds().contains(x, y)) {
            bEdit.setMouseOver(true);
        } else if(bSetting.getBounds().contains(x, y)) {
           bSetting.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlay.getBounds().contains(x, y)) {
            bPlay.setMousePress(true);
        } else if(bEdit.getBounds().contains(x, y)) {
           bEdit.setMousePress(true);
        } else if(bSetting.getBounds().contains(x, y)) {
         bEdit.setMousePress(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePress(true);
        }

    }

    @Override
    public void mouseRelease(int x, int y) {
        resetBtn();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    public void resetBtn() {
       bPlay.resetBoolean();
       bSetting.resetBoolean();
       bQuit.resetBoolean();
       bEdit.resetBoolean();
    }

    private void drawButton(Graphics g) {
        bPlay.draw(g);
        bEdit.draw(g);
        bSetting.draw(g);
        bQuit.draw(g);
    }

    private int getRanInt(){
        return random.nextInt(20);
    }
}
