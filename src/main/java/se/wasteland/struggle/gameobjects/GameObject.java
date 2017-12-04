package se.wasteland.struggle.gameobjects;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class GameObject implements Serializable {

    /**
     * baseID, a unique number identifying a certain object. Will be used by the console. (Not implemented)
     * refID, identifies the Game Object type. (Not implemented)
     * height, the Game Object height
     * width, the Game Object width
     * x, the Game Object X coordinate
     * y, the Game Object Y coordinate.
     * image, the Game Object image.
     */
    protected static int count = 0;

    protected int baseID;
    protected int height;
    protected int refID;
    protected int width;
    protected int x;
    protected int y;

    protected Image image;
    protected String imagePath;

    public GameObject() {
        count++;
        refID = count;
    }

    public GameObject(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public GameObject(int x, int y, int width, int height) {
        this(x, y);
        this.width  = width;
        this.height = height;
    }

    public GameObject(int x, int y, int width, int height, String imagePath) {
        this(x, y, width, height);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
        image = ii.getImage();
    }

    public int getBaseID() {
        return baseID;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public int getRefID() {
        return refID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImage(final String imagePath) {
        final ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
        image = ii.getImage();
    }

    public void setRefID(int value) {
        refID = value;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
