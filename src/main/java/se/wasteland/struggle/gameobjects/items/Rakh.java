package se.wasteland.struggle.gameobjects.items;

import se.wasteland.struggle.gameobjects.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Rakh extends Item implements ActionListener {
    /**
     * hatchingTime The time it takes for the egg to hatch. (Not yet implemented)
     * hatchTimer   The timer object used with hatchingTime. (Not yet implemented)
     */
    private int hatchingTime = 10000;
    private Timer hatchTimer;

    public Rakh(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, imagePath);

        hatchTimer = new Timer(hatchingTime, this);
    }

    /**
     * This method is called by the hatchTimer.
     * @param evt The timer event.
     */
    public void actionPerformed(ActionEvent evt) {
        hatchTimer.stop();
        hatch();
    }

    /**
     * This method turns the rakh into a Behemoth. (Not yet implemented)
     */
    public void hatch() {
        
    }
}
