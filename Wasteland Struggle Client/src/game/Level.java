package game;

import game.data.MapData;
import game.data.MapParser;
import game.exception.MalformedLevelException;
import game.gameobjects.tiles.Floor;
import game.gameobjects.items.Rakh;
import game.gameobjects.characters.MutantPC;
import game.gameobjects.Item;
import game.gameobjects.characters.NPC;
import game.gameobjects.Tile;
import game.gameobjects.characters.Behemoth;
import game.gameobjects.tiles.Obstacle;
import game.gameobjects.tiles.Trap;
import game.gameobjects.items.Box;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Level extends JPanel {
    /**
     * items         The items that exsist in the level.
     * npcs          The AI based characters in the level.
     * players       Players (Multiplayer only).
     * tempTiles     Contains references to the visible tiles (Only used with Camera class).
     * tiles         The tiles which the level is made up off.
     * zones         The players zones.
     * mapDimension  The size of the map.
     * tileDimension The square size of a tile.
     * mapData       A object that contains the default structure of the level map.
     * pc            Player (Single player only).
     */
    public int gameObjectCount = 0;

    public ArrayList<Item>     items     = new ArrayList();
    public ArrayList<NPC>      npcs      = new ArrayList();
    public ArrayList<MutantPC> players   = new ArrayList();
    public ArrayList<Tile>     tempTiles = null;
    public ArrayList<Tile>     tiles     = new ArrayList();
    public ArrayList<Zone>     zones     = new ArrayList();

    public Dimension mapDimension;
    public Dimension tileDimension;

    private MapData mapData;
    public MutantPC pc;

    /**
     * The class constructor which creates player zones
     * and builds a level.
     */
    public Level() {
        mapData = new MapData();

        // Create zones
        zones.add(new Zone(64, 64, 192, 192));
        zones.add(new Zone(1344, 64, 192, 192));
        zones.add(new Zone(64, 1344, 192, 192));
        zones.add(new Zone(1344, 1344, 192, 192));

        try {
            build();
        } catch (MalformedLevelException ex) {
            System.out.println(ex);
        }

        setBackground(Color.BLACK);
        setBounds(0, 0, 800, 600);
        setPreferredSize(new Dimension(800, 600));
    }

    /**
     * This method creates a map parser and writes data to the MapData
     * object. It then uses the map data to create all game objects.
     */
    public void build() throws MalformedLevelException {
        // Set the start coordinates
        int currentX = 0;
        int currentY = 0;
        int i = 0;

        // Read XML file and put data into MapData object
        MapParser mapParser = new MapParser(mapData);
        mapParser.readXMLFile("./level1.xml");
        mapParser.parseDocument();

        // Retrive data from MapData object
        String[][] tile;
        String[] map = mapData.getMap();
        int mapWidth = Integer.parseInt(map[0]);
        int mapHeight = Integer.parseInt(map[1]);
        int tileSize = Integer.parseInt(map[2]);

        mapDimension = new Dimension(mapWidth, mapHeight);
        tileDimension = new Dimension(tileSize, tileSize);

        ArrayList<String[][]> tileData = mapData.getTiles();

        // Fill rows
        while (currentY <= mapHeight) {

            // Fill columns
            while (currentX <= mapWidth) {
                // Throw exception if number of defined tiles is to low
                if (i < tileData.size())
                    tile = tileData.get(i);
                else
                    throw new MalformedLevelException("Number of tiles doesn't match map width.");

                // Get values from tile
                String type        = tile[0][0];
                String texture     = tile[0][1];
                String contentType = tile[1][0];

                // Create tile and content
                spawnTile(currentX, currentY, tileSize, type, texture);
                createGameObjects(currentX, currentY, contentType);

                // Increment counters
                i++;
                currentX += tileSize;
            }

            currentX = 0;
            currentY += tileSize;
        }
    }

    /**
     * This method generates all items, pc and npcs.
     * @param x The x coordinate of the object.
     * @param y The y coordinate of the object.
     * @param contentType The type of the content(PC, NPC, Item, etc)
     */
    private void createGameObjects(int x, int y, String contentType) {
        if (contentType.equals("pc"))
            spawnPC(new MutantPC(x, y));
        else if (contentType.equals("multiplayerpc"))
            spawnPlayer(new MutantPC(x, y));
        else if (contentType.equals("npc"))
            spawnNPC(new Behemoth(x, y));
        else if (contentType.equals("rakh"))
            spawnItem(new Rakh(x, y, 60, 57, "/images/egg.gif"));
        else if (contentType.equals("box"))
            spawnItem(new Box(x, y, 20, 20, "/images/crate.png"));
    }

    /**
     * This method calls all of the paint methods.
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g2D = (Graphics2D) graphics;

        paintTempTiles(g2D); // This is only used with the Camera class.
        paintTiles(g2D);
        paintItems(g2D);
        paintPC(g2D);        // This will only be done in singel player mode
        paintPlayers(g2D);   // This will only be done in multiplayer mode
        paintNPCs(g2D);

        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    /**
     * This method paints all items.
     * @param g2D A Graphics2D object
     */
    private void paintItems(Graphics2D g2D) {
        for (int i = 0; i < items.size(); i++) {
            Item tempItem = items.get(i);
            g2D.drawImage(tempItem.getImage(), tempItem.getX(), tempItem.getY(), this);
        }
    }

    /**
     * This method paints all NPCs.
     * @param g2D A Graphics2D object
     */
    private void paintNPCs(Graphics2D g2D) {
        for (int i = 0; i < npcs.size(); i++) {
            NPC tempNpc = npcs.get(i);
            g2D.drawImage(tempNpc.getImage(), tempNpc.getX(), tempNpc.getY(), this);
        }
    }

    /**
     * This method paints the player (Single player only).
     * @param g2D A Graphics2D object
     */
    private void paintPC(Graphics2D g2D) {
        if (pc != null)
            g2D.drawImage(pc.getImage(), pc.getX(), pc.getY(), this);
    }

    /**
     * This method paints all multiplayer players.
     * @param g2D A Graphics2D object
     */
    private void paintPlayers(Graphics2D g2D) {
        for (int i = 0; i < players.size(); i++) {
            MutantPC tempOpponent = players.get(i);
            g2D.drawImage(tempOpponent.getImage(), tempOpponent.getX(), tempOpponent.getY(), this);
        }
    }

    /**
     * This method paints all temporary tiles (Only used with the Camera class).
     * @param g2D A Graphics2D object
     */
    private void paintTempTiles(Graphics2D g2D) {
        if (tempTiles != null) {
            for (int i = 0; i < tempTiles.size(); i++) {
                Tile tempTile = tempTiles.get(i);
                g2D.drawImage(tempTile.getImage(), tempTile.getX(), tempTile.getY(), this);
            }
        }
    }

    /**
     * This method paints all tiles.
     * @param g2D A Graphics2D object
     */
    private void paintTiles(Graphics2D g2D) {
        if (tempTiles == null) {
            for (int i = 0; i < tiles.size(); i++) {
                Tile tempTile = tiles.get(i);
                g2D.drawImage(tempTile.getImage(), tempTile.getX(), tempTile.getY(), this);
            }
        }
    }

    /**
     * This method adds a new Item to the items list.
     * @param item A new Item object.
     */
    public void spawnItem(Item item) {
        item.setRefID(gameObjectCount);
        items.add(item);
        gameObjectCount++;
    }

    public void spawnItem(Item item, int refID) {
        item.setRefID(refID);
        items.add(item);
    }

    /**
     * This method adds a new computer controlled character
     * to the npc list.
     * @param npc
     */
    public void spawnNPC(NPC npc) {
        npc.setRefID(gameObjectCount);
        npcs.add(npc);
        gameObjectCount++;
    }

    /**
     * This method adds a new player to the player list.
     * @param player
     */
    public void spawnPlayer(MutantPC player) {
        players.add(player);
    }

    /**
     * This method sets the player or overrides the
     * the old object (Single player only).
     * @param pc
     */
    public void spawnPC(MutantPC pc) {
        this.pc = pc;
    }

    /**
     * This method spawns a tile of type floor, obstacle or trap.
     * @param x The x coordinate which the tile should be placed.
     * @param y The y coordinate which the tile should be placed.
     * @param size The square size of the tile.
     * @param type The tile type (floor, obstacle, trap).
     * @param texture The texture of the tile based on a texture ID.
     */
    public void spawnTile(int x, int y, int size, String type, String texture) {
        if (type.equals("floor"))
            tiles.add(new Floor(x, y, size, size, "/images/tiles/floor" + texture + ".png"));
        else if (type.equals("obstacle"))
            tiles.add(new Obstacle(x, y, size, size, "/images/tiles/obstacle" + texture + ".png"));
        else if (type.equals("trap"))
            tiles.add(new Trap(x, y, size, size, "/images/tiles/trap" + texture + ".png"));
    }
}