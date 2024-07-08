package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.BAT;

public class Bat extends Enemy {

	public Bat(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, BAT, enemyManager);

	}

}
