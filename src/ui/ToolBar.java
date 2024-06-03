package ui;

import helperPackage.LoadedSave;
import objects.Tile;
import screen.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.GameState.MENU;
import static main.GameState.setGameState;

public class ToolBar extends Bar{

    private Editing editing;
    private buttonClass bMenu, bSave;
    private Tile selectedTile;

    //private ArrayList<buttonClass> tileBtn = new ArrayList<>();
    private Map<buttonClass, ArrayList<Tile>> map = new HashMap<buttonClass, ArrayList<Tile>>();
    private buttonClass bGrass, bWater, bRoadS, bRoadC, bWaterC, bWaterB, bWaterI;

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButton();
    }

    private void initButton() {
        bMenu = new buttonClass("Menu", 2, 642, 100, 30);
        bSave = new buttonClass("Save", 2, 674, 100, 30);

        int w = 50;
        int h = 50;
        int xStart = 100;
        int yStart = 640;
        int xOffset = (int) (w * 1.1);
        int i = 0;

        bGrass = new buttonClass("Grass", xStart, yStart, w, h ,i++);
        bWater = new buttonClass("Water", xStart + xOffset, yStart, w, h, i++);

        initMapBtn(bRoadS, editing.getGame().getTileManager().getRoadS(), xStart, yStart, xOffset, w, h, i++);
        initMapBtn(bRoadC, editing.getGame().getTileManager().getRoadC(), xStart, yStart, xOffset, w, h, i++);
        initMapBtn(bWaterC, editing.getGame().getTileManager().getCorner(), xStart, yStart, xOffset, w, h, i++);
        initMapBtn(bWaterB, editing.getGame().getTileManager().getBeach(), xStart, yStart, xOffset, w, h, i++);
        initMapBtn(bWaterI, editing.getGame().getTileManager().getIsland(), xStart, yStart, xOffset, w, h, i++);


    }

    private void initMapBtn(buttonClass b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {

        b = new buttonClass("", x + xOff * id, y, w, h, id);
        map.put(b, list);



    }

    private void saveLevel() {
        editing.saveLevel();
    }



    public void draw(Graphics g) {
        g.setColor(new Color(221, 33, 122));
        g.fillRect(x, y, width, height);

        drawBtn(g);
    }

    private void drawBtn(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);


        drawNormalBtn(g, bGrass);
        drawNormalBtn(g, bWater);
        drawSelectTile(g);
        drawMapBtn(g);
    }

    private void drawNormalBtn(Graphics g, buttonClass b) {

        g.drawImage(getBtnImg(b.getId()), b.x, b.y, b.width, b.height, null);
        drawBtnFeedBack(g, b);
    }

    private void drawMapBtn(Graphics g) {
        for(Map.Entry<buttonClass, ArrayList<Tile>> entry : map.entrySet()) {
            buttonClass b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, b.x, b.y, b.width, b.height, null);
            drawBtnFeedBack(g, b);

        }
    }

    private void drawBtnFeedBack(Graphics g, buttonClass b) {
        // Mouse Over
        if (b.isMouseOver()) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.YELLOW);
        }

        // Border
        g.drawRect(b.x, b.y, b.width, b.height);

        // Mouse Press
        if (b.isMousePress()) {
            g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
            g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
        }
    }

    private void drawSelectTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 640, 50, 50, null);
            g.drawRect(550, 640, 50, 50);
        }
    }

    public BufferedImage getBtnImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            saveLevel();
        } else if (bWater.getBounds().contains(x,y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
            editing.setSelectedTile(selectedTile);
            return;
        } else if (bGrass.getBounds().contains(x,y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
            editing.setSelectedTile(selectedTile);
            return;
        }  else {

            for(buttonClass b : map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    selectedTile = map.get(b).get(0);
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }

        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bWater.setMouseOver(false);
        bGrass.setMouseOver(false);

        for(buttonClass b : map.keySet()) {
            b.setMouseOver(true);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
        } else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMouseOver(true);
        }else if (bWater.getBounds().contains(x, y)) {
            bWater.setMouseOver(true);
        }else {
            for (buttonClass b : map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePress(true);
        } else if(bSave.getBounds().contains(x, y)) {
            bSave.setMousePress(true);
        }else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMousePress(true);
        }else if (bWater.getBounds().contains(x, y)) {
            bWater.setMousePress(true);
        }else {
            for (buttonClass b : map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePress(true);
                    return;
                }
            }
        }
    }

    public void mouseRelease(int x, int y) {
        bMenu.resetBoolean();
        bSave.resetBoolean();
        bWater.resetBoolean();
        bGrass.resetBoolean();
        for (buttonClass b : map.keySet()) {
            b.resetBoolean();
        }
    }
}
