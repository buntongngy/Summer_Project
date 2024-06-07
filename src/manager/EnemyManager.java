package manager;

import Enemy.Enemy;
import helperPackage.LoadedSave;
import screen.Playing;
import Enemy.Orc;
import Enemy.Bat;
import Enemy.Knight;
import Enemy.Wolf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperPackage.Constants.Direction.*;
import static helperPackage.Constants.Enemeis.*;
import static helperPackage.Constants.Tile.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImg;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float speed = 0.5f;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImg = new BufferedImage[4];
        addEnemies(0 * 32,13 * 32, ORC);
        addEnemies(6 * 32,9 * 32, BAT);
        addEnemies(7 * 32,9 * 32, KNIGHT);
        addEnemies(8 * 32,8 * 32, WOLF);
        loadEnemyImg();
    }

    private void loadEnemyImg() {
        BufferedImage atlas = LoadedSave.getSpriteAtlas();
        for (int i = 0; i<4;i++) {
            enemyImg[i] = atlas.getSubimage(i * 32,32,32,32);
        }
    }

    public void update() {
        for (Enemy e: enemies) {
            updateEnemyMove(e);
        }

    }

    public void updateEnemyMove(Enemy e) {

        if (e.getLastDir() == -1) {
            setNewDirectionandMove(e);
        }

        int newX = (int)(e.getX() + getSpeedWidth(e.getLastDir()));
        int newY = (int)(e.getY() + getSpeedHeight(e.getLastDir()));

        if(getTileType(newX,newY) == ROAD_TILE) {
            e.move(speed, e.getLastDir());
        } else if (isAtEnd(e)) {

        } else {

        }

    }

    private void setNewDirectionandMove(Enemy e) {

        int dir = e.getLastDir();

        int xCord = (int)e.getX() /32;
        int yCord = (int)e.getY() /32;

        fixEnemyOffset(e, dir, xCord, yCord);

        if(dir == LEFT || dir == RIGHT) {
            int newY = (int)(e.getY() + getSpeedHeight(UP));
            if (getTileType((int)e.getX(), newY) == ROAD_TILE) {
                e.move(speed, UP);
            } else {
                e.move(speed, DOWN);
            }
        } else {
            int newX = (int)(e.getX() + getSpeedWidth(RIGHT));
            if(getTileType(newX, (int)e.getY()) == ROAD_TILE) {
                e.move(speed, RIGHT);
            } else {
                e.move(speed, LEFT);
            }
        }

    }

    private void fixEnemyOffset(Enemy e, int dir, int xCord, int yCord) {

        switch(dir) {

            case RIGHT:
                if (xCord < 19) {
                    xCord++;
                }
                break;
            case DOWN:
                if (yCord < 19) {
                    yCord++;
                }
                break;
        }

        e.setPos(xCord * 32, yCord * 32);

    }

    private boolean isAtEnd(Enemy e) {
            return false;

    }

    private int getTileType(int x, int y) {
        return  playing.getTileType(x,y);
    }

    public float getSpeedWidth(int dir) {
        if(dir == LEFT) {
            return -speed;
        } else if(dir == RIGHT) {
            return speed + 32;
        }
        return 0;
    }

    public float getSpeedHeight(int dir) {
        if(dir == UP) {
            return -speed;
        } else if(dir == DOWN) {
            return speed + 32;
        }
        return 0;
    }

    public void addEnemies(int x, int y, int enemyType) {

        switch (enemyType) {
            case ORC:
                enemies.add(new Orc(x, y,0));
            break;
            case BAT:
                enemies.add(new Bat(x, y,0));
                break;
            case KNIGHT:
                enemies.add(new Knight(x, y,0));
                break;
            case WOLF:
                enemies.add(new Wolf(x, y,0));
                break;
        }


    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            drawEnemy(e,g);
        }

    }

    private void drawEnemy(Enemy e, Graphics g) {

        g.drawImage(enemyImg[e.getEnemyType()], (int)e.getX(),(int)e.getY(),null);

    }
}