package game.gameobjects.tiles;

import game.gameobjects.Tile;

public class Trap extends Tile {

    /**
     * The class constructor which takes x, y, width, height and imagePath.
     * @param x         The x coordinate of the trap tile.
     * @param y         The y coordinate of the trap tile.
     * @param width     The width of the trap tile.
     * @param height    The height of the trap tile.
     * @param imagePath File path to an image file of type (gif, jpg, png).
     */
    public Trap(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }
}
