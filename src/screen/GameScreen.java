package screen;

import main.Game;

public class GameScreen {

    Game game;

    public  GameScreen(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
