package cryptotrader.view;

public interface ModifyObservers {
    public void attach(Observer observer);
    public void detach(Observer observer);
}
