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

public class StrategyD extends TradingStrategy {

    /**
     * If the price of LTC is greater than or equal to $123 then sell 234 SOL
     */

    private static final ArrayList<String> coinsD = new ArrayList<String>() {
        {
            add("LTC");
            add("SOL");
        }
    };

    /**
     * Constructor
     */

    @Override
    public String getName() {
        return "Strategy D";
    }

    public StrategyD() {
        super(coinsD);
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
        if (checkCoins(interestedCoins) && coinsIn.get("LTC").getPrice() >= 123) {
            TradeResult res = new TradeResult(null, this, "SOL", "sell", 234,
                    coinsIn.get("SOL").getPrice(), formatter.format(date));
            return res;
        }

        return null;
    }

}
