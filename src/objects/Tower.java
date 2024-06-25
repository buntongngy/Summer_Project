package objects;

import helpz.Constants;

public class Tower {

    private int x, y, id, towerType, cdTick,dmg;
    private float range, coolDown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCoolDown();
    }

    public void update() {
        cdTick++;
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

    public float getRange() {return range;}

    public float getCoolDown() {return coolDown;}

    public boolean isCoolDownOver() {return cdTick  >= coolDown;}

    public void resetCoolDown() {cdTick = 0;}

}
