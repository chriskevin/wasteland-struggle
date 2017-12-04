package se.wasteland.struggle.gameobjects.characters;

import se.wasteland.struggle.Key;
import se.wasteland.struggle.gameobjects.Playable;

public class MutantPC extends Mutant implements Playable {

    /**
     * This Construcor takes 2 arguments. X and Y which becomes the MutantPC
     * start positions.
     * @param x
     * @param y
     */
    public MutantPC(int x, int y) {
        super(x, y);
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
    public MutantPC(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    /**
     * This method is implemented from the Playable interface. It determines what
     * the result of given key action ( (code)+(state) ) is.
     * @param key
     */
    public void doWhen(Key key) {

        if (key.getState().equals("pressed")) {
            if (key.getKeyCode() == 38) {
                dy = -vel;
            } else if (key.getKeyCode() == 37) {
                dx = -vel;
            } else if (key.getKeyCode() == 40) {
                dy = vel;
            } else if (key.getKeyCode() == 39) {
                dx = vel;
            }
        } else if (key.getState().equals("released")) {
            if (key.getKeyCode() == 38) {
                dy = 0;
            } else if (key.getKeyCode() == 37) {
                dx = 0;
            } else if (key.getKeyCode() == 40) {
                dy = 0;
            } else if (key.getKeyCode() == 39) {
                dx = 0;
            }
        }
    }
}
