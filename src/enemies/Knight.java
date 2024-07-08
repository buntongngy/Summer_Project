package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.KNIGHT;

public class Knight extends Enemy {

	public Knight(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, KNIGHT, enemyManager);

	}

}
