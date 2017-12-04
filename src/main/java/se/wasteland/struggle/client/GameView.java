package se.wasteland.struggle.client;

import se.wasteland.struggle.Camera;
import se.wasteland.struggle.HUD;
import se.wasteland.struggle.Level;
import se.wasteland.struggle.data.GameState;
import se.wasteland.struggle.gameobjects.Item;
import se.wasteland.struggle.gameobjects.characters.NPC;
import se.wasteland.struggle.gameobjects.items.Box;
import se.wasteland.struggle.gameobjects.items.Rakh;
import se.wasteland.struggle.screens.Screen;
import se.wasteland.struggle.sound.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

public class GameView extends Screen implements ActionListener {

    /**
     * camera   Game camera used with level.
     * level    The client side graphical representation of the level.
     * hud      The HUD shows player stats.
     * musicPlayer reference to the musicPlayer.
     * play     control variable.
     * musicTimer Timer.
     */
    private Camera camera;
    private Level level;
    private HUD hud;
    private Player musicPlayer;
    private boolean play = true;
    private Timer musicTimer = new Timer(1000, this);

    /**
     * Class constructor which initiates the game view.
     */
    public GameView() {
        level = new Level();
        camera = new Camera(level);
        musicPlayer = new Player();

        add(level);

        camera.setTarget(level.players.get(0));
        musicTimer.start();

        setBounds(0, 0, 800, 600);
    }

    /**
     * This method is used to restrict how many sounds
     * that can be played.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        play = true;
    }

    /**
     * This method reads a GameState and updates all game objects
     * with the data from the GameState.
     * @param gameState
     */
    public void readGameState(GameState gameState) {
        removeObjects(gameState.removeItems);
        spawnObjects(gameState.spawnItems);

        playSounds(gameState.soundFile, gameState.playerID);

        updateScore(gameState.playerScore);
        updateItemPositions(gameState.items);
        updatePlayerPositions(gameState.playerCoords);
        updateNPCPositions(gameState.NPCS);

        camera.setTarget(level.players.get(gameState.playerID));
        camera.follow();
        level.repaint();

        for (int i = 0; i < level.items.size(); i++) {
            int refID = level.items.get(i).getRefID();
            int x = level.items.get(i).getX();
            int y = level.items.get(i).getY();
        }
    }

    /**
     * This method plays the assigned sound file. Read
     * from the game state.
     * @param soundFile
     * @param playerID
     */
    private void playSounds(int[][] soundFile, int playerID) {
        if (soundFile[playerID][0] != 0 && play) {
            musicPlayer.playChannel2(soundFile[playerID][0]);
            soundFile[playerID][0] = 0;
            play = false;
        }
    }

    /**
     * This method removes all objects that the game state has assigned.
     * @param objects
     */
    private void removeObjects(List<Integer> objects) {
        for (int i = 0; i < objects.size(); i++) {
            int refID = objects.get(i);

            for (int j = 0; j < level.items.size(); j++) {
                Item item = level.items.get(j);

                if (item.getRefID() == refID) {
                    level.items.remove(item);
                    break;
                }
            }
        }
    }

    /**
     * This method sets the gameView hud to the HUD invocation argument.
     * @param hud
     */
    public void setHUD(HUD hud) {
        this.hud = hud;
    }

    /**
     * This method spawns Boxes and RAhks.
     * @param objects
     */
    private void spawnObjects(List<String[]> objects) {
        for (int i = 0; i < objects.size(); i++) {
            int x = Integer.parseInt(objects.get(i)[0]);
            int y = Integer.parseInt(objects.get(i)[1]);
            String type = objects.get(i)[2];
            int refID = Integer.parseInt(objects.get(i)[3]);

            if (type.equals("box")) {
                Box box = new Box(x, y, 100, 100, "/images/crate.png");
                level.spawnItem(box, refID);
            } else if (type.equals("rakh")) {
                Rakh egg = new Rakh(x, y, 100, 100, "/images/egg.gif");
                level.spawnItem(egg, refID);
            }
        }
    }

    /**
     * This method updates the positions of the Boxes and RAhks
     * @param items
     */
    private void updateItemPositions(List<int[]> items) {
        for (int i = 0; i < items.size(); i++) {
            int refID = items.get(i)[0];
            int x = items.get(i)[1];
            int y = items.get(i)[2];

            for (int j = 0; j < level.items.size(); j++) {
                Item item = level.items.get(j);

                if (item.getRefID() == refID) {
                    item.setX(x);
                    item.setY(y);
                    break;
                }
            }
        }
    }

    /**
     * This methid updates positions of all NPCs in the game.
     * @param npcs
     */
    private void updateNPCPositions(List<int[]> npcs) {
        for (int i = 0; i < npcs.size(); i++) {
            int refID = npcs.get(i)[0];
            int x = npcs.get(i)[1];
            int y = npcs.get(i)[2];

            for (int j = 0; j < level.npcs.size(); j++) {
                NPC npc = level.npcs.get(j);

                if (npc.getRefID() == refID) {
                    npc.setX(x);
                    npc.setY(y);
                    break;
                }
            }
        }
    }

    /**
     * This method updates all players positions.
     * @param playerCoords
     */
    private void updatePlayerPositions(int[][] playerCoords) {
        for (int i = 0; i < level.players.size(); i++) {
            level.players.get(i).setX(playerCoords[i][0]);
            level.players.get(i).setY(playerCoords[i][1]);
        }
    }

    /**
     * This method updates all player scoores. 
     */
    private void updateScore(int[] score) {
        if (hud != null) {
            hud.player1Score.setText("Player 1 " + score[0]);
            hud.player2Score.setText("Player 2 " + score[1]);
            hud.player3Score.setText("Player 3 " + score[2]);
            hud.player4Score.setText("Player 4 " + score[3]);
        }
    }
}
