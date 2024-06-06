package manager;

import Enemy.Enemy;
import helperPackage.LoadedSave;
import main.Game;
import screen.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperPackage.Constants.Direction.*;
import static helperPackage.Constants.Tile.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImg;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float speed = 0.5f;

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
            isNextTileRoad(e);
        }

    }

    public boolean isNextTileRoad(Enemy e) {

        int newX = (int)(e.getX() + getSpeedWidth(e.getLastDir()));
        int newY = (int)(e.getY() + getSpeedHeight(e.getLastDir()));

        if(getTileType(newX,newY) == ROAD_TILE) {
            e.move(speed, e.getLastDir());
        } else if (isAtEnd(e)) {

        } else {
            setNewDirectionandMove(e);
        }

        return false;
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
//            case LEFT:
//                if(xCord > 0) {
//                    xCord--;
//                }
//                break;
            case RIGHT:
                if (xCord < 19) {
                    xCord++;
                }
                break;
//            case UP:
//                if (yCord > 0) {
//                    yCord--;
//                }
//                break;
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