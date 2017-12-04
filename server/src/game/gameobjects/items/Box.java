package game.gameobjects.items;

import game.gameobjects.Item;
import java.util.ArrayList;

public class Box extends Item {
    private ArrayList<Item> contents = new ArrayList();

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

    public void addContent(Item item) {
        contents.add(item);
    }

    public ArrayList getContents() {
        return contents;
    }

    public void removeContents() {
        contents.clear();
    }
}
