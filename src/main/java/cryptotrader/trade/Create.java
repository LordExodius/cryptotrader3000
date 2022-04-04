package cryptotrader.trade;

/**
 * An interface for the abstract trading strategy class
 */

public interface Create {
    /**
     * A factory method that dynamically decides on which child trading strategy to create
     * @param strategySelection
     * @return An object of type Strategy
     */
    TradingStrategy create(String strategySelection);
}
