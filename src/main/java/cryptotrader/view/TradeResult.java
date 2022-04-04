package cryptotrader.view;

import cryptotrader.trade.TradingBroker;
import cryptotrader.trade.TradingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TradeResult implements GetTradeInfo {

    /**
     * The trading broker associated with the trade.
     */
    private TradingBroker broker;

    /**
     * The trading strategy used for the trade.
     */
    private TradingStrategy strategy;

    /**
     * The name of the coin which was bought/sold.
     */
    private String coinName;

    /**
     * Either "Buy" or "Sell".
     */
    private String action;

    /**
     * The amount of coins which were traded.
     */
    private int quantity;

    /**
     * The price of the coin when the trade occurred.
     */
    private double price;

    /**
     * The date when the trade occurred (in "dd-MMM-yyy" format).
     */
    private String timestamp;

    /**
     * Constructs a TradeResult, with the timestamp being the current time.
     * @param broker TradingBroker broker.
     * @param strategy TradingStrategy strategy.
     * @param coinName String name of the coin.
     * @param action String either a "Buy" or "Sell" action.
     * @param quantity int amount of coins traded.
     * @param price double the price at which the coin was traded at.
     */
    public TradeResult(
        TradingBroker broker,
        TradingStrategy strategy,
        String coinName,
        String action,
        int quantity,
        double price
    ) {
        this.broker = broker;
        this.strategy = strategy;
        this.coinName = coinName;
        this.action = action;
        this.quantity = quantity;
        this.price = price;
        
        LocalDate date = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        this.timestamp = date.format(myFormatObj);
    }
    
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
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @return Object[] an array of strings to be used by TradeActivityGraph and TradeActivityTable.
     */
    public Object[] getResultObj() {
        Object[] obj = {broker.getName(), broker.getStrategy().getName(), coinName, action, quantity, price, timestamp};
        return obj;
    }

    
}
