package cryptotrader.view;

import java.util.ArrayList;

public abstract class Subject implements ModifyObservers {
    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(this);
    }
}
