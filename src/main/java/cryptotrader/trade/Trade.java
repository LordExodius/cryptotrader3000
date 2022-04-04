package cryptotrader.trade;

import cryptotrader.view.TradeResult;

import java.util.HashMap;

public interface Trade {
    public TradeResult trade(HashMap<String, Coin> interestedCoins);
}
