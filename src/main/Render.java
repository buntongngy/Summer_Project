package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Render {

    private GameScreen gameScreen;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;
    private Random random;

    public Render(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        random = new Random();
        importImg();
        loadSprites();
    }

    public void render(Graphics g) {

        switch(GameState.gameState) {
            case NEW:
                for (int y = 0; y < 20; y++) {
                    for (int x = 0; x < 20; x++) {
                        g.drawImage(sprites.get(getRanInt()),x*32,y*32, null);
                    }
                }
                break;
            case PLAYING:
                break;
            case SETTINGS:
                break;
        }

    }

    private void loadSprites() {
        for (int y=0; y<3; y++) {
            for (int x=0; x<10; x++) {
                sprites.add(img.getSubimage(x*32,y*32,32,32));
            }
        }
    }

    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/TD_sheet.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int getRanInt(){
        return random.nextInt(20);
    }

}
