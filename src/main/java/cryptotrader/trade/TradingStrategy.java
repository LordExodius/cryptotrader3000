package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.ArrayList;

import com.google.gson.JsonObject;

public abstract class TradingStrategy implements Trade {

    private ArrayList<String> coins;

    @Override
    public TradeResult trade(JsonObject coinInfo) {
        // TODO Auto-generated 
        return null;
    }

    public ArrayList<String> getCoins() {
        return this.coins;
    }

    public String getName()
    {
        return null;
    }
    
}
