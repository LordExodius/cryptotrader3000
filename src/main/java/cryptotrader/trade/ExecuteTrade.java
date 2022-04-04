package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.HashMap;

public interface ExecuteTrade {
    public TradeResult executeTrade(HashMap<String, Coin> interestedCoins);
}
