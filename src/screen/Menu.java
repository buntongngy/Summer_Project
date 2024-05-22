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


public class Menu extends GameScreen implements ScreenMethods{

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;
    private Random random;
    private buttonClass bPlay, bSetting, bQuit;

    public Menu(Game game) {
        super(game);
        random = new Random();
        img = LoadedSave.getSpriteAtlas();
        loadSprites();
        initButtons();
    }

    @Override
    public void render(Graphics g) {

        drawButton(g);

    }


    private void initButtons() {
        bPlay = new buttonClass("Play", 100, 100, 100, 30);
    }

    private void drawButton(Graphics g) {
        bPlay.draw(g);
    }




    private void loadSprites() {
        for (int y=0; y<3; y++) {
            for (int x=0; x<10; x++) {
                sprites.add(img.getSubimage(x*32,y*32,32,32));
            }
        }
    }


    private int getRanInt(){
        return random.nextInt(20);
    }
}
