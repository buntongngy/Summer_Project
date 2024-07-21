package objects;

import helpz.Constants;

import static helpz.Constants.Towers.*;

public class Tower {

    private int x, y, id, towerType, cdTick,dmg, tier;
    private float range, coolDown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        tier = 1;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCoolDown();
    }

    public void update() {
        cdTick++;
    }

    public void upgradeTower() {
        tier++;

        switch(towerType) {
            case ARCHER:
                dmg += 10;
                range += 30;
                coolDown -= 5;
            case CANNON:
                dmg += 10;
                range += 20;
                coolDown -= 10;
            case WIZARD:
                dmg += 5;
                range += 25;
                coolDown -= 5;
        }

    }

    private void setDefaultCoolDown() {
        coolDown = Constants.Towers.GetDefaultCoolDown(towerType);
    }

    private void setDefaultRange() {
        range = Constants.Towers.GetDefaultRange(towerType);
    }

    private void setDefaultDmg() {
        dmg = Constants.Towers.GetStartDmg(towerType);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getTowerType() {
        return towerType;
    }

    public int getDmg() {return dmg; }

    public int getTier() {return tier; }

    public float getRange() {return range;}

    public float getCoolDown() {return coolDown;}

    public boolean isCoolDownOver() {return cdTick  >= coolDown;}

    public void resetCoolDown() {cdTick = 0;}

}
