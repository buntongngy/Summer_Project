package manager;

import helperPackage.LoadedSave;
import helperPackage.imgFix;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helperPackage.Constants.Tile.*;

public class TileManager {

    public Tile GRASS, WATER, BL_Water_Corner, BR_Water_Corner, TL_Water_Corner, TR_Water_Corner, L_Water, R_Water, T_Water, B_Water,
     ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R, TL_ISLE, TR_ISLE, BR_ISLE, BL_ISLE;
    public BufferedImage atlas;

    public ArrayList<Tile> tiles = new ArrayList<>();
    public ArrayList<Tile> roadS = new ArrayList<>();
    public ArrayList<Tile> roadC = new ArrayList<>();
    public ArrayList<Tile> corner = new ArrayList<>();
    public ArrayList<Tile> beach = new ArrayList<>();
    public ArrayList<Tile> island = new ArrayList<>();



    public TileManager() {

        loadAtlas();
        createTiles();

    }

    private void createTiles() {
        int id = 0;

        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, GRASS_TILE));
        tiles.add(WATER = new Tile(getAnimeSprite(0, 0), id++, WATER_TILE));
        tiles.add(ROAD_LR = new Tile(getSprite(8,0), id++, ROAD_TILE));

        roadS.add(ROAD_LR = new Tile(getSprite(8, 0), id++, ROAD_TILE));
        roadS.add(ROAD_TB = new Tile(imgFix.getRotateImg(getSprite(8, 0), 90), id++, ROAD_TILE));

        roadC.add(ROAD_B_TO_R = new Tile(getSprite(7, 0), id++, ROAD_TILE));
        roadC.add(ROAD_L_TO_B = new Tile(imgFix.getRotateImg(getSprite(7, 0), 90), id++, ROAD_TILE));
        roadC.add(ROAD_L_TO_T = new Tile(imgFix.getRotateImg(getSprite(7, 0), 180), id++, ROAD_TILE));
        roadC.add(ROAD_T_TO_R = new Tile(imgFix.getRotateImg(getSprite(7, 0), 270), id++, ROAD_TILE));

        corner.add(BL_Water_Corner = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(5, 0), 0), id++, WATER_TILE));
        corner.add(TL_Water_Corner = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(5, 0), 90), id++, WATER_TILE));
        corner.add(TR_Water_Corner = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(5, 0), 180), id++, WATER_TILE));
        corner.add(BR_Water_Corner = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(5, 0), 270), id++, WATER_TILE));

        beach.add(T_Water = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(6, 0), 0), id++, WATER_TILE));
        beach.add(R_Water = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(6, 0), 90), id++, WATER_TILE));
        beach.add(B_Water = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(6, 0), 180), id++, WATER_TILE));
        beach.add(L_Water = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(6, 0), 270), id++, WATER_TILE));

        island.add(TL_ISLE = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(4, 0), 0), id++, WATER_TILE));
        island.add(TR_ISLE = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(4, 0), 90), id++, WATER_TILE));
        island.add(BR_ISLE = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(4, 0), 180), id++, WATER_TILE));
        island.add(BL_ISLE = new Tile(imgFix.getBuildRotImg(getAnimeSprite(0, 0), getSprite(4, 0), 270), id++, WATER_TILE));

        tiles.addAll(roadS);
        tiles.addAll(roadC);
        tiles.addAll(corner);
        tiles.addAll(beach);
        tiles.addAll(island);

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

    public BufferedImage getAnimationSprite(int id, int animeIndex) {
        return tiles.get(id).getSprite(animeIndex);
    }

    public BufferedImage[] getAnimeSprite(int xCord, int yCord) {
      BufferedImage[] arr = new BufferedImage[4];
      for (int i = 0; i<4; i++) {
          arr[i] = getSprite(xCord + i, yCord);
      }

      return arr;
    }

    public boolean isSpriteAnime(int spriteId) {
       return tiles.get(spriteId).isAnime();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32,32);
    }

    public ArrayList<Tile> getBeach() {
        return beach;
    }

    public ArrayList<Tile> getCorner() {
        return corner;
    }

    public ArrayList<Tile> getIsland() {
        return island;
    }

    public ArrayList<Tile> getRoadC() {
        return roadC;
    }

    public ArrayList<Tile> getRoadS() {
        return roadS;
    }
}
