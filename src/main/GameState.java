package main;

public enum GameState {

    PLAYING, MENU, SETTINGS;

    public static GameState gameState = MENU;

    public static void setGameState(GameState state) {
        gameState = state;
    }
}
