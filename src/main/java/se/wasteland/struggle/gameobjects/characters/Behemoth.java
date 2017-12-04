package se.wasteland.struggle.gameobjects.characters;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Behemoth extends NPC {

    /**
     * startPosX        integer that determines the behemoth spawn X coordinates.
     * startPosY        integer that determines the behemoth spawn X coordinates.
     * playerRectangles Rectangle that is used to check if players are near the behemoth
     * playerList       ArrayList Reference to the level.players Array List.
     * detectArea       Rectangle that sets the behemoth detection "range".
     * speedSet         Boolean used for control of behemoth movement.
     */
    private boolean case0 = false;
    private boolean case1 = false;
    private boolean case2 = false;
    private boolean case3 = false;
    private Rectangle detectArea;
    private Rectangle detectRectangle;
    private boolean hunting;
    private List<MutantPC> playerList;
    private List<Rectangle> playerRectangles;
    private boolean returning;
    private boolean speedSet;
    private int startPosX;
    private int startPosY;
    private int startPositionX;
    private int startPositionY;
    private int squareSize;

    /**
     * This Constructor takes 2 arguments; x coordinate and Y coordinate. Beyond setting
     * the X and Y this constructor sets the Game Object width to 128, the height to 192 and the
     * imagePath/image is "/images/behemoth.gif".
     * @param x
     * @param y
     */
    public Behemoth(int x, int y) {
        super(x, y);
        width = 100;
        height = 125;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/behemoth.gif"));
        image = ii.getImage();
        startPosX = this.getX();
        startPosY = this.getY();
        detectArea = new Rectangle(this.getWidth() * 3, this.getHeight() * 3);

        speedSet = false;
        returning = false;
        hunting = false;
        vel = 2;
        squareSize = 250;
    }

    /**
     * This Constructor requires all 5 arguments; x coordinate, y coordinate, widht, height,imagePath;
     * Width and height determines the object logical size in the game. imagePath is the
     * location of the desired game object picture file.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param imagePath
     */
    public Behemoth(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    /**
     * This method is the interface method from the AI class. This act() method is
     * also described in the NPC -class, which Behemoth extends from, and is therefore overridden.
     * The act method() runds the do BehemothBehavior() method and the move() method.
     * Totaly the act() method
     * (NOT IMPLMENTED)
     */
    @Override
    public void act(List players) {
        playerList = players;
        doBehemothBehaviour();
        move();
    }

    /**
     * This method tells in what order the checkPlayerPresence() and moveBehemoth() are called for.
     * It also describes the movement pattern for the behemoth. The pattern can be read from the moveBehemoth()
     * arguments sent.
     */
    public void doBehemothBehaviour() {

        if (returning == false) {
            checkForPlayerPresence();
            moveBehemoth(3, squareSize, vel); // Direction 3- Down, pixelLength, pixels per iteration(speed)
            checkForPlayerPresence();
            moveBehemoth(0, squareSize, vel); // Direction 0- Left, pixelLength, pixels per iteration(speed))
            checkForPlayerPresence();
            moveBehemoth(2, squareSize, vel); // Direction 2- Up, pixelLength, pixels per iteration(speed)
            checkForPlayerPresence();
            moveBehemoth(1, squareSize, vel); // Direction 1- Right, pixelLength, pixels per iteration(speed)
        } else if (returning == true && hunting == false) {
            dx = 0;
            dy = 0;
            getHome();
        }
    }

    /**
     * This method uses the playerRectangles and the Behemoth detectArea(Rectangle) to check if they collide.
     * If they do, a human is near and the rushPlayer(playerId) method is invoked.
     */
    public void checkForPlayerPresence() {
        playerRectangles = new ArrayList();
        for (int i = 0; i < playerList.size(); i++) {
            playerRectangles.add(playerList.get(i).getBounds());
        }
        detectRectangle = new Rectangle(this.getX() - this.getWidth(), this.getY() - this.getHeight(), detectArea.width, detectArea.height);
        for (int j = 0; j < playerRectangles.size(); j++) {
            // AXEL MÃ…STE PILLA DETTA SÃ… ATT MAN KAN FLY FRÃ…N B UTAN ATT DEN STICKER!
            if (!playerRectangles.get(j).intersects(detectRectangle) ) {
                if (hunting == true) {
                    /*dx = 0;
                    dy = 0;
                    returning = true;
                    hunting = false;*/
                    //System.out.println("Player out of area!");
                    System.out.println("intersects " +playerRectangles.get(j).intersects(detectRectangle));
                    System.out.println("hunting " + hunting);
                } else if (hunting == false) {
                    System.out.println("Does not hunt");
                }
            }
            else if (playerRectangles.get(j).intersects(detectRectangle) ) {
                if (hunting == false && returning == false) {
                    speedSet = false;
                    hunting = true;
                    rushPlayer(j);
                } else if (hunting == true) {
                    dx = 0;
                    dy = 0;
                    rushPlayer(j);
                }
            }
        }
    }

    /**
     * This method makes the Behemoth follow the targetPlayer, defined by the checkForPlayerPresence() method
     * It stops following when either it has intersected the player or the player is in itÃƒâ€šÃ‚Â´s zone.
     * @param playerId
     */
    private void rushPlayer(int playerId) {
        System.out.println("rushPlayer()");
        Rectangle behemothBounds = this.getBounds();
        Rectangle targetBounds = playerList.get(playerId).getBounds();
        int targetPlayerX = playerList.get(playerId).getX();// + (playerList.get(playerId).getWidth()/2);
        int targetPlayerY = playerList.get(playerId).getY();// + (playerList.get(playerId).getHeight()/2);

        int behemothRealX = this.getX();// + (this.getWidth()/2);
        int behemothRealY = this.getY();// + (this.getHeight()/2);

        //When the targetPlayer is in his zone or has been intersected
        //The behemoth should return to itÃƒâ€šÃ‚Â´s "home". Otherwise it should get closer to its target.


        if (behemothBounds.intersects(targetBounds)) {
            System.out.println("player HIT!");
            dx = 0;
            dy = 0;
            returning = true;
            hunting = false;
        }
        if (behemothRealX > targetPlayerX && hunting == true) { //vÃ¤nster
            dx -= vel * 2;
        }
        if (behemothRealX < targetPlayerX && hunting == true) { // HÃ¶ger
            dx += vel * 2;
        }
        if (behemothRealY > targetPlayerY && hunting == true) { // Up
            dy -= vel * 2;
        }
        if (behemothRealY < targetPlayerY && hunting == true) { // Down
            dy += vel * 2;
        }
    }

    /**
     * This method getÃƒâ€šÃ‚Â´s the Behemoth home after a successfull or unsuccessfull hunt.
     * The Behemoth getÃƒâ€šÃ‚Â´s back home and on itÃƒâ€šÃ‚Â´s way home itÃƒâ€šÃ‚Â´s not interfering with any players.
     * The method uses interruptX and interruptY for navigation backhome.
     */
    public void getHome() {
        System.out.println("getHome()");
        // the distanceLeft fields are used to change the dx and dy when the behemoth
        // closes itÂ´s "home". Anti-bug code =)
        int xDistanceLeft = this.getX() - startPosX;
        int yDistanceLeft = this.getY() - startPosY;


        if (this.getX() > startPosX) { //vÃ¤nster
            if (xDistanceLeft > -10 && xDistanceLeft < 10) {
                dx -= 1;
            } else {
                dx -= vel * 2;
            }
        }
        if (this.getX() < startPosX) { // HÃ¶ger
            if (xDistanceLeft > -10 && xDistanceLeft < 10) {
                dx += 1;
            } else {
                dx += vel * 2;
            }
        }
        if (this.getY() > startPosY) { // Up
            if (yDistanceLeft > -10 && yDistanceLeft < 10) {
                dy -= 1;
            } else {
                dy -= vel * 2;
            }
        }
        if (this.getY() < startPosY) { // Down
            if (yDistanceLeft > -10 && yDistanceLeft < 10) {
                dy += 1;
            } else {
                dy += vel * 2;
            }
        } else if (this.getX() == startPosX && this.getY() == startPosY) {
            returning = false;
            case0 = false;
            case1 = false;
            case2 = false;
            case3 = false;
            speedSet = false;
        }
    }

    /**
     * This method t
     * ells the Behemoth to move at the given direction, the given length with
     * the given speed. Direction, length and speed are the arguments.
     * direction 0 = left
     * direction 1 = right
     * direction 2 = up
     * direction 3 = down
     * @param dir
     * @param length
     * @param speed
     */
    private void moveBehemoth(int dir, int length, int speed) {
        switch (dir) {
            case 0: { // --- left ----
                if (speedSet == false && case0 == false && case1 == false && case2 == false && case3 == false) {
                    System.out.println("left");
                    startPositionX = this.getX();
                    dx -= speed;
                    speedSet = true;
                    case0 = true;
                }
                if (x <= startPositionX - length && speedSet == true && case0 == true) {
                    dx = 0;
                    speedSet = false;
                    case0 = false;
                }
                break;
            }
            case 1: { // ---- right ----
                if (speedSet == false && case0 == false && case1 == false && case2 == false && case3 == false) {
                    System.out.println("right");
                    startPositionX = this.getX();
                    dx += speed;
                    speedSet = true;
                    case1 = true;
                }
                if (x >= startPositionX + length && speedSet == true && case1 == true) {
                    dx = 0;
                    speedSet = false;
                    case1 = false;
                }
                break;
            }
            case 2: { // --- up ---
                if (speedSet == false && case0 == false && case1 == false && case2 == false && case3 == false) {
                    System.out.println("up");
                    startPositionY = this.getY();
                    dy -= speed;
                    speedSet = true;
                    case2 = true;
                }
                if (y <= startPositionY - length && speedSet == true && case2 == true) {
                    dy = 0;
                    speedSet = false;
                    case2 = false;
                }
                break;
            }
            case 3: { // --- down ---
                if (!speedSet && !case0 && !case1 && !case2 && !case3) {
                    System.out.println("down");
                    startPositionY = this.getY();
                    dy += speed;
                    speedSet = true;
                    case3 = true;
                }
                if (y >= startPositionY + length && speedSet && case3) {
                    dy = 0;
                    speedSet = false;
                    case3 = false;
                }
                break;
            }
        }
    }
}