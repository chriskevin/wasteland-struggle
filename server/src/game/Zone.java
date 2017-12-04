package game;

import game.gameobjects.Item;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Zone {
    /**
     * height The zones height.
     * width  The zones width.
     * x      The zones x coordinate which is the start point of the zone.
     * y      The zones y coordinate which is the start point of the zone.
     * items  A list of items which the zone can contain.
     */
    private int height;
    private int width;
    private int x;
    private int y;

    private ArrayList<Item> items = new ArrayList();

    /**
     * Class constructor that requires (x, y , width, height) to create
     * the zones boundries.
     * @param x The zones x coordinate which is the start point of the zone.
     * @param y The zones y coordinate which is the start point of the zone.
     * @param width The zones width.
     * @param height The zones height.
     */
    public Zone(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * This method adds an item to the item list.
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * This method returns the zones boundaries.
     * return A new Rectangle object.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * This method returns an item based on the index value
     * @param index The index value of the item.
     * @return A single item
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * This method returns the list of items.
     * @return The list of items.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * This method returns the zones height.
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method returns the zones width.
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method returns the zones X coordinate.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns the zones Y coordinate.
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * This method removes an item from the item list.
     * @param item The item which should be removed.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
}