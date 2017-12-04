package se.wasteland.struggle.client;

import se.wasteland.struggle.Key;

import java.io.Serializable;

public class ClientData extends Object implements Serializable {
    /**
     * playerID The ID of the player.
     * key      The key that was pressed or released.
     */
    private int playerID = 0;
    private Key key;

    public ClientData() {
        key = new Key(99, "");
    }

    /**
     * This method returns the key.
     * @return
     */
    public Key getKey() {
        return key;
    }

    /**
     * This method returns the player ID.
     * @return
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * This method sets the key.
     * @param key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * This method sets the player ID.
     * @param ID
     */
    public void setPlayerID(int ID) {
        playerID = ID;
    }
}
