package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class myKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A is click");
        } else if (e.getKeyCode() == KeyEvent.VK_B){
            System.out.println("B is click");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
