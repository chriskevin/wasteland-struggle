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

    /**Mutant class instance variables
     *
     * items, a list of items that the mutant Carries.
     * lastX, holds the previous x coordinate of the mutant.
     * lastY, holds the previous y coordinate of the mutant.
     * update, boolean to determine when to set the previous X and Y coords.
     *
     */
    protected List<Item> items = new ArrayList();
    protected boolean update = true;

    /**
     * This Constructor takes 2 arguments; x coordinate and Y coordinate. Beyond setting
     * the X and Y coordinates this constructor sets the Game Object width to 128, the height to 192 and the
     * imagePath/image is "/images/behemoth.gif".
     * @param x
     * @param y
     */
    public Mutant(int x, int y) {
        super(x, y);
        width = 111;
        height = 145;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/player_rundown.gif"));
        image = ii.getImage();
    }

    /**
     * This Constructor requires all 5 arguments; x coordinate, y coordinate, widht, height,imagePath;
     * Width and height determines the object logical size in the game. imagePath is the
     * location of the desired game object picture file.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param imagePath
     */
    public Mutant(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    /**
     * This method adds an item to the items arraylist. The mutant now "carries" the
     * given item.
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Returns the bounds in form of a REctangle object.
     * @return
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 25, y + 40, width - 25, height - 40);
    }

    /**
     * Returns the that is carried, if there is any.
     * @return
     */
    public Item getEgg() {
        Item egg = null;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Rakh) {
                egg = items.get(i);
            }
        }

        return egg;
    }



    /**
     * This method is used to get the item on
     * location s in the items arraylist.
     * @param index
     * @return
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * This method returns the complete list of items that the mutant "carries".
     * @return
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Returns the powerup that is carried, if there is any.
     * @return
     */
    public Item getPowerUp() {
        Item powerUp = null;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof PowerUp) {
                powerUp = items.get(i);
            }
        }

        return powerUp;
    }

    /**
     * Checks if the mutant carries an egg, returns true if it deos, and false otherwise.
     * @return
     */
    public boolean hasEgg() {
        boolean hasEgg = false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Rakh) {
                hasEgg = true;
                break;
            }
        }

        return hasEgg;
    }

    /**
     * Checks if the player has powerups and returns true if it has, and false if it doesn´nt.
     * @return
     */
    public boolean hasPowerUp() {
        boolean hasPowerUp = false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof PowerUp) {
                hasPowerUp = true;
                break;
            }
        }

        return hasPowerUp;
    }

    /**
     * This is the method that actually moves the mutant.
     * @param X
     * @param Y
     */
    public void move(int X, int Y) {
        x = X;
        y = Y;
    }

    /**
     * This method removes the item from the items arraylist at index "item".
     * @param item
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
}
