package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public abstract class TradingStrategy implements Trade {

    private ArrayList<String> coins;

    /**
     * Constructor
     * @param coins
     */
    protected TradingStrategy(ArrayList<String> coins) {
        this.coins = coins; 
    }

    @Override
    abstract public TradeResult trade(HashMap<String, Coin> interestedCoins);

    /**
     * Checks to see if the list of the coins involved in the trade contains all coins
     * TradingStrategy requires to do the trade.
     * @param coinsIn
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

    public String getName()
    {
        return null;
    }
    
}
