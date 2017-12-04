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

    public Key getKey() {
        return key;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setPlayerID(int ID) {
        playerID = ID;
    }
}
