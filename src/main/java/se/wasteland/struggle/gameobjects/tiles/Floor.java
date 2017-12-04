package se.wasteland.struggle.gameobjects.tiles;

import se.wasteland.struggle.gameobjects.Tile;

public class Floor extends Tile {
    
    /**
     * The class constructor which takes x, y, width, height and imagePath.
     * @param x         The x coordinate of the floor tile.
     * @param y         The y coordinate of the floor tile.
     * @param width     The width of the floor tile.
     * @param height    The height of the floor tile.
     * @param imagePath File path to an image file of type (gif, jpg, png).
     */
    public Floor(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }
}
