package cryptotrader.trade;

import java.util.ArrayList;

public interface UpdateTraderList {
    public boolean addTrader(TradingBroker newTrader);
    public TradingBroker removeTrader(String brokerName);
    public boolean updateCoins(String brokerName, ArrayList<String> newCoins);
    public boolean updateStrategy(String brokerName, TradingStrategy strategy);
}
