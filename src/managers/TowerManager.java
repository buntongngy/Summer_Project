package managers;

import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helpz.Constants.Tower.*;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImg;
    private Tower tower;

    public TowerManager(Playing playing) {

        this.playing = playing;
        
        loadTowerImg();
        initTower();

    }

    private void initTower() {

        tower = new Tower(3*32, 6 * 32,0, ARCHER);

    }

    private void loadTowerImg() {

        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImg = new BufferedImage[3];

        for (int i =0; i<3;i++) {
            towerImg[i] = atlas.getSubimage((4 + i) * 32, 32,32,32);
        }

    }

    public void draw(Graphics g) {

        g.drawImage(towerImg[ARCHER], tower.getX(), tower.getY(), null);
        g.drawImage(towerImg[CANNON], tower.getX(), tower.getY(), null);
        g.drawImage(towerImg[WIZARD], tower.getX(), tower.getY(), null);

    }

    public BufferedImage[] getTowerImg() {
        return towerImg;
    }

    public void update() {

    }

}
