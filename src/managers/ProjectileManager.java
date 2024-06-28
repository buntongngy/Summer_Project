package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Projectile.*;
import static helpz.Constants.Towers.*;

public class ProjectileManager {


    private Playing playing;
    private ArrayList<Projectile> projectile = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private BufferedImage[] projectileImg, explosionImg;
    private Boolean drawBomb = false;
    private int bombTick, bombIndex;
    private Point2D.Float bombPos;

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
        importExplosion(atlas);
    }

    private void importExplosion(BufferedImage atlas) {

        explosionImg = new BufferedImage[7];

        for (int i = 0; i < 7; i++) {
            explosionImg[i] = atlas.getSubimage(i*32, 32*2,32,32);
        }

    }


    public void newProjectile(Tower t, Enemy e) {

        int type = getProjectTileType(t);
       int xDist = (int)(t.getX() - e.getX());
       int yDist = (int)(t.getY()- e.getY());
       int toDist = Math.abs(xDist) + Math.abs(yDist);

       float xPercent = (float) Math.abs(xDist) / toDist;

       float xSpeed = xPercent * Constants.Projectile.GetSpeed(type);
       float ySpeed =  Constants.Projectile.GetSpeed(type) - xSpeed;

       if (t.getX() > e.getX())
           xSpeed *= -1;
       if (t.getY() > e.getY())
           ySpeed *= -1;

       float rotate = 0;

       if (type == ARROW) {
           float arcValue = (int)Math.atan(yDist / (float)xDist);
             rotate = (float) Math.toDegrees(arcValue);
           if (xDist < 0)
               rotate += 180;
       }





      projectile.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, projectileId++, type));

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
                    if(p.getProjectileType() == BOMB) {
                        explosions.add(new Explosion(p.getPos()));
                        explodeOnEnemy(p);
                    }
                } else {

                }
            }
        for (Explosion e : explosions)

            if (e.getIndex()<7) {
                e.update();

            }

    }

    private void explodeOnEnemy(Projectile p) {

        for(Enemy e : playing.getEnemyManager().getEnemies()){

            if(e.isAlive()) {
                float radius = 40.0f;

                float xDist = Math.abs(p.getPos().x - e.getX());
                float yDist = Math.abs(p.getPos().y - e.getY());

                float realDist = (float) Math.hypot(xDist, yDist);

                if (realDist <= radius) {
                    e.hurt(p.getDmg());
                }
            }

        }

    }

    private boolean isProjectileHit(Projectile p) {

        for(Enemy e : playing.getEnemyManager().getEnemies()){
            if(e.isAlive()) {
            if (e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                return true;
            }
        }}
        return false;

    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for (int i=0; i <explosionImg.length;i++) {

            g2d.drawImage(explosionImg[i], 300 + i * 32, 300, null);

        }

        for (Projectile p: projectile) {
            if (p.isActive()) {
                if (p.getProjectileType() == ARROW) {
                    g2d.translate(p.getPos().x, p.getPos().y);
                    g2d.rotate(Math.toRadians(90));
                    g2d.drawImage(projectileImg[p.getProjectileType()], -16, -16, null);
                    g2d.rotate(Math.toRadians(90));
                    g2d.translate(-p.getPos().x, -p.getPos().y);

                } else {
                    g2d.drawImage(projectileImg[p.getProjectileType()], (int) p.getPos().x - 16, (int) p.getPos().y - 16, null);
                }
            }
        }
        drawBomb(g2d);
    }

    private void drawBomb(Graphics2D g2d) {

       for(Explosion e: explosions)
           if(e.getIndex() < 7) {
                g2d.drawImage(explosionImg[e.getIndex()], (int)e.getPos().x - 16,(int)e.getPos().y - 16,null);
           }
    }

    public class Explosion {

        private Point2D.Float pos;
        private int bombTick = 0, bombIndex = 0;
        public Explosion(Point2D.Float pos) {
            this.pos = pos;
        }

        public void update() {

                bombTick++;
                if (bombTick >= 12) {
                    bombTick = 0;
                    bombIndex++;

                }
        }

        public int getIndex() {
            return bombIndex;
        }

        public Point2D.Float getPos() {
            return pos;
        }
    }

}
