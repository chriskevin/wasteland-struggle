/**
 * This class is as of now complete. Don't make changes unless necessary!
 */

package game.gameobjects.characters;

import game.gameobjects.AI;
import game.gameobjects.Character;
import java.util.ArrayList;

public class NPC extends Character implements AI {

    /**
    * This Construcor takes 2 arguments. X and Y which becomes the MutantPC
    * start positions.
    * @param x
    * @param y
    */
    public NPC(int x, int y) {
        super(x, y);
    }

    /**
    * This Constructor takes 4 arguments; x coordinate, y coordinate, widht and imagePath;
    * Width and height determines the object logical size in the game.
    * @param x
    * @param y
    * @param width
    * @param height
    */
    public NPC(int x, int y, int width, int height) {
        super(x, y, width, height);
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
    public NPC(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    /**
    * This method is the interface method from the AI class. It is also described
    * in each class that extends from NPC, such as Behemoth. It determines what
    * The act method() describes what the general NPC behaviour is, the NPC logics.
    * (NOT IMPLMENTED)
    */
    public void act(ArrayList players) {

    }

}
