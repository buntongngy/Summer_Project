package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

    private Random random;
    private Game game;
    private Dimension size;

    private Render render;

    public GameScreen(Game game) {
        this.game = game;
        render = new Render(this);
        setPanelSize();

    }



    public void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        render.render(g);

    }





}