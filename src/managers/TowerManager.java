package managers;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImg;
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private int towerAcc = 0;


    public TowerManager(Playing playing) {

        this.playing = playing;
        
        loadTowerImg();

    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos,yPos,towerAcc++, selectedTower.getTowerType()));
    }

    public void removeTower(Tower displayTower) {

        for(int i =0; i <towers.size(); i++) {

            if(towers.get(i).getId() == displayTower.getId()) {
                towers.remove(i);
            }

        }

    }

    public void upgradeTower(Tower displayTower) {

        for (Tower t: towers) {
            if (t.getId() == displayTower.getId()) {
                t.upgradeTower();
            }
        }

    }

    private void loadTowerImg() {

        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImg = new BufferedImage[3];

        for (int i =0; i<3;i++) {
            towerImg[i] = atlas.getSubimage((4 + i) * 32, 32,32,32);
        }

    }

    public void draw(Graphics g) {

        for (Tower t: towers) {
            g.drawImage(towerImg[t.getTowerType()], t.getX(), t.getY(), null);
        }
    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t: towers)
            if (t.getX() == x)
                if (t.getY() == y)
                    return t;
        return null;
    }

    public BufferedImage[] getTowerImg() {
        return towerImg;
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attackEnemyisClose(t);
        }


    }

    private void attackEnemyisClose(Tower t) {

            for (Enemy e : playing.getEnemyManager().getEnemies()) {
                if (e.isAlive()) {
                  if (isEnemyisRange(t,e)) {
                      if (t.isCoolDownOver()){
                      playing.shootEnemy(t,e);
                      t.resetCoolDown();
                }
                  }
                }else {

                }
            }

    }


    private boolean isEnemyisRange(Tower t, Enemy e) {

            int range = helpz.Utilz.GetDistance(t.getX(),t.getY(),e.getX(), e.getY());

        return range < t.getRange();
    }



}
