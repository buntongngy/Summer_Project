package manager;

import Enemy.Enemy;
import helperPackage.LoadedSave;
import main.Game;
import screen.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImg;
    private Enemy enemies;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImg = new BufferedImage[4];
        enemies = new Enemy(32*3, 32*9,0,0);
        loadEnemyImg();
    }

    private void loadEnemyImg() {
        BufferedImage atlas = LoadedSave.getSpriteAtlas();
        enemyImg[0] = atlas.getSubimage(0,32,32,32);
        enemyImg[1] = atlas.getSubimage(32,32,32,32);
        enemyImg[2] = atlas.getSubimage(2* 32,32,32,32);
        enemyImg[3] = atlas.getSubimage(3* 32,32,32,32);
    }

    public void update() {

    }

    public void draw(Graphics g) {
        drawEnemy(enemies,g);
    }

    private void drawEnemy(Enemy e, Graphics g) {

        g.drawImage(enemyImg[0], (int)e.getX(),(int)e.getY(),null);

    }
}
