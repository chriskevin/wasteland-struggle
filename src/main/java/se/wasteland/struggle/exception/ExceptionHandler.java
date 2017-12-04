package se.wasteland.struggle.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ExceptionHandler extends Observable {

    private List<Exception> exceptions = new ArrayList<>();

    public void addException(Exception ex) {
        exceptions.add(ex);
        setChanged();
        notifyObservers();
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public Exception getLatest() {
        return exceptions.get( (exceptions.size() - 1) );
    }
}
