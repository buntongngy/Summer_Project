package objects;

import java.awt.geom.Point2D;

public class Projectile {

    private Point2D.Float pos;
    private int id, projectileType, dmg;
    private boolean active = true;
    private float xSpeed, ySpeed, rotation;

    public Projectile(float x, float y, float xSpeed, float ySpeed,int dmg, float rotation, int id, int projectileType) {
        pos = new Point2D.Float(x,y);
        this.id = id;
        this.projectileType = projectileType;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.rotation = rotation;
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

    public float getRotation() {
        return rotation;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
