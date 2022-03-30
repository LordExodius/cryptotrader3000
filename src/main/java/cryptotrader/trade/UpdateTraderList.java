package cryptotrader.trade;

public interface UpdateTraderList {
    public boolean addTrader(TradingBroker newTrader);
    public TradingBroker removeTrader(String brokerID);
    public boolean updateName(String brokerID, String brokerName);
    public boolean addCoin(String brokerID, String newCoin);
    public boolean updateStrategy(String brokerID, TradingStrategy strategy);
}
