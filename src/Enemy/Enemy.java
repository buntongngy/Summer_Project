package Enemy;

import java.awt.*;
import static helperPackage.Constants.Direction.*;

public abstract class Enemy {

    private float x,y;
    private Rectangle bounds;
    private int health, id, enemyType, lastDir;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        lastDir = RIGHT;
        bounds = new Rectangle((int)x, (int)y, 32, 32);
    }

    public void move(float speed, int dir) {
        lastDir = dir;
        switch (dir) {
            case LEFT:
                this.x -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir() {
        return lastDir;
    }

    public float getSpeedX(int lastDir) {
        return lastDir;
    }

    public float getSpeedY(int lastDir) {
        return lastDir;
    }
}
