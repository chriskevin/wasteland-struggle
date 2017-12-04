/**
 * This class is as of now complete. Don't make changes unless necessary!
 */

package se.wasteland.struggle.gameobjects.characters;

import se.wasteland.struggle.gameobjects.AI;
import se.wasteland.struggle.gameobjects.Character;
import java.util.List;

public class NPC extends Character implements AI {

    public NPC(int x, int y) {
        super(x, y);
    }

    public NPC(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public NPC(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    public void act(List players) {

    }

}
