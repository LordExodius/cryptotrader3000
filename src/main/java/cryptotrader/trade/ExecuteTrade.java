package cryptotrader.trade;

import java.util.HashMap;
import cryptotrader.view.TradeResult;

/**
 * An interface provided by TradingStrategy for TradingBroker to use
 * 
 * @author Jackson Howe
 * @version 1.0
 */

public interface ExecuteTrade {
    /**
     * Execute a trade given the info for certain coins.
     * @param coinInfo A mapping from a coin name (e.g. BTC) to its coin info
     * @return a trade result 
     */
    public TradeResult executeTrade(HashMap<String, Coin> coinInfo);
}
