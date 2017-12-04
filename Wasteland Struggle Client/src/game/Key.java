/*
 * This class is as of now complete. Don't make changes unless necessary!
 */

package game;

import java.io.Serializable;

public class Key implements Serializable {
    /**
     * keyCode The code of the key.
     * state   The state of the key (pressed or released).
     */
    private int keyCode;
    private String state;

    /**
     * The class constructor which requires a key code and a state.
     * @param keyCode The code of the keyboard key.
     * @param state The state of the keyboard key.
     */
    public Key(int keyCode, String state) {
        this.keyCode = keyCode;
        this.state   = state;
    }

    /**
     * This method returns the key code.
     * @return The keys code.
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * This method returns the keys state.
     * @return The state of the key.
     */
    public String getState() {
        return state;
    }
}