package cryptotrader.view;

import java.util.ArrayList;
import java.util.List;

import cryptotrader.trade.StrategyA;
import cryptotrader.trade.Trade;
import cryptotrader.trade.TradingBroker;

public class ViewTest {

    public static void main(String[] args) {

        StrategyA strategyA = new StrategyA();
        
        TradingBroker broker1 = new TradingBroker();
        TradingBroker broker2 = new TradingBroker();
        TradingBroker broker3 = new TradingBroker();
        TradingBroker broker4 = new TradingBroker();

        broker1.updateStrategy(strategyA);
        broker2.updateStrategy(strategyA);
        broker3.updateStrategy(strategyA);
        broker4.updateStrategy(strategyA);
        
        // TEST 1: Adding trade results to TradeLog
        TradeResult result1 = new TradeResult(broker1, broker1.getStrategy(), "ETH", "Buy", 500, 150.3);
        TradeResult result2 = new TradeResult(broker2, broker2.getStrategy(), "BTC", "Sell", 200, 50.2);
        TradeResult result3 = new TradeResult(broker3, broker3.getStrategy(), "USDT", "Buy", 1000, 2.59);
        TradeResult result4 = new TradeResult(broker4, broker4.getStrategy(), "USDC", "Buy", 500, 150.3);

        TradeLog log = new TradeLog();
        log.addResults(new ArrayList<TradeResult>(
            List.of(result1,result2,result3,result4)));

        log.getResults().forEach((result) -> {
            System.out.println(result.getResultObj().toString());
        });

    }
    
}
