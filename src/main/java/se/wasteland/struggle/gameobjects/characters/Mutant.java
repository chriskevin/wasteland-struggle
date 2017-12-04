package se.wasteland.struggle.gameobjects.characters;

import se.wasteland.struggle.gameobjects.Character;
import se.wasteland.struggle.gameobjects.Item;
import se.wasteland.struggle.gameobjects.items.PowerUp;
import se.wasteland.struggle.gameobjects.items.Rakh;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Mutant extends Character {

    /**
     * items, a list of items that the mutant Carries.
     * lastX, holds the previous x coordinate of the mutant.
     * lastY, holds the previous y coordinate of the mutant.
     * update, boolean to determine when to set the previous X and Y coords.
     *
     */
    protected List<Item> items = new ArrayList<>();
    protected boolean update = true;

    public Mutant(int x, int y) {
        super(x, y);
        width = 111;
        height = 145;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/player_rundown.gif"));
        image = ii.getImage();
    }

    public Mutant(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 25, y + 40, width - 25, height - 40);
    }

    public Item getEgg() {
        return items
                .stream()
                .filter(x -> x instanceof Rakh)
                .findFirst()
                .orElseGet(null);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getPowerUp() {
        return items
                .stream()
                .filter(x -> x instanceof PowerUp)
                .findFirst()
                .orElseGet(null);
    }

    public boolean hasEgg() {
        return items
                .stream()
                .anyMatch(x -> x instanceof Rakh);
    }

    public boolean hasPowerUp() {
        return items
                .stream()
                .anyMatch(x -> x instanceof PowerUp);
    }

    public void move(int X, int Y) {
        x = X;
        y = Y;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
