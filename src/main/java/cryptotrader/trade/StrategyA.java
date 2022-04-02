package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

public class StrategyA extends TradingStrategy {

    /**
     * "If the price of BTC is less or equal $47,000 and the price of ADA is more than $1.15 then buy 10 ADA coins"
     */
    
    @Override
    public String getName()
    {
        return "StrategyA";
    }

    @Override
    public TradeResult trade(JsonObject coinInfo) {
        JsonElement BTCprice = coinInfo.get("bitcoin");
        System.out.println(BTCprice);
        return null;
    }

    public static void main(String[] args) {
        StrategyA test = new StrategyA();
    }

}
