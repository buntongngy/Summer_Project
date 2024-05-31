package manager;

import helperPackage.LoadedSave;
import helperPackage.imgFix;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    public Tile GRASS, WATER, BL_Water_Corner, BR_Water_Corner, TL_Water_Corner, TR_Water_Corner, L_Water, R_Water, T_Water, B_Water,
    ROAD, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R, TL_ISLE, TR_ISLE, BR_ISLE, BL_ISLE;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();


    public TileManager() {

        loadAtlas();
        createTiles();

    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9,0), id++, "Grass"));
        tiles.add(WATER = new Tile(getSprite(1,0), id++ , "Water"));
        tiles.add(ROAD = new Tile(getSprite(8,0), id++, "Road"));

        tiles.add(ROAD_TB = new Tile(imgFix.getRotateImg(getSprite(8,0), 90), id++, "TB_Road"));
        tiles.add(ROAD_B_TO_R = new Tile(getSprite(7,0), id++, "Road_Bottom_To_Right"));

        tiles.add(ROAD_L_TO_B = new Tile(imgFix.getRotateImg(getSprite(7,0), 90), id++, "Road_Left_To_Bottom"));
        tiles.add(ROAD_L_TO_T = new Tile(imgFix.getRotateImg(getSprite(7,0), 180), id++, "Road_Left_To_Top"));
        tiles.add(ROAD_T_TO_R = new Tile(imgFix.getRotateImg(getSprite(7,0), 270), id++, "Road_Top_To_Right"));

        tiles.add(BL_Water_Corner = new Tile(imgFix.buildImg(getImg(0,0,5,0)), id++, "BL_Water_Corner"));
        tiles.add(BR_Water_Corner = new Tile(imgFix.getBuildRotImg(getImg(0,0,5,0), 270, 1), id++, "BR_Water_Corner"));
        tiles.add(TL_Water_Corner = new Tile(imgFix.getBuildRotImg(getImg(0,0,5,0), 90, 1), id++, "TL_Water_Corner"));
        tiles.add(TR_Water_Corner = new Tile(imgFix.getBuildRotImg(getImg(0,0,5,0), 180, 1), id++, "TR_Water_Corner"));

        tiles.add(T_Water = new Tile(imgFix.buildImg(getImg(0,0,6,0)), id++, "T_Water"));
        tiles.add(R_Water = new Tile(imgFix.getBuildRotImg(getImg(0,0,6,0), 90, 1), id++, "R_Water"));
        tiles.add(B_Water = new Tile(imgFix.getBuildRotImg(getImg(0,0,6,0), 180, 1), id++, "B_Water"));
        tiles.add(L_Water = new Tile(imgFix.getBuildRotImg(getImg(0,0,6,0), 270, 1), id++, "L_Water"));

        tiles.add(TL_ISLE = new Tile(imgFix.buildImg(getImg(0,0,4,0)), id++, "TL_Isle"));
        tiles.add(TR_ISLE = new Tile(imgFix.getBuildRotImg(getImg(0,0,4,0), 90, 1), id++, "TR_Isle"));
        tiles.add(BR_ISLE = new Tile(imgFix.getBuildRotImg(getImg(0,0,4,0), 180, 1), id++, "BR_Isle"));
        tiles.add(BL_ISLE = new Tile(imgFix.getBuildRotImg(getImg(0,0,4,0), 270, 1), id++, "BL_Isle"));
    }

    private BufferedImage[] getImg(int firstX, int firstY, int secondX, int secondY) {

        return new BufferedImage[] {getSprite(firstX, firstY), getSprite(secondX,secondY)};

    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    private void loadAtlas() {
        atlas = LoadedSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32,32);
    }
}
