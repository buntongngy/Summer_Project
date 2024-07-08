package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.ORC;

public class Orc extends Enemy{

	public Orc(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, ORC, enemyManager);
		health = 50;
	}

}
