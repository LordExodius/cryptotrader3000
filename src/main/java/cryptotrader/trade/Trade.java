package cryptotrader.trade;

import cryptotrader.view.TradeResult;

import java.util.HashMap;

/**
 * A method that allows each TradingStrategy obejct to perform trading logic
 */

public interface Trade {
    /**
     * A method that allows the subclasses of TradingStrategy to perform trading logic. It returns an object of 
     * type TradeResult if successful
     * @param interestedCoins
     * @return TradeResult, either a successful trade result or a null trade result if the trade was unsuccessful
     */
    public TradeResult trade(HashMap<String, Coin> interestedCoins);
}
