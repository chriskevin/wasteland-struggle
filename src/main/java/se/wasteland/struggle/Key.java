package se.wasteland.struggle;

import java.io.Serializable;

public class Key implements Serializable {
    /**
     * keyCode The code of the key.
     * state   The state of the key (pressed or released).
     */
    private int keyCode;
    private String state;

    public Key(int keyCode, String state) {
        this.keyCode = keyCode;
        this.state   = state;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getState() {
        return state;
    }
}