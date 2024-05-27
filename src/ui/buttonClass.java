package ui;

import java.awt.*;

public class buttonClass {

    public int x, y, width, height, id;
    private String text;

    private Rectangle bounds;
    private Boolean mouseOver = false;
    private Boolean mousePress = false;

    //For normal Btn
    public buttonClass(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;
        initBound();
    }

    //For tile Btn
    public buttonClass(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        initBound();
    }

    public void draw(Graphics  g) {

        drawBody(g);

        drawBoarder(g);

        drawText(g);

    }

    private void drawBoarder(Graphics g) {
        g.setColor(Color.yellow);
        g.drawRect(x,y,width,height);
        if (mousePress) {
            g.drawRect(x + 1, y+1, width - 2, height -2);
            g.drawRect(x + 2, y+2, width - 4, height -4);
        }
    }

    private void drawBody(Graphics g) {
        if (mouseOver) {
            g.setColor((Color.GRAY));
        } else {
            g.setColor(Color.GREEN);
        }
        g.fillRect(x,y,width,height);

    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text , x - w/2 + width/2, y+ h/2 + height/2);
    }

    public void resetBoolean() {
        this.mousePress = false;
        this.mouseOver = false;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMousePress(boolean mousePress) {
        this.mousePress = mousePress;
    }

    public boolean isMousePress() {
        return mousePress;
    }


    public void initBound() {
        this.bounds = new Rectangle(x,y,width,height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getId() {
        return id;
    }

}
