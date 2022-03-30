package cryptotrader.trade;

public interface UpdateBroker {
    public void updateName(String name);
    public void addCoin(String newCoinName);
    public void updateStrategy(TradingStrategy strategyName);
}
