package cryptotrader.trade;

public interface UpdateTraderList {
    public boolean addTrader(TradingBroker newTrader);
    public TradingBroker removeTrader(int brokerID);
    public boolean updateName(int brokerID, String brokerName);
    public boolean addCoin(int brokerID, String newCoin);
    public boolean updateStrategy(int brokerID, TradingStrategy strategy);
}
