package se.wasteland.struggle.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    /**
     * playerID     The reciepients ID.
     * playerScore  The players scores.
     * playerCoords The players coordinates.
     * soundFile    The sound file to play.
     * gameOver     If the game is over or not.
     * pause        If the games is paused.
     * NPCS          Information about all NPCs
     * items        The items contained in the current level.
     * removeItems  The items to remove.
     * removeNPCs   The NPCs to remove.
     * spawnItems   The items to spawn.
     */

    public int playerID;
    public int[] playerScore = new int[4];
    public int[][] playerCoords = new int[4][2];
    public int[][] soundFile = new int [4][4];
    public boolean gameOver = false;
    public int pause = 0;

    public List<int[]> NPCS = new ArrayList<>();
    public List<int[]> items = new ArrayList<>();
    public List<Integer> removeItems = new ArrayList<>();
    public List<Integer> removeNPCs  = new ArrayList<>();
    public List<String[]> spawnItems = new ArrayList<>();
}
