package se.wasteland.struggle.server;

import se.wasteland.struggle.Key;
import se.wasteland.struggle.Level;
import se.wasteland.struggle.Zone;
import se.wasteland.struggle.client.ClientData;
import se.wasteland.struggle.data.GameState;
import se.wasteland.struggle.gameobjects.GameObject;
import se.wasteland.struggle.gameobjects.Item;
import se.wasteland.struggle.gameobjects.Tile;
import se.wasteland.struggle.gameobjects.characters.MutantPC;
import se.wasteland.struggle.gameobjects.characters.NPC;
import se.wasteland.struggle.gameobjects.items.Box;
import se.wasteland.struggle.gameobjects.items.PowerUp;
import se.wasteland.struggle.gameobjects.items.Rakh;
import se.wasteland.struggle.gameobjects.items.TimeDialation;
import se.wasteland.struggle.gameobjects.tiles.Obstacle;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class ServerEngine implements ActionListener {

    /**
     * playTime         The duration which you can play.
     * passedTime       The time that has passed.
     * gameState        Reference to the game state.
     * gameStateBuffer  Buffer used before writing to the game state.
     * level            The level containing all objects.
     * gameFlow         The timer which controls the games flow.
     * winTimer         The counter for the play time.
     * winCondition     The conditon for winning.
     */
    private int playTime;
    private int passedTime = 0;
    private GameState gameState;
    private GameState gameStateBuffer;
    private Level level;
    private Timer gameFlow;
    private Timer winTimer;

    private ActionListener winCondition = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (ServerMainWindow.time.isSelected()) {
                if (passedTime > playTime) {
                    System.out.println("Game Over");
                    gameStateBuffer.gameOver = true;
                    updateGameState();
                    winTimer.stop();
                }
            } else if (ServerMainWindow.collect.isSelected()) {
                for (int i = 0; i < level.players.size(); i++) {
                    if (gameState.playerScore[i] == Integer.parseInt(ServerMainWindow.rakhs.getSelectedItem().toString())) {
                        gameStateBuffer.gameOver = true;
                        updateGameState();
                        winTimer.stop();
                    }
                }
            }
            passedTime++;
        }
    };

    /**
     * The class constructor that initiates the engine.
     */
    public ServerEngine() {
        initEngine();
    }

    /**
     * This method controls all game logic.
     * @param evt
     */
    public void actionPerformed(ActionEvent evt) {
        refreshGameStateBuffer();
        spawnRandom();
        checkPowerUps();
        detectCollision();
        movePlayers();
        moveNPCs();
    }

    /**
     * This method checks if a game object is colliding with
     * an item. If collision occurs appropriate actions are called.
     * @param obj The object to check against.
     */
    private void checkItemCollision(GameObject obj) {
        Rectangle objBounds = obj.getBounds();

        for (int i = 0; i < level.items.size(); i++) {
            Item tempItem = level.items.get(i);
            Rectangle itemBounds = tempItem.getBounds();

            if (objBounds.intersects(itemBounds)) {      
                if (obj instanceof MutantPC) {
                    MutantPC player = (MutantPC) obj;
                    eventPlayerItemPickup(player, tempItem);
                }
            }
        }
    }

    /**
     * This method checks if a game object is colliding with
     * an obstacle. If collision occurs appropriate actions are called.
     * @param obj The object to check against.
     */
    private void checkObstacleCollision(GameObject obj) {
        Rectangle objBounds = obj.getBounds();

        for (int i = 0; i < level.tiles.size(); i++) {
            Tile tempTile = level.tiles.get(i);

            if (tempTile instanceof Obstacle) {
                Rectangle obstacleBounds = tempTile.getBounds();

                if (objBounds.intersects(obstacleBounds)) {
                    
                    if (obj instanceof MutantPC) {
                        MutantPC player = (MutantPC) obj;
                        int playerIndex = level.players.indexOf(obj);
                        player.setMovement(false);
                        gameStateBuffer.soundFile[playerIndex][0] = 5;
                    } else if (obj instanceof NPC) {
                        NPC npc = (NPC) obj;
                        npc.setMovement(false);
                    }
                }
            }
        }
    }

    /**
     * This method checks if a game object is colliding with
     * a player. If collision occurs appropriate actions are called.
     * @param obj The object to check against.
     */
    private void checkPlayerCollision(GameObject obj){
        Rectangle objBounds = obj.getBounds();

        for (int i = 0; i < level.players.size(); i++) {
            MutantPC tempPlayer = level.players.get(i);
            Rectangle playerBounds = tempPlayer.getBounds();

            if (objBounds.intersects(playerBounds)) {
                if (obj instanceof MutantPC) {
                    int playerIndex = level.players.indexOf(obj);

                    if (i != playerIndex) {
                        MutantPC player = (MutantPC) obj;
                        player.setMovement(false);
                        gameStateBuffer.soundFile[playerIndex][0] = 7;
                    }
                } else if (obj instanceof NPC) {
                    NPC npc = (NPC) obj;
                    //npc.setMovement(false);
                    gameStateBuffer.soundFile[i][0] = 8;
                    eventPlayerIsAttacked(tempPlayer);
                }
            }
        }
    }

    /**
     * This method check which power ups are active or expired
     * and applies their effect on the right target.
     */
    private void checkPowerUps() {
        for (int i = 0; i < level.players.size(); i++) {
            MutantPC player = level.players.get(i);

            if (player.hasPowerUp()) {
                PowerUp powerUp = (PowerUp) player.getPowerUp();

                // Remove power up if it has expired
                if (powerUp.hasExpired()) {
                    player.removeCondition(powerUp.getEffect());
                    player.removeItem(powerUp);
                } else {
                    // Activate power up if not already done
                    if (!powerUp.isActive())
                        powerUp.activate();

                    // Apply power ups effects
                    if (powerUp instanceof TimeDialation) {
                        TimeDialation timeDialation = (TimeDialation) powerUp;
                        timeDialation.affect(player);
                    }
                }
            }
        }
    }

    /**
     * This method checks if an object spawns on an obstacle.
     * @param box
     * @return
     */
    private boolean checkSpawnWallCollision(Box box) {
        Rectangle boxBounds = box.getBounds();

        for (int i = 0; i < level.tiles.size(); i++) {
            Tile tempTile = level.tiles.get(i);

            if (tempTile instanceof Obstacle) {
                Rectangle obstacleBounds = tempTile.getBounds();
                if (obstacleBounds.intersects(boxBounds)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks if an object spawns in a zone.
     * @param box
     * @return
     */
    private boolean checkSpawnZoneCollision(Box box) {
        Rectangle boxBounds = box.getBounds();

        for (int i = 0; i < level.zones.size(); i++) {
            Rectangle zoneBounds = level.zones.get(i).getBounds();
            if (zoneBounds.intersects(boxBounds)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if a game object is colliding with
     * a zone. If collision occurs appropriate actions are called.
     * @param obj The object to check against.
     */
    private void checkZoneCollision(GameObject obj) {
        Rectangle objBounds = obj.getBounds();

        for (int i = 0; i < level.zones.size(); i++){
            Rectangle zoneBounds = level.zones.get(i).getBounds();

            if (zoneBounds.intersects(objBounds)) {
                if (obj instanceof MutantPC) {
                    eventPlayerInZone((MutantPC) obj, i);
                }
            }
        }
    }

    /**
     * This method is used for running all the
     * collision detection methods.
     */
    private void detectCollision() {
        // Check Players collisions
        for (int i = 0; i < level.players.size(); i++) {
            checkObstacleCollision(level.players.get(i));
            checkItemCollision(level.players.get(i));
            checkZoneCollision(level.players.get(i));
            checkPlayerCollision(level.players.get(i));
        }

        // Check NPC collision
        for (int i = 0; i < level.npcs.size(); i++) {
            checkObstacleCollision(level.npcs.get(i));
            checkPlayerCollision(level.npcs.get(i));
        }
    }

    /**
     * This method lets the player drop an egg in his zone or steal
     * an egg from an opponents zone.
     * @param player
     * @param zoneNo
     */
    private void eventPlayerInZone(MutantPC player, int zoneNo) {
        int playerIndex = level.players.indexOf(player);
        Zone zone = level.zones.get(zoneNo);

        if (playerIndex == zoneNo) {
            List<Item> playerItems = player.getItems();

            for (int i = 0; i < playerItems.size(); i++) {
                Item tempItem = playerItems.get(i);

                if (tempItem instanceof Rakh) {
                    int x = (zone.getX() + (zone.getItems().size() * (tempItem.getWidth() / 2) ) );
                    int y;

                    if (x >= (zone.getX() + zone.getWidth()) )
                        y = (zone.getY() * (zone.getItems().size() / 4) );
                    else
                        y = zone.getY();

                    String[] itemData = {Integer.toString(x), Integer.toString(y),
                        "rakh", Integer.toString(tempItem.getRefID())};

                    int[] itemData2 = {tempItem.getRefID(), x, y};

                    zone.addItem(tempItem);
                    player.removeItem(tempItem);

                    gameStateBuffer.spawnItems.add(itemData);
                    gameStateBuffer.items.add(itemData2);
                    gameStateBuffer.soundFile[playerIndex][0] = 13;
                    gameStateBuffer.playerScore[playerIndex]++;

                    updateGameState();
                }
            }
        } else {
            if (zone.getItems().size() > 0) {
                boolean hasEgg = player.hasEgg();
                Item item = zone.getItem(zone.getItems().size() - 1);

                if (!hasEgg) {
                    zone.removeItem(item);
                    player.addItem(item);
                    gameStateBuffer.playerScore[zoneNo]--;

                    for (int i = 0; i < gameStateBuffer.items.size(); i++) {
                        int[] bufferData = gameStateBuffer.items.get(i);
                        if (item.getRefID() == bufferData[0]) {
                            gameStateBuffer.items.remove(i);
                            break;
                        }
                    }
                    updateGameState();
                }
            }
        }
    }

    /**
     * This method makes the player drop an egg when attacked.
     * @param player
     */
    private void eventPlayerIsAttacked(MutantPC player) {
        boolean hasEgg = player.hasEgg();

        if (hasEgg) {
            Item egg = player.getEgg();
            egg.setX(player.getX() - 100);
            egg.setY(player.getX() - 100);

            level.items.add(egg);
            player.removeItem(egg);

            String[] itemData = {Integer.toString(egg.getX()),
                Integer.toString(egg.getY()),
                "rakh",
                Integer.toString(egg.getRefID())};

            int[] itemData2 = {egg.getRefID(), egg.getX(), egg.getY()};

            gameStateBuffer.spawnItems.add(itemData);
            gameStateBuffer.items.add(itemData2);
        }
    }

    /**
     * This method lets the player pick upp an item.
     * @param player
     * @param item
     */
    private void eventPlayerItemPickup(MutantPC player, Item item) {
        boolean hasEgg = player.hasEgg();
        int playerIndex = level.players.indexOf(player);

        if (item instanceof Rakh) {
            if (!hasEgg) {
                System.out.println("Player picks upp an egg!");
                player.addItem(item);
                gameStateBuffer.removeItems.add(item.getRefID());
                gameStateBuffer.soundFile[playerIndex][0] = 12;

                for (int i = 0; i < gameStateBuffer.items.size(); i++) {
                    int[] bufferData = gameStateBuffer.items.get(i);
                    if (item.getRefID() == bufferData[0]) {
                        gameStateBuffer.items.remove(i);
                        break;
                    }
                }

                level.items.remove(item);
            }
        } else if (item instanceof Box) {
            int quantity = player.getItems().size();
            Box box = (Box) item;

            if (quantity < 2 && hasEgg || quantity < 1 && hasEgg) {
                List<Item> contents = box.getContents();
                player.addItem(contents.get(0));
                gameStateBuffer.removeItems.add(item.getRefID());

                for (int i = 0; i < gameStateBuffer.items.size(); i++) {
                    int[] bufferData = gameStateBuffer.items.get(i);
                    if (item.getRefID() == bufferData[0]) {
                        gameStateBuffer.items.remove(i);
                        break;
                    }
                }

                level.items.remove(item);
            }
        }

        updateGameState();
    }

    /**
     * This method is used for creating the enginethread,
     * the gameflow timer, an instance of the Level class and
     * an instance och the GameStateBuffer class.
     */
    private void initEngine() {
        gameFlow = new Timer(14, this);
        level = new Level();
        gameStateBuffer = new GameState();
        winTimer = new Timer(1, winCondition);
    }

    /**
     * This method moves all NPCs and updates their position data.
     */
    private void moveNPCs() {
        gameStateBuffer.NPCS.clear();

        for (int i = 0; i < level.npcs.size(); i++) {
            if (level.npcs.get(i).getMovement()) {

                level.npcs.get(i).act(level.players);

                int[] npcData = {
                    level.npcs.get(i).getRefID(),
                    level.npcs.get(i).getX(),
                    level.npcs.get(i).getY()};

                level.npcs.get(i).setLastX(level.npcs.get(i).getX());
                level.npcs.get(i).setLastY(level.npcs.get(i).getY());

                gameStateBuffer.NPCS.add(npcData);
                updateGameState();

            } else if (!level.players.get(i).getMovement()) {

                int[] npcData = {
                    level.npcs.get(i).getRefID(),
                    level.npcs.get(i).getLastX(),
                    level.npcs.get(i).getLastY()};

                gameStateBuffer.NPCS.add(npcData);
                updateGameState();

                level.npcs.get(i).setMovement(true);
            }
        }
    }

    /**
     * This method moves all players and updates their position data.
     */
    private void movePlayers() {
        for (int i = 0; i < level.players.size(); i++) {
            if (level.players.get(i).getMovement()) {
                gameStateBuffer.playerCoords[i][0] = level.players.get(i).getX();
                gameStateBuffer.playerCoords[i][1] = level.players.get(i).getY();
                level.players.get(i).move();
                updateGameState();
            } else if (!level.players.get(i).getMovement()) {
                level.players.get(i).move(gameState.playerCoords[i][0], gameState.playerCoords[i][1]);
                level.players.get(i).setMovement(true);
            }
        }
    }

    void pauseGame(){
        if(gameState.pause == 0){
            gameStateBuffer.pause = 1;
            System.out.println("PAUSE");
            gameFlow.stop();
        }
        else if(gameState.pause == 1){
            gameStateBuffer.pause = 0;
            System.out.println("RESUME");
            gameFlow.start();
        }
        updateGameState();
    }

    /**
     * This method reads the client data and forwards the key
     * to the correct player.
     * @param clientData recieved client data
     */
    void readClientData(ClientData clientData) {
        int playerID = clientData.getPlayerID();
        Key key = clientData.getKey();
        
        if (key.getKeyCode() == KeyEvent.VK_P) {
            pauseGame();
        } else {
            level.players.get(playerID).doWhen(key);
            updateGameState();
        }
    }

    /**
     * This method refreshes the game state buffer by
     * clearing old data.
     */
    private void refreshGameStateBuffer() {
        gameStateBuffer.removeItems.clear();
        gameStateBuffer.spawnItems.clear();
        
        for (int i = 0; i < level.players.size(); i++) {
            if (gameState.soundFile[i][0] > 0) {
                gameStateBuffer.soundFile[i][0] = 0;
            }
        }
        updateGameState();
    }

    /**
     * This method restarts the engine.
     */
    public void resetEngine() {
        gameFlow.stop();
        winTimer.stop();
        level = null;
        gameStateBuffer = null;
    }

    /**
     * This method is used for setting the references between this
     * class and the gameState class.
     * @param gameState
     */
    void setGameStateReference(GameState gameState) {
        this.gameState = gameState;
        updateGameState();
    }

    /**
     * This method adds the items positions to the
     * buffer and game state.
     */
    private void setItemPositions() {
        for (int i = 0; i < level.items.size(); i++) {
            int[] itemData = {
                level.items.get(i).getRefID(),
                level.items.get(i).getX(),
                level.items.get(i).getY()};
            gameStateBuffer.items.add(itemData);
        }
        updateGameState();
    }

    void setPlayTime(int value) {
        playTime = (value * 60000);
    }

    /**
     * This method spawns objects randomly based on a fixed set
     * of occurences.
     */
    private void spawnRandom() {
        int rand1 = (int) (Math.random() * 10);
        int rand2 = (int) (Math.random() * 10);
        int rand3 = (int) (Math.random() * 10);
        int product = ((rand1 * rand2) * rand3);

        int randX = (int) (((Math.random() * 30) * rand1) * 10);
        int randY = (int) (((Math.random() * 60) * rand2) * 10);

        if (randX < level.mapDimension.height && randY < level.mapDimension.width) {
            if (product == 1) {
                Box box = new Box(randX, randY, 100, 100, "/images/crate.png");
                if (!checkSpawnWallCollision(box)) {
                    if (!checkSpawnZoneCollision(box)) {
                        System.out.println(randX);
                        System.out.println(randY);
                        box.addContent(new TimeDialation());
                        level.spawnItem(box);

                        int refID = level.items.get(level.items.size() - 1).getRefID();
                        String[] data = {Integer.toString(randX),
                            Integer.toString(randX),
                            "box",
                            Integer.toString(refID)};

                        int[] itemData = {refID, randX, randY};

                        gameStateBuffer.spawnItems.clear();
                        gameStateBuffer.spawnItems.add(data);
                        gameStateBuffer.items.add(itemData);
                        updateGameState();
                    }
                }
            }
        }
    }

    /**
     * This method is used for starting the main thread of this
     * class and the gameFlow timer.
     */
    void startEngine() {
        setItemPositions();
        gameFlow.start();
        winTimer.start();
    }

    /**
     * This method stops the engine.
     */
    void stopEngine() {
        gameState = null;
        gameStateBuffer = null;
        level = null;
        gameFlow.stop();
        winTimer.stop();
        gameFlow = null;
        winTimer = null;
    }

    /**
     * This method gives the GameState all of the values
     * that the game state buffer is storing.
     */
    public synchronized void updateGameState() {
        // Update score
        for (int i = 0; i < gameStateBuffer.playerScore.length; i++) {
            gameState.playerScore[i] = gameStateBuffer.playerScore[i];
        }

        // Update player coords
        for (int i = 0; i < gameStateBuffer.playerCoords.length; i++) {
            gameState.playerCoords[i][0] = gameStateBuffer.playerCoords[i][0];
            gameState.playerCoords[i][1] = gameStateBuffer.playerCoords[i][1];
        }

        //Update NPCs
        gameState.NPCS = new ArrayList<>(gameStateBuffer.NPCS);

        // Update items
        gameState.items = new ArrayList<>(gameStateBuffer.items);

        // Update remove objects
        gameState.removeItems = new ArrayList<>(gameStateBuffer.removeItems);
        gameState.removeNPCs = new ArrayList<>(gameStateBuffer.removeNPCs);

        // Update spawn objects
        gameState.spawnItems = new ArrayList<>(gameStateBuffer.spawnItems);

        // Update sound
        gameState.soundFile = gameStateBuffer.soundFile;

        // Update Pause
        gameState.pause = gameStateBuffer.pause;

        // Update Game Over
        gameState.gameOver = gameStateBuffer.gameOver;
    }
}