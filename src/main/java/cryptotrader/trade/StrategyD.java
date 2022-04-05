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
        return "Strategy-D";
    }

    public StrategyD() {
        super(coinsD);
    }

    /**
     * A method that performs trading logic for Strategy C
     * 
     * @param coinsIn a mapping from a coin's ticker symbol (e.g. BTC) to its coin info
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
        if (coinsIn.get("LTC").getPrice() >= 123) {
            TradeResult res = new TradeResult(null, this, "SOL", "sell", 234,
                    Math.round((coinsIn.get("SOL").getPrice())*100.0)/100.0);
            return res;
        }
        return null;
    }

}
