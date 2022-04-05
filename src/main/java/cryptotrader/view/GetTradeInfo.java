package cryptotrader.view;

import cryptotrader.trade.TradingBroker;
import cryptotrader.trade.TradingStrategy;

/**
 * An interface to be implemented by a class that allows the retrieval
 * of details regarding a trade.
 * 
 * @author Ben Asokanthan
 * @version 1.0
 */
public interface GetTradeInfo {
    /**
     * Getter method for the TradingBroker associated with the trade.
     * @return TradingBroker the broker who made the trade.
     */
    public TradingBroker getBroker();

    /**
     * Getter method for the TradingStrategy associated with the trade.
     * @return TradingStrategy the strategy which was used for the trade.
     */
    public TradingStrategy getStrategy();

    /**
     * Getter method for the name of the coin associated with the trade.
     * @return String the name of the coin.
     */
    public String getCoinName();

    /**
     * Getter method for the type of action performed.
     * @return String either "Buy" or "Sell".
     */
    public String getActionType();

    /**
     * Getter method for the quantity of coins traded.
     * @return int number of coins traded.
     */
    public int getQuantity();

    /**
     * Getter method for the price of the coin traded.
     * @return double price of the coin (in dollars).
     */
    public double getPrice();

    /**
     * Getter method for the timestamp of the trade.
     * @return String the date of the trade (in "dd-MMM-yyyy" format).
     */
    public String getTimestamp();
}
