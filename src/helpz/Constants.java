package helpz;

import scenes.Playing;

public class Constants {

	public static class Projectile {
		public static final int ARROW = 0;
		public static final int MAGIC = 1;
		public static final int BOMB = 2;

		public static float GetSpeed(int type) {
			switch (type) {
				case BOMB :
					return 4f;
				case ARROW:
					return 8f;
				case MAGIC:
					return 6f;
			}
			return 0f;
		}

	}

	public static class Towers {
		public static final int CANNON = 0;
		public static final int ARCHER = 1;
		public static final int WIZARD = 2;

		public static String GetName(int towerType) {
			switch(towerType) {
				case CANNON:
					return "Cannon";
				case ARCHER:
					return "Archer";
				case WIZARD:
					return "Wizard";
			}
			return "";
		}

		public static int GetTowerCost(int towerType) {
			switch(towerType) {
				case CANNON:
					return 65;
				case ARCHER:
					return 30;
				case WIZARD:
					return 15;
			}
			return 0;
		}

		public static int GetStartDmg(int towerType) {
			switch(towerType) {
				case CANNON:
					return 15;
				case ARCHER:
					return 5;
				case WIZARD:
					return 2;
			}
			return 0;
		}

		public static float GetDefaultRange(int towerType)  {
			switch(towerType) {
				case CANNON:
					return 100;
				case ARCHER:
					return 200;
				case WIZARD:
					return 80;
			}
			return 0;
		}

		public static float GetDefaultCoolDown(int towerType) {
			switch(towerType) {
				case CANNON:
					return 50;
				case ARCHER:
					return 25;
				case WIZARD:
					return 40;
			}
			return 0;
		}
	}


	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class Enemies{
		public static final int ORC = 0;
		public static final int BAT = 1;
		public static final int KNIGHT = 2;
		public static final int WOLF = 3;

		public static int GetReward(int enemyType) {

			switch (enemyType) {
				case ORC:
					return 10;
				case BAT:
					return 5;
				case KNIGHT:
					return 25;
				case WOLF:
					return 15;
			}
			return 0;

		}

		public static float GetSPEED(int enemyType) {
			switch (enemyType) {
				case ORC:
					return 0.5f;
				case BAT:
					return 0.65f;
				case KNIGHT:
					return 0.3f;
				case WOLF:
					return 0.75f;
			}

			return 0;
		}

		public static int GetStartHealth(int enemyType) {

			switch (enemyType) {
				case ORC:
					return 100;
				case BAT:
					return 50;
				case KNIGHT:
					return 200;
				case WOLF:
					return 75;
			}
			return 0;

		}
	}
	
	public static class Tiles{
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}

}
