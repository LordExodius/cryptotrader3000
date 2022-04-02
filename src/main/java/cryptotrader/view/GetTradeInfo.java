package cryptotrader.view;

import cryptotrader.trade.TradingBroker;
import cryptotrader.trade.TradingStrategy;

public interface GetTradeInfo {
    public TradingBroker getBroker();
    public TradingStrategy getStrategy();
    public String getCoinName();
    public String getActionType();
    public int getQuantity();
    public double getPrice();
    public String getTimestamp();
}
