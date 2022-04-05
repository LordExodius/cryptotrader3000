package cryptotrader.trade;

import cryptotrader.gui.PopupUI;
import cryptotrader.view.TradeResult;
import java.util.ArrayList;
import java.util.HashMap;

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
        return "Strategy-B";
    }

    public StrategyB() {
        super(coinsB);
    }

    /**
     * A method that performs trading logic for Strategy B
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
            return new TradeResult(null, this, "SOL", "Fail", 0, 0);
        }
            

        // Trading logic
        if (coinsIn.get("LTC").getPrice() >= 125 && coinsIn.get("ADA").getPrice() > 1.15) {
            TradeResult res = new TradeResult(null, this, "SOL", "Buy", 20, 
                    Math.round(((coinsIn.get("SOL").getPrice())*100.0)/100.0));
            return res;
        }

        return null;
    }

}