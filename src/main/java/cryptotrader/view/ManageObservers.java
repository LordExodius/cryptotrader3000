package cryptotrader.view;

public interface ManageObservers {
    public void attach(Observer o);
    public void detach(Observer o);
}
