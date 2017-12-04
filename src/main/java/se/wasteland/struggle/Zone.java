package se.wasteland.struggle;

import se.wasteland.struggle.gameobjects.Item;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

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

    private List<Item> items = new ArrayList<>();

    public Zone(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}