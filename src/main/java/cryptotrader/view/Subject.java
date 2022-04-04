package cryptotrader.view;

import java.util.ArrayList;

/**
 * Subject class under the Observer pattern to be extended by the
 * TradeLog class. The Observer pattern is used for the model view
 * controller architectural style.
 * 
 * @author Ben Asokanthan
 * @version 1.0
 */
public abstract class Subject implements ModifyObservers {

    /**
     * A list of observers whcih watch this subject.
     */
    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }


    /**
     * Notifies all the observers by calling their update() methods.
     */
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(this);
    }
}
