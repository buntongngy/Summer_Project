package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.WOLF;

public class Wolf extends Enemy {

	public Wolf(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, WOLF, enemyManager);

	}

}
