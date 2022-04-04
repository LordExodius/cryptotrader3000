package cryptotrader.view;

import cryptotrader.trade.TradingBroker;
import cryptotrader.trade.TradingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents one trade result
 * @author Ben Asokanthan
 * @version 1.0
 */

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
    /**
     * Return broker
     * @return broker
     */
    public TradingBroker getBroker() {
        return this.broker;
    }

    @Override
    /**
     * Return trading strategy
     * @return strategy
     */
    public TradingStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    /**
     * Return coin name
     * @return coinName
     */
    public String getCoinName() {
        return this.coinName;
    }

    @Override
    /**
     * Return action (buy or sell)
     * @return action
     */
    public String getActionType() {
        return this.action;
    }

    @Override
    /**
     * Return quantity of coin in trade
     * @return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    /**
     * Return price of coin
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    @Override
    /**
     * Return time of trade
     * @return timestamp
     */
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
