package se.wasteland.struggle;

import se.wasteland.struggle.gameobjects.GameObject;
import se.wasteland.struggle.gameobjects.Item;
import se.wasteland.struggle.gameobjects.Tile;
import se.wasteland.struggle.gameobjects.characters.MutantPC;
import se.wasteland.struggle.gameobjects.characters.NPC;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

final public class Camera {
    /*
     * speed       The speed which the camera moves at.
     * targetLastX The targets latest x position.
     * targetLastY The targets latest y position.
     * target      The specified object to follow.
     * level       A reference to the level object.
     * viewArea    The area which corresponds to the levels size.
     * POV         The area which the level is visible within.
     */
    private double speed;
    private int targetLastX = 0;
    private int targetLastY = 0;

    private GameObject target;
    private Level level;
    private Rectangle viewArea;
    private Rectangle POV;

    /**
     * The constructor method which requires a reference to a level object.
     * @param level Reference to the level.
     */
    public Camera(Level level) {
        this.level = level;

        speed    = (level.tileDimension.getWidth() / 4);
        viewArea = new Rectangle(new Point(0, 0), level.mapDimension);
        POV      = new Rectangle(new Point(0, 0), new Dimension(800, 600));
    }

    /**
     * This method locks the camera to the assigned target.
     */
    public void follow() {
        int targetX   = ( (POV.width / 2) - (target.getWidth() / 2) );
        int targetY   = ( (POV.height / 2) - (target.getHeight() / 2) );
        int relativeX = (targetX - target.getX() );
        int relativeY = (targetY - target.getY() );

        int leftBound   = targetX;
        int rightBound  = ( (viewArea.width - (POV.width / 2) ) );
        int topBound    = targetY;
        int bottomBound = ( (viewArea.height - (POV.height / 2) ) );

        resetObjectPositions();

        // Center targets X if it is between bounds
        if (target.getX() >= leftBound && target.getX() <= rightBound) {

            if (target.getX() != targetLastX)
                targetLastX = target.getX();

            setObjectPositions(relativeX, 0);
            target.setX(targetX);

        } else if (target.getX() > rightBound) {
            setObjectPositions(-( (viewArea.width - POV.width) + 64 ), 0);
            target.setX( (targetX + (target.getX() - rightBound ) ) );
        }

        // Center targets Y if it is between bounds
        if (target.getY() >= topBound && target.getY() <= bottomBound) {

            if (target.getY() != targetLastY)
                targetLastY = target.getY();

            setObjectPositions(0, relativeY);
            target.setY(targetY);

        } else if (target.getY() > bottomBound) {
            setObjectPositions(0, -( (viewArea.height - POV.height) + 64 ) );
            target.setY( (targetY + (target.getY() - bottomBound ) ) );
        }

        updatePOV();
    }

    /**
     * This method sets all non moving objects coordinates back
     * to their initial positions.
     */
    private void resetObjectPositions() {
        int tileSize = level.tiles.get(0).getWidth();
        int currentX = 0;
        int currentY = 0;
        int n = 0;

        while (currentY <= viewArea.height) {
            while (currentX <= viewArea.width) {
                level.tiles.get(n).setX(currentX);
                level.tiles.get(n).setY(currentY);
                currentX += tileSize;
                n++;
            }
            currentX = 0;
            currentY += tileSize;
        }
    }

    /**
     * This method changes all GameObject objects positions
     * relative to the POV.
     * @param x
     * @param y
     */
    public void setObjectPositions(int x, int y) {

        // Update tiles
        for (int i = 0; i < level.tiles.size(); i++) {
            Tile tempTile = level.tiles.get(i);
            tempTile.setX( (tempTile.getX() + x) );
            tempTile.setY( (tempTile.getY() + y) );
        }

        // Update players
        for (int i = 0; i < level.players.size(); i++) {
            MutantPC tempPlayer = level.players.get(i);
            int targetIndex = level.players.indexOf(target);
            if (i != targetIndex) {
                tempPlayer.setX( (tempPlayer.getX() + x) );
                tempPlayer.setY( (tempPlayer.getY() + y) );
            }
        }

        // Update npcs
        for (int i = 0; i < level.npcs.size(); i++) {
            NPC tempNPC = (NPC) level.npcs.get(i);
            tempNPC.setX( (tempNPC.getX() + x) );
            tempNPC.setY( (tempNPC.getY() + y));
        }

        // Update items
        for (int i = 0; i < level.items.size(); i++) {
            Item tempItem = level.items.get(i);
            tempItem.setX( (tempItem.getX() + x) );
            tempItem.setY( (tempItem.getY() + y) );
        }
    }

    /**
     * This method sets the target to follow.
     * @param obj
     */
    public void setTarget(GameObject obj) {
        target = obj;
    }

    /**
     * This method check which tiles are inside the POV
     * and adds references to them in the Level objects
     * tempTiles list.
     */
    private void updatePOV() {
        level.tempTiles = new ArrayList();

        // Find the start index
        for (int i = 0; i < level.tiles.size(); i++) {
            Tile tempTile = level.tiles.get(i);
            Rectangle tileBounds = tempTile.getBounds();

            if (tileBounds.intersects(POV))
                level.tempTiles.add(tempTile);
        }
    }
}
