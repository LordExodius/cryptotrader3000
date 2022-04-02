package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


import com.google.gson.JsonObject;

public abstract class TradingStrategy implements Trade {

    private HashMap<String, Coin> coins;

    @Override
    public TradeResult trade(JsonObject coinData) {
        // TODO Auto-generated 
        return null;
    }

    /**
     * 
     * @param coinsIn
     * @return boolean
     */
    public boolean checkCoins(HashMap<String, Coin> coinsIn) {
        Set interestedCoins = coinsIn.keySet();
        for (String coinTitle : coins.keySet()) {
            if (!(interestedCoins.contains(coinTitle))) {
                return false;
            }
        }
        return true;
    }
    
}
