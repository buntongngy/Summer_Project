package main;

public enum GameStates {

	PLAYING, MENU, SETTINGS, EDIT, Game_Over;

	public static GameStates gameState = MENU;

	public static void SetGameState(GameStates state) {
		gameState = state;
	}

}
