package cryptotrader.trade;

import cryptotrader.gui.PopupUI;
import cryptotrader.view.TradeResult;
import java.util.ArrayList;
import java.util.HashMap;

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
        return "Strategy-C";
    }

    public StrategyC() {
        super(coinsC);
    }

    /**
     * A method that performs trading logic for Strategy C
     * 
     * @param coinsIn a mapping from a coin's ticker symbol (e.g. BTC) to its coin
     *                info
     * @return res
     */
    public TradeResult trade(HashMap<String, Coin> coinsIn) {
        ArrayList<String> interestedCoins = new ArrayList<>(coinsIn.keySet());
        if (!checkCoins(interestedCoins))
        {
            // TODO: POPUP WHEN TRADE DOES NOT HAVE CORRECT INFORMATION
            new PopupUI(getName() + " does not have the required coin information to proceed.");
            return new TradeResult(null, this, "ADA", "Fail", 0, 0);
        }
            

        // Trading logic
        if (coinsIn.get("ETH").getPrice() > 3450
                && coinsIn.get("BTC").getPrice() > 46000) {
            TradeResult res = new TradeResult(null, this, "ADA", "sell", 100,
                    Math.round((coinsIn.get("ADA").getPrice())*100.0)/100.0);
            return res;
        }

        return null;
    }

}
