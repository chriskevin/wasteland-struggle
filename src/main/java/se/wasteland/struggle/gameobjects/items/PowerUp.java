package se.wasteland.struggle.gameobjects.items;

import se.wasteland.struggle.gameobjects.Character;
import se.wasteland.struggle.gameobjects.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class PowerUp extends Item implements ActionListener {

    /**
     * expired      control variable.
     * duration     the power up duration
     * effect       the power up effect
     * effecttimer  the timer that counts the time of the effect.
     */
    protected boolean expired = false;
    protected int duration = 0;
    protected String effect = "";
    protected Timer effectTimer;

    /**
     * The class construcoter that creates a new effecttimer.
     */
    public PowerUp() {
        super();
        effectTimer = new Timer(duration, this);
    }

    /**
     * This method expires the power up if duration is > 0.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (duration > 0) {
            expire();
        }
    }

    /**
     * This method activites the powerup.
     */
    public void activate() {
        effectTimer.start();
    }

    /**
     * This method applies the powerup effect on the Character target.
     * @param target
     */
    public void affect(Character target) {
        target.addCondition(effect);
    }

    /**
     * This method sets the powerup as expired.
     */
    public void expire() {
        expired = true;
        effectTimer.stop();
    }

    /**
     * This methodreturns the effetct of the powerup.
     * @return
     */
    public String getEffect() {
        return effect;
    }

    /**
     * This method checks if the powerup is active and then returns true, else it returns false.
     * @return
     */
    public boolean isActive() {
        if (effectTimer.isRunning()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method returns the control variable expired.
     * @return
     */
    public boolean hasExpired() {
        return expired;
    }
}
