package main;

import input.myKeyListener;
import input.myMouseListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

    private Random random;
    private Game game;
    private Dimension size;
    private myMouseListener myMouseListener;
    private myKeyListener myKeyListener;

    private Render render;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();

    }

    public void initInput() {
        myMouseListener = new myMouseListener(game);
        myKeyListener = new myKeyListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(myKeyListener);

        requestFocus();
    }

    public void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);

    }





}