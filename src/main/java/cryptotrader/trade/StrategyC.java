package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * A subclass of strategy
 * @author Jackson Howe
 * @version 1.0
 */

public class StrategyC extends TradingStrategy {

    /**
     * If the price of ETH is greater than $3450 and the price of BTC is greater than
     * $46000 then sell 100 ADA
     */

    private static final ArrayList<String> coinsC = new ArrayList<String>() {
        {
            add("ETH");
            add("BTC");
            add("ADA");
        }
    };

    /**
     * Constructor
     */

    @Override
    public String getName() {
        return "Strategy C";
    }

    public StrategyC() {
        super(coinsC);
    }

    /**
     * A method that performs trading logic for Strategy C
     * 
     * @param coinsIn
     * @return res
     */
    public TradeResult trade(HashMap<String, Coin> coinsIn) {
        ArrayList<String> interestedCoins = new ArrayList<>(coinsIn.keySet());

        // Get current date
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat();

        // Trading logic
        if (checkCoins(interestedCoins) && coinsIn.get("ETH").getPrice() > 3450
                && coinsIn.get("BTC").getPrice() > 46000) {
            TradeResult res = new TradeResult(null, this, "ADA", "sell", 100,
                    coinsIn.get("ADA").getPrice(), formatter.format(date));
            return res;
        }

        return null;
    }

}
