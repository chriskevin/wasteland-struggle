/*
 * This class is as of now complete. Don't make changes unless necessary!
 */

package se.wasteland.struggle.data;

import java.util.ArrayList;
import java.util.List;

public class MapData {

    /**
     * levelNo  The levels number.
     * map      Contains the maps width, height, tile dimensions.
     * metadata Metadata about the level (game name, version, date, author).
     * tiles    All tiles of the level.
     */
    private int levelNo;
    private String[] map = new String[3];
    private String[] metadata = new String[4];

    private List<String[][]> tiles = new ArrayList<>();

    public int getLevelNo() {
        return levelNo;
    }

    public String[] getMap() {
        return map;
    }

    public String[] getMetadata() {
        return metadata;
    }

    public List<String[][]> getTiles() {
        return tiles;
    }

    public void setLevelNo(int number) {
        levelNo = number;
    }

    public void setMap(String width, String height, String dimension) {
        map[0] = width;
        map[1] = height;
        map[2] = dimension;
    }

    public void setMetadata(String game, String version, String date, String author) {
        metadata[0] = game;
        metadata[1] = version;
        metadata[2] = date;
        metadata[3] = author;
    }

    public void setTileData(String tileType, String texture) {
        String[][] tileData = new String[2][4];
        tileData[0][0] = tileType;
        tileData[0][1] = texture;

        for (int i = 0; i < tileData[1].length; i++)
            tileData[1][i] = "";

        tiles.add(tileData);
    }
    
    /**
     * This method builds a string array with all of the
     * tiles attributes and adds it to the tile list.
     * @param tileType
     * @param texture
     * @param contentType
     * @param ID
     * @param depth
     * @param margin
     */
    public void setTileData(String tileType, String texture, String contentType, String ID, String depth, String margin) {
        String[][] tileData = new String[2][4];
        tileData[0][0] = tileType;
        tileData[0][1] = texture;

        tileData[1][0] = contentType;
        tileData[1][1] = ID;
        tileData[1][2] = depth;
        tileData[1][3] = margin;

        tiles.add(tileData);
    }
}
