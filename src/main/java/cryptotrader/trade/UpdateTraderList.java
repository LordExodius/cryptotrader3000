package cryptotrader.trade;

import java.util.ArrayList;

/**
 * An interface that performs operations on the current list of trading brokers
 */

public interface UpdateTraderList {
    /**
     * A method that adds a trading broker to the trader list
     * @param newTrader the trader to add to the list
     * @return true if the operation was successful, false if not
     */
    public boolean addTrader(TradingBroker newTrader);

    /**
     * A method that removes a trading broker from the trader list
     * @param brokerName name of the trader to remove
     * @return the TradingBroker object representing the trading broker that was removed
     */
    public TradingBroker removeTrader(String brokerName);

    /**
     * A method that updates the coin list of the specified trader
     * @param brokerName name of the trader to update
     * @param newCoins list of coin names (tickers) that this trader is interested in
     * @return true if the operation was successful, false if not
     */
    public boolean updateCoins(String brokerName, ArrayList<String> newCoins);

    /**
     * A method that updates the strategy of the specified trader
     * @param brokerName name of the trader to update
     * @param strategy trading strategy object that this trader will use
     * @return true if the operation is true, false if not
     */
    public boolean updateStrategy(String brokerName, TradingStrategy strategy);
}
