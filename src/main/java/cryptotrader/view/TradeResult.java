package cryptotrader.view;


public class TradeResult implements GetTradeInfo {

    // private TradingBroker broker;
    // private TradingStrategy strategy;
    private String coinName;
    private String action;
    private int quantity;
    private double price;
    // time in which the trade was executed
    private String timestamp;
    
    // @Override
    // public TradingBroker getBroker() {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public TradingStrategy getStrategy() {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

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
