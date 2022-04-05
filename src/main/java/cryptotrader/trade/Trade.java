package cryptotrader.trade;

import cryptotrader.view.TradeResult;

import java.util.HashMap;

/**
 * A method that allows each TradingStrategy obejct to perform trading logic
 * 
 * @author Jackson Howe
 * @version 1.0
 */

public interface Trade {
    /**
     * Perform a trade given a collection of coins and their info.
     * @param interestedCoins a mapping from a coin's ticker symbol (e.g. BTC) to its coin info
     * @return TradeResult, either a successful trade result or a null trade result if the trade was unsuccessful
     */
    public TradeResult trade(HashMap<String, Coin> interestedCoins);
}
