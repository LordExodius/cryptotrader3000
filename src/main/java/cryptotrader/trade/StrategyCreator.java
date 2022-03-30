package cryptotrader.trade;

public class StrategyCreator implements Create {

    @Override
    public TradingStrategy create(String strategySelection) {
        if (strategySelection.equals("Strategy A"))
            return new S1();
        else if (strategySelection.equals("Strategy B"))
            return new S2();
        else if (strategySelection.equals("Strategy C"))
            return new S2();
        else if (strategySelection.equals("Strategy D"))
            return new S3();
        else 
            return null;
    }
    
}
