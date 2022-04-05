package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * An abstract class that represents an abstract trading strategy
 * @author Jackson Howe, David Tran
 * @version 1.0
 */

public abstract class TradingStrategy implements Trade {

    private ArrayList<String> coins;

    /**
     * Constructor
     * @param coins list of coins required for this strategy (ticker symbols)
     */
    protected TradingStrategy(ArrayList<String> coins) {
        this.coins = coins; 
    }

    @Override
    abstract public TradeResult trade(HashMap<String, Coin> interestedCoins);

    /**
     * Checks to see if the list of the coins involved in the trade contains all
     * coins
     * TradingStrategy requires to do the trade.
     * 
     * @param coinsIn list of coins to verify (ticker symbols)
     * @return boolean
     */
    protected boolean checkCoins(ArrayList<String> coinsIn) {
        HashSet<String> interestedCoins = new HashSet<String>(coinsIn);
        for (String coinTitle : coins) {
            if (!(interestedCoins.contains(coinTitle))) {
                return false;
            }
        }
        return true;
    }

    abstract public String getName();
    
}
