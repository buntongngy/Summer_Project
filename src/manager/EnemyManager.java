package manager;

import Enemy.Enemy;
import helperPackage.LoadedSave;
import main.Game;
import screen.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImg;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImg = new BufferedImage[4];
        addEnemies(3 * 32,9 * 32);
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
        for (Enemy e: enemies) {
            e.move(0.5f, 0);
        }

    }

    public void addEnemies(int x, int y) {
        enemies.add(new Enemy(x, y,0,0))  ;
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            drawEnemy(e,g);
        }

    }

    private void drawEnemy(Enemy e, Graphics g) {

        g.drawImage(enemyImg[0], (int)e.getX(),(int)e.getY(),null);

    }
}
