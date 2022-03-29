package cryptotrader;

public @interface GetInfo {
    public TradingBroker getBroker();
    public TradingStrategy getStrategy();
    public String getCoinName();
    public String getActionType();
    public int getQuantity();
    public double getPrice();
}
