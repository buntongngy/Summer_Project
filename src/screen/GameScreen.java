package screen;

import main.Game;

import java.awt.image.BufferedImage;

public class GameScreen {

    Game game;
    protected int tick;
    protected int animeIndex;
    protected int ANIMATION_SPEED = 25;

    public  GameScreen(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    protected boolean isAnimation(int spriteId) {
        return game.getTileManager().isSpriteAnime(spriteId);
    }

    protected void updateTick() {
        tick++;
        if (tick >= 20) {
            tick = 0;
            animeIndex++;
            if (animeIndex >= 4) {
                animeIndex =0;
            }
        }
    }

    protected BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    protected BufferedImage getSprite(int spriteId, int animeIndex) {
        return game.getTileManager().getAnimationSprite(spriteId, animeIndex);
    }
}
