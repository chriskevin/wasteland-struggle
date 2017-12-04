package game.gameobjects.items;

import game.gameobjects.Item;

public class Box extends Item {

    /**
     * The class constructor which takes x, y, width, height and imagePath.
     * @param x         The x coordinate of the box.
     * @param y         The y coordinate of the box.
     * @param width     The width of the box.
     * @param height    The height of the box.
     * @param imagePath File path to an image file of type (gif, jpg, png).
     */
    public Box(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }
}
