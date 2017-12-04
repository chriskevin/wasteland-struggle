/**
 * This class is as of now complete. Don't make changes unless necessary!
 */

package game.gameobjects;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class GameObject implements Serializable {
    /** GameObject class instance variabels.
     *
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

    /**
     * This Constructor requires 2 arguments; x and y coordinates.
     * @param x
     * @param y
     */
    public GameObject(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    /**
     * This Constructor requires 4 arguments; x, y, width and height.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public GameObject(int x, int y, int width, int height) {
        this(x, y);
        this.width  = width;
        this.height = height;
    }

    /**
     * This Constructor requires all 5 object properties; x,y,widht,height,imagePath;
     * Width and height determines the object logical size in the game. imagePath is the
     * location of the desired game object picture file.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param imagePath
     */

    public GameObject(int x, int y, int width, int height, String imagePath) {
        this(x, y, width, height);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
        image = ii.getImage();
    }


    /**
     * This method returns the baseID of the Game Object (Not implemented)
     * @return
     */
    public int getBaseID() {
        return baseID;
    }

    /**
     * This method returns the Boundries of the Game Object. In form of a Rectangle Object
     * (startPosX, startPosY, width and height).
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * This method returns the height of the Game Object.
     * @return
     */
    public int getHeight() {
        return height;
    }
    /**
     * This method returns the image Object of the Game Object. !!! NOT the imagePAth
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * This method returns the Reference ID (Not implemented)
     * @return
     */
    public int getRefID() {
        return refID;
    }
    /**
     * This method returns the X value of the Game Object.
     * @return
     */
    public int getX() {
        return x;
    }
    /**
     * This method returns the Y value of the Game Object.
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * This method returns the width of tje object.
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * This sets the height of the Game Object.
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * This method take 1 argument; a String imagePath.
     * @param imagePath
     */
    public void setImage(String imagePath) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
        image = ii.getImage();
    }

    /**
     * This method sets the game object reference id.
     * @param value
     */
    public void setRefID(int value) {
        refID = value;
    }

    /**
     * This method sets the width of the Game Object.
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * This method sets the x coordinate of the Game Object.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This method sets the y coordinate of the Game Object.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
