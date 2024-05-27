package input;

import main.Game;
import main.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class myMouseListener implements MouseListener, MouseMotionListener {

    private Game game;

    public myMouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

           switch(GameState.gameState) {
               case MENU:
                    game.getMenu().mouseClicked(e.getX(), e.getY());
                   break;
               case PLAYING:
                   game.getPlaying().mouseClicked(e.getX(), e.getY());
                   break;
               case SETTINGS:
                   game.getSetting().mouseClicked(e.getX(), e.getY());
                   break;
                default:
                   break;
           }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch(GameState.gameState) {
            case MENU:
                game.getMenu().mousePressed(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mousePressed(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSetting().mousePressed(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(GameState.gameState) {
            case MENU:
                game.getMenu().mouseRelease(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseRelease(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSetting().mouseRelease(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        switch(GameState.gameState) {
            case MENU:
                game.getMenu().mouseMoved(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseMoved(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSetting().mouseMoved(e.getX(), e.getY());
                break;
            default:
                break;
        }

    }
}
