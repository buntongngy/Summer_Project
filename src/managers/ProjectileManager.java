package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Projectile.*;
import static helpz.Constants.Towers.*;

public class ProjectileManager {


    private Playing playing;
    private ArrayList<Projectile> projectile = new ArrayList<Projectile>();
    private BufferedImage[] projectileImg;

    private int projectileId = 0;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImg();
    }


    private void importImg() {

        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImg = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            projectileImg[i] = atlas.getSubimage((7+i) * 32, 32, 32, 32);
        }
    }

    public void newProjectile(Tower t, Enemy e) {

        int type = getProjectTileType(t);
       int xDist = (int)Math.abs(t.getX() - e.getX());
       int yDist = (int)Math.abs(t.getY()- e.getY());
       int toDist = xDist + yDist;

       float xPercent = (float) xDist/toDist;

       float xSpeed = xPercent * Constants.Projectile.GetSpeed(type);
       float ySpeed =  Constants.Projectile.GetSpeed(type) - xSpeed;

       if (t.getX() > e.getX())
           xSpeed *= -1;
       if (t.getY() > e.getY())
           ySpeed *= -1;

      projectile.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), projectileId++, type));

    }

    private int getProjectTileType(Tower t) {

        switch (t.getTowerType()) {
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return MAGIC;
        }
        return 0;
    }

    public void update() {

        for (Projectile p: projectile)
            if (p.isActive()) {
                p.move();
                if(isProjectileHit(p)) {
                    p.setActive(false);
                } else {

                }
            }


    }

    private boolean isProjectileHit(Projectile p) {

        for(Enemy e : playing.getEnemyManager().getEnemies()){
            if (e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;

    }

    public void draw(Graphics g) {
        for (Projectile p: projectile)
            if (p.isActive())
            g.drawImage(projectileImg[p.getProjectileType()], (int)p.getPos().x, (int)p.getPos().y, null  );
    }
}
