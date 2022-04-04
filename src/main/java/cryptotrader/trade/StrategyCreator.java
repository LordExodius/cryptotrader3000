package cryptotrader.trade;

/**
 * A factory class that creates the trading strategies
 * @author Ben Asokanthan
 * @version 1.0
 */

public class StrategyCreator implements Create {

    @Override
    /**
     * A method that chooses between creating one of strategy A, B, C, or D, based upon the parameter 
     * passed
     * @param strategySelection
     * @return Strategy object
     */
    public TradingStrategy create(String strategySelection) {
        if (strategySelection.equals("Strategy-A"))
            return new StrategyA();
        else if (strategySelection.equals("Strategy-B"))
            return new StrategyB();
        else if (strategySelection.equals("Strategy-C"))
            return new StrategyC();
        else if (strategySelection.equals("Strategy-D"))
            return new StrategyD();
        else 
            return null;
    }
    
}
