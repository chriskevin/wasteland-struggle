package game.gameobjects;

import java.util.ArrayList;

public class Character extends GameObject {

    /**
     * Character class instance variabels.
     *
     * dx, temporary X movement.
     * dy, temporary Y movement.
     * vel, the character speed, velocity.
     * state, The character state,can be of type running, tackling, dead, etc. (not implemented)
     */
    protected int dx = 0;
    protected int dy = 0;
    protected int lastX = 0;
    protected int lastY = 0;
    protected int vel = 5;
    protected ArrayList<String> conditions = new ArrayList();
    protected boolean move = true;

    protected int xCounter = 0;
    protected int yCounter = 0;

    /**
     * This Construcor takes 2 arguments. X and Y which becomes the character
     * start positions.
     * @param x
     * @param y
     */
    public Character(int x, int y) {
        super(x, y);
    }

    /**
     * This Construcotr takes 4 arguments. startPosition X and startposition Y,
     * the width and the height of the Character.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Character(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * This Constructor takes 5 arguments. startPosition X and startposition Y,
     * the width and the height of the Character. It also sets the Image via the imagePath.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param imagePath
     */
    public Character(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    /**
     * This method adds a new conditon to the character.
     * @param condition
     */
    public void addCondition(String condition) {
        boolean exsists = false;

        for (int i = 0; i < conditions.size(); i++) {
            String tempCondition = conditions.get(i);

            if (tempCondition.equals(condition)) {
                exsists = true;
                break;
            }
        }

        if (!exsists) {
            conditions.add(condition);
        }
    }

    /**
     * This method returns the list of conditions.
     * @return
     */
    public ArrayList<String> getConditions() {
        return conditions;
    }

    /**
     * This method returns the velocity of the character.
     * @return
     */
    public double getVel() {
        return vel;
    }

    /**
     * This method updates the X and Y coordinates from
     * the temporary dX and dY fields.
     */
    public void move() {
        if (conditions.contains("Immobile")) {
            x += 0;
            y += 0;
        } else if (conditions.contains("SpeedUp")) {
            x += (dx * 2);
            y += (dy * 2);
        } else {
            x += dx;
            y += dy;
        }
    }

    /**
     * This method removes a condition from the character.
     * @param condition
     */
    public void removeCondition(String condition) {
        conditions.remove(condition);
    }

    public void setMovement(boolean move) {
        this.move = move;
    }

    public boolean getMovement() {
        return move;
    }

    /**
     * This method sets x to the previous x coordinate. it is used to prevent the player to
     * move into obstacles and such.
     * @param X
     */
    public void setLastX(int X) {
        xCounter++;
        if (xCounter == 2) {
            x = X;
            xCounter = 0;
        }
            //lastX = X;
    }
    /**
     * This method sets y to the previous y coordinate. it is used to prevent the player to
     * move into obstacles and such.
     * @param Y
     */
    public void setLastY(int Y) {
        yCounter++;
        if (yCounter == 2) {
            y = Y;
            yCounter = 0;
        }
        //lastY = Y;
    }

    /**
     * This method returns the last x coordinate.
     * @return
     */
    public int getLastX() {
        return lastX;
    }

    /**
     * This method returns the previous y coordinate.
     * @return
     */
    public int getLastY() {
        return lastY;
    }
}
