package Enemy;

import java.awt.*;

public class Enemy {

    private float x,y;
    private Rectangle bounds;
    private int health, id, enemyType;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x, (int)y, 32, 32);
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
}
