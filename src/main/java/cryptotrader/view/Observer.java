package cryptotrader.view;

/**
 * Observer interface under the Observer pattern to be implemented by the
 * TradeActivityGraph and TradeActivityTable classes. The Observer pattern 
 * is used for the model view controller architectural style.
 */
public interface Observer {

    /**
     * updates the observer. To be called whenever the Subject changes.
     * 
     * @param s the reference to the subject which the Observer is watching.
     */
    public void update(Subject s);
}
