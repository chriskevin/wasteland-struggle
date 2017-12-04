package se.wasteland.struggle.exception;

public class OutOfBoundsException extends Exception {

    /**
     * The class constructor of the exception that is thrown
     * when object leaves play area.
     * @param message
     */
    public OutOfBoundsException(String message) {
        super(message);
    }
}