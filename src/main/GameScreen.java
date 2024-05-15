package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

    private Random random;
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private int[] num;

    public GameScreen(BufferedImage img) {
        this.img = img;
        loadSprites();
        random = new Random();
    }

    private void loadSprites() {
        for (int y=0; y<3; y++) {
            for (int x=0; x<10; x++) {
                sprites.add(img.getSubimage(x*32,y*32,32,32));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

     //   g.drawImage(sprites.get(2), 0, 0, null);

       //g.drawImage(img.getSubimage(32*8,32,32,32), 0,0, null);

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.drawImage(sprites.get(getRanInt()),x*32,y*32, null);
            }
        }
    }

    private int getRanInt(){
        return random.nextInt(20);
    }

    private Color getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }

}