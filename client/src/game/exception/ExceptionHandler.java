package game.exception;

import java.util.ArrayList;
import java.util.Observable;

public class ExceptionHandler extends Observable {
    /*
     * exceptions   List of exceptions.
     */
    private ArrayList<Exception> exceptions = new ArrayList();

    /**
     * This method adds an exception to the list.
     * @param ex
     */
    public void addException(Exception ex) {
        exceptions.add(ex);
        setChanged();
        notifyObservers();
    }

    /**
     * This method returns the list of exceptions.
     * @return
     */
    public ArrayList<Exception> getExceptions() {
        return exceptions;
    }

    /**
     * This method returns the last added exception.
     * @return
     */
    public Exception getLatest() {
        return exceptions.get( (exceptions.size() - 1) );
    }
}
