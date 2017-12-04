package se.wasteland.struggle.exception;

public class MalformedLevelException extends Exception {

    /**
     * The class constructor of the exception which is thrown
     * when the level is malformed.
     * @param message
     */
    public MalformedLevelException(String message) {
        super(message);
    }
}
