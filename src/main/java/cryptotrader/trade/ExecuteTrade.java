package cryptotrader.trade;

import java.util.HashMap;
import cryptotrader.view.TradeResult;

/**
 * An interface provided by TradingStrategy for TradingBroker to use
 */

public interface ExecuteTrade {
    /**
     * A method that calls the broker's strategy's trade method and returns the result
     * @param coinInfo
     * @return a trade result 
     */
    public TradeResult executeTrade(HashMap<String, Coin> coinInfo);
}
