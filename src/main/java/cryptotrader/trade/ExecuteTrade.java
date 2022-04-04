package cryptotrader.trade;

import java.util.HashMap;
import cryptotrader.view.TradeResult;

public interface ExecuteTrade {
    public TradeResult executeTrade(HashMap<String, Coin> coinInfo);
}
