package screen;

import helperPackage.LevelBuild;
import main.Game;
import manager.TileManager;
import ui.bottomBar;

import java.awt.*;

import static main.GameState.PLAYING;
import static main.GameState.setGameState;

public class Playing extends GameScreen implements ScreenMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private bottomBar bottomBar;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new bottomBar(0, 640, 640, 100, this);
    }

    @Override
    public void render(Graphics g) {

        for(int y=0; y <lvl.length; y++) {
            for (int x = 0; x <lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*32, y*32, null);
            }

        }
        bottomBar.draw(g);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y>=640) {
            bottomBar.mouseMoved(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y>=640) {
            bottomBar.mouseMoved(x, y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y>=640) {
            bottomBar.mouseMoved(x, y);
        }
    }

    @Override
    public void mouseRelease(int x, int y) {

            bottomBar.mouseMoved(x, y);

    }
}
