package input;

import main.Game;
import main.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameState;
import screen.Setting;

import static main.GameState.*;

public class myKeyListener implements KeyListener {
    private Game game;

    public myKeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(gameState == EDIT) {
           game.getEditor().keyPressed(e);
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
