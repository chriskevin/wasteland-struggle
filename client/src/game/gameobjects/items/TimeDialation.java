package game.gameobjects.items;

import javax.swing.Timer;

public class TimeDialation extends PowerUp {
    
    /**
     * The class constructos that sets the default properties.
     */
    public TimeDialation() {
        duration = 10000;
        effect   = "SpeedUp";
        effectTimer = new Timer(duration, this);
    }
}