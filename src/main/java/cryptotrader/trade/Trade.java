package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import com.google.gson.JsonObject;

public interface Trade {
    public TradeResult trade(JsonObject coinInfo);
}
