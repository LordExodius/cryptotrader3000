package cryptotrader.trade;
import java.util.ArrayList;

public interface UpdateBroker {
    public void updateName(String name);
    public void updateCoins(ArrayList<String> newCoins);
    public void updateStrategy(TradingStrategy strategyName);
}
