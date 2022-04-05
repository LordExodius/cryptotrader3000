package cryptotrader.view;

/**
 * An interface to be implemented by a Subject (Observer design pattern)
 * that allows the attaching and detaching of observers.
 * 
 * @author Ben Asokanthan
 * @version 1.0
 */
public interface ModifyObservers {
    /**
     * Attaches an observer to this subject, which adds the observer
     * the observers list.
     * 
     * @param observer the observer to be attach.
     */
    public void attach(Observer observer);

    /**
     * Detaches an observer from this subject, which removes an
     * observer from the observers list.
     * 
     * @param observer the observer to detach
     */
    public void detach(Observer observer);
}
