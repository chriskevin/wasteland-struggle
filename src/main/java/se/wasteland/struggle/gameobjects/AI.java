/*
 * This class is as of now complete. Don't make changes unless necessary!
 */
package se.wasteland.struggle.gameobjects;

import java.util.List;

/**
 * Interface AI(Artificial intelligence)
 * Contains the abstract method act() and should be implemented by objects
 * controlled by the Server.
 * The act() method describes what the AI controlled object is supposed to do.
 */
public interface AI {
    public abstract void act(List players);
}
