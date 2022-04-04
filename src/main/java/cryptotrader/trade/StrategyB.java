package cryptotrader.trade;

import cryptotrader.view.TradeResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * A subclass of Strategy
 * @author Jackson Howe
 * @version 1.0
 */

public class StrategyB extends TradingStrategy {

    /**
     * If the price of LTC is greater than $125 and the price of ADA is greater than $1.15 then buy 20 coins of SOL
     */

    private static final ArrayList<String> coinsB = new ArrayList<String>() {
        {
            add("LTC");
            add("ADA");
            add("SOL");
        }
    };

    /**
     * Constructor
     */

    @Override
    public String getName() {
        return "Strategy B";
    }

    public StrategyB() {
        super(coinsB);
    }

    /**
     * A method that performs trading logic for Strategy B
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
        if (checkCoins(interestedCoins) && coinsIn.get("LTC").getPrice() <= 125 && coinsIn.get("ADA").getPrice() > 1.15) {
            TradeResult res = new TradeResult(null, this, "SOL", "buy", 20, 
                    coinsIn.get("SOL").getPrice(), formatter.format(date));
            return res;
        }

        return null;
    }

}