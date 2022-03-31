package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.ArrayList;

public abstract class TradingStrategy implements Trade {

    private ArrayList<String> coins;

    @Override
    public TradeResult trade() {
        // TODO Auto-generated 
        return null;
    }

    public ArrayList<String> getCoins() {
        return this.coins;
    }
    
}
