package cryptotrader.trade;
import java.util.ArrayList;

/**
 * An interface for changing broker info
 * 
 * @author Jackson Howe
 * @version 1.0
 */

public interface UpdateBroker {
    /**
     * A method that updates a trading broker's name
     * @param name updated name of the trading broker
     */
    public void updateName(String name);

    /**
     * A method that updates (fully replaces) a trading broker's list of coins.
     * @param newCoins updated list of the trader's coins
     */
    public void updateCoins(ArrayList<String> newCoins);

    /**
     * A method that updates a trading broker's trading strategy
     * @param strategyName updated strategy of the trader
     */
    public void updateStrategy(TradingStrategy strategyName);
}
