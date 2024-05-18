package input;

import main.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameState;
import screen.Setting;

import static main.GameState.*;

public class myKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            GameState.gameState = MENU;
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            GameState.gameState = PLAYING;
        }   else if (e.getKeyCode() == KeyEvent.VK_S) {
           GameState.gameState = SETTINGS;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
