/**
 * This class is as of now complete. Don't make changes unless necessary!
 */

package se.wasteland.struggle.gameobjects;

import se.wasteland.struggle.Key;

/**
 * Interface Playable
 * Contains the abstract method doWhen() and should be implemented by objects
 * controlled by human interactors.
 * The doWhen method required for the interface takes 1 argument; A Key object
 * that has two important variabels; the key code and the key state.
 * The method defines what the human controlled object should "do" when a certain key(code)
 * is pressed(state) or released(state).
 */
public interface Playable {
    public abstract void doWhen(Key key);
}
