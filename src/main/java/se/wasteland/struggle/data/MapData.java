/*
 * This class is as of now complete. Don't make changes unless necessary!
 */

package se.wasteland.struggle.data;

import java.util.ArrayList;

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

    private ArrayList<String[][]> tiles = new ArrayList();

    /**
     * This method retrives the levels number.
     * @return The level number.
     */
    public int getLevelNo() {
        return levelNo;
    }

    /**
     * This method retrives the general map data.
     * @return The map data (size, dimension).
     */
    public String[] getMap() {
        return map;
    }

    /**
     * This method retrives the maps metadata.
     * @return The map metadata.
     */
    public String[] getMetadata() {
        return metadata;
    }

    /**
     * This method returns the list of tiles.
     * @return The list of tiles.
     */
    public ArrayList<String[][]> getTiles() {
        return tiles;
    }

    /**
     * This method sets the number of the level.
     * @param num
     */
    public void setLevelNo(int number) {
        levelNo = number;
    }

    /**
     * This method sets the general map data.
     * @param size The size of the whole map.
     * @param dimension The dimension of all the tiles.
     */
    public void setMap(String width, String height, String dimension) {
        map[0] = width;
        map[1] = height;
        map[2] = dimension;
    }

    /**
     * This method sets the maps metadata.
     * @param game The name of the game.
     * @param version The version of the map file.
     * @param date The creation date.
     * @param author The author of the map file.
     */
    public void setMetadata(String game, String version, String date, String author) {
        metadata[0] = game;
        metadata[1] = version;
        metadata[2] = date;
        metadata[3] = author;
    }

    /**
     * This method builds a string array with all of the
     * tiles attributes and adds it to the tile list.
     * @param tileType
     * @param texture
     */
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
