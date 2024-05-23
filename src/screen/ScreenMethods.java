package screen;

import java.awt.*;

public interface ScreenMethods {

    public void render(Graphics g);
    public void mouseClicked(int x, int y);
    public void mouseMoved(int x, int y);
    public void mousePressed(int x, int y);
    public void mouseRelease(int x, int y);

}
