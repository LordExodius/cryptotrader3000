package cryptotrader.trade;
import java.util.ArrayList;

/**
 * An interface for changing broker info
 */

public interface UpdateBroker {
    /**
     * A method that updates a trading broker's name
     * @param name
     */
    public void updateName(String name);

    /**
     * A method that updates (fully replaces) a trading broker's list of coins.
     * @param newCoins
     */
    public void updateCoins(ArrayList<String> newCoins);

    /**
     * A method that updates a trading broker's trading strategy
     * @param strategyName
     */
    public void updateStrategy(TradingStrategy strategyName);
}
