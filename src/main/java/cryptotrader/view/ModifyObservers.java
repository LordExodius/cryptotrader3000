package cryptotrader.view;

public interface ModifyObservers {
    /**
     * Attaches an observer to this subject, which adds the observer
     * the observers list.
     * 
     * @param observer the observer to be attached.
     */
    public void attach(Observer observer);

    /**
     * Detaches an observer from this subject, which removes an
     * observer from the observers list.
     * 
     * @param observer
     */
    public void detach(Observer observer);
}
