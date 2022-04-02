package cryptotrader.trade;

import cryptotrader.view.TradeResult;

import java.util.HashMap;

import com.google.gson.JsonObject;

public interface Trade {
    public TradeResult trade(HashMap<String, Coin> interestedCoins);
}
