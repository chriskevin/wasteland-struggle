/*
 * This class is as of now complete. Don't make changes unless necessary!
 */

package game.gameobjects;

public class Item extends GameObject {

    public Item() {}

    /**
     * This Constructor requires all 5 arguments; x coordinate, y coordinate, widht, height,imagePath;
     * Width and height determines the object logical size in the game. imagePath is the
     * location of the desired game object picture file.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param imagePath
     */
    public Item(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }
}