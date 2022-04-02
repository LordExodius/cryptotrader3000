package cryptotrader.view;

import cryptotrader.trade.TradingBroker;
import cryptotrader.trade.TradingStrategy;

public class TradeResult implements GetTradeInfo {

    private TradingBroker broker;
    private TradingStrategy strategy;
    private String coinName;
    private String action;
    private int quantity;
    private double price;
    // time in which the trade was executed
    private String timestamp;

    public TradeResult(
        TradingBroker broker,
        TradingStrategy strategy,
        String coinName,
        String action,
        int quantity,
        double price,
        String timestamp
    ) {
        this.broker = broker;
        this.strategy = strategy;
        this.coinName = coinName;
        this.action = action;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }
    
    @Override
    public TradingBroker getBroker() {
        // TODO Auto-generated method stub
        return this.broker;
    }

    @Override
    public TradingStrategy getStrategy() {
        // TODO Auto-generated method stub
        return this.strategy;
    }

    @Override
    public String getCoinName() {
        // TODO Auto-generated method stub
        return this.coinName;
    }

    @Override
    public String getActionType() {
        // TODO Auto-generated method stub
        return this.action;
    }

    @Override
    public int getQuantity() {
        // TODO Auto-generated method stub
        return this.quantity;
    }

    @Override
    public double getPrice() {
        // TODO Auto-generated method stub
        return this.price;
    }

    @Override
    public string getTimestamp() {
        return timestamp;
    }

    
}
