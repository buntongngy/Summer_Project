package objects;

import java.awt.geom.Point2D;

public class Projectile {

    private Point2D.Float pos;
    private int id, projectileType, dmg;
    private boolean active = true;
    private float xSpeed, ySpeed;

    public Projectile(float x, float y, float xSpeed, float ySpeed,int dmg, int id, int projectileType) {
        pos = new Point2D.Float(x,y);
        this.id = id;
        this.projectileType = projectileType;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
    }

    public void move() {
        pos.x += xSpeed;
        pos.y += ySpeed;
    }

    public int getId() {
        return id;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public boolean isActive() {
        return active;
    }

    public int getDmg() {
        return dmg;
    }

    public void setActive(boolean b) {

    }
}
