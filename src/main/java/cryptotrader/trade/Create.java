package cryptotrader.trade;

/**
 * An interface for the abstract trading strategy class
 */

public interface Create {
    /**
     * A factory method that dynamically decides on which child trading strategy to create.
     * 
     * @param strategySelection the name of the strategy to create (e.g. "Strategy-A")
     * @return An object of type Strategy
     */
    TradingStrategy create(String strategySelection);
}
