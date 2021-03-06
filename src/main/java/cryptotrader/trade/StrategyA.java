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

public class StrategyA extends TradingStrategy {
    
    /**
     * If the price of BTC is less or equal 50,000$ and the price of ADA more than 2$ then buy 10 ADA coins
     */

    private static final ArrayList<String> coinsA = new ArrayList<String>(){{
        add("BTC");
        add("ADA");
    }};

    /**
     * Constructor
     */
    
    @Override
    public String getName()
    {
        return "Strategy-A";
    }

    public StrategyA () {
        super(coinsA);
    }

    /**
     * A method that performs trading logic for Strategy A
     * 
     * @param coinsIn a mapping from a coin's ticker symbol (e.g. BTC) to its coin
     *                info
     * @return res
     */
    public TradeResult trade (HashMap<String, Coin> coinsIn) {
        ArrayList<String> interestedCoins = new ArrayList<>(coinsIn.keySet());
        if (!checkCoins(interestedCoins))
        {
            new PopupUI(getName() + " does not have the required coin information to proceed.");
            return new TradeResult(null, this, "ADA", "Fail", 0, 0);
        }
            

        double adaPrice = coinsIn.get("ADA").getPrice();
        if (coinsIn.get("BTC").getPrice() <= 50000 && adaPrice > 2) {
            TradeResult res = new TradeResult(null, this, "ADA", "Buy", 10, Math.round(adaPrice*100.0)/100.0);
            return res;
        }
        return null;
    }

}
