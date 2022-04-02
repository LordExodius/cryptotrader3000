package cryptotrader.view;

public interface GetTradeInfo {
    // public TradingBroker getBroker();
    // public TradingStrategy getStrategy();
    public String getCoinName();
    public String getActionType();
    public int getQuantity();
    public double getPrice();
    public String getTimestamp();
}
