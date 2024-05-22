package ui;

import java.awt.*;

public class buttonClass {

    private int x, y, width, height;
    private String text;

    private Rectangle bounds;


    public buttonClass(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initBound();
    }

    public void draw(Graphics  g) {

        g.setColor(Color.GREEN);
        g.fillRect(x,y,width,height);

        g.setColor(Color.yellow);
        g.drawRect(x,y,width,height);

        drawText(g);


    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text , x - w/2 + width/2, y+ h/2 + height/2);
    }

    public void initBound() {
        this.bounds = new Rectangle(x,y,width,height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

}
