package se.wasteland.struggle.gameobjects.items;

import se.wasteland.struggle.gameobjects.Item;

import java.util.ArrayList;
import java.util.List;

public class Box extends Item {

    private List<Item> items = new ArrayList<>();

    public Box(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    public void addContent(Item item) {
        items.add(item);
    }

    public List<Item> getContents() { return items; }
}
