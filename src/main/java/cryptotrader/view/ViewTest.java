package cryptotrader.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import cryptotrader.gui.MainUI;
import cryptotrader.trade.StrategyA;
import cryptotrader.trade.TradingBroker;

public class ViewTest {

    public static void main(String[] args) {

        MainUI frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);

        StrategyA strategyA = new StrategyA();
        
        TradingBroker broker1 = new TradingBroker();
        TradingBroker broker2 = new TradingBroker();
        TradingBroker broker3 = new TradingBroker();
        TradingBroker broker4 = new TradingBroker();

        broker1.updateStrategy(strategyA);
        broker1.updateName("Trader-1");
        broker2.updateStrategy(strategyA);
        broker2.updateName("Trader-2");
        broker3.updateStrategy(strategyA);
        broker3.updateName("Trader-3");
        broker4.updateStrategy(strategyA);
        broker4.updateName("Trader-4");
        
        // TEST 1: Adding trade results to TradeLog
        TradeResult result1 = new TradeResult(broker1, broker1.getStrategy(), "ETH", "Buy", 500, 150.3);
        TradeResult result2 = new TradeResult(broker2, broker2.getStrategy(), "BTC", "Sell", 200, 50.2);
        TradeResult result3 = new TradeResult(broker3, broker3.getStrategy(), "USDT", "Buy", 1000, 2.59);
        TradeResult result4 = new TradeResult(broker4, broker4.getStrategy(), "USDC", "Buy", 500, 150.3);
        TradeResult result5 = new TradeResult(broker2, broker2.getStrategy(), "ADA", "Sell", 200, 50.2);
        TradeResult result6 = new TradeResult(broker2, broker2.getStrategy(), "SOL", "Buy", 1000, 2.59);

        TradeLog log = new TradeLog();
        log.addResults(new ArrayList<TradeResult>(
            List.of(result1,result2,result3,result4, result5, result6)));

        printResults(log.getResults());

        // TEST 2: Render TradeActivityGraph and TradeActivityTable

        TradeActivityGraph graph = new TradeActivityGraph();
        TradeActivityTable table = new TradeActivityTable();
        
        log.attach(table);
        log.attach(graph);

        log.notifyObservers();

        // TEST 3: Adding Results to TradeLog (controlling the model) updates TradeActivityGraph and TradeActivityTable (the views).

        Scanner input = new Scanner(System.in);
        System.out.println("Press enter to add more results");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TradeResult result7 = new TradeResult(broker3, broker3.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result8 = new TradeResult(broker4, broker4.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result9 = new TradeResult(broker3, broker3.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result10 = new TradeResult(broker3, broker3.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result11 = new TradeResult(broker4, broker4.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result12 = new TradeResult(broker3, broker3.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result13 = new TradeResult(broker3, broker3.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result14 = new TradeResult(broker4, broker4.getStrategy(), "SOL", "Buy", 1000, 2.59);
        TradeResult result15 = new TradeResult(broker3, broker3.getStrategy(), "SOL", "Buy", 1000, 2.59);

        frame.removeAllStats();
        log.addResults(new ArrayList<TradeResult>(
            List.of(result7,result8,result9,result10,result11,result12,result13,result14,result15)));

    }

    private static void printResults(ArrayList<TradeResult> results) {
        results.forEach((result) -> {
            Object[] resultObj = result.getResultObj();
            System.out.printf("Name: %s, Strategy: %s, Coin: %s, Action: %s, Quantity: %s, Price: %s, Date: %s\n",
            resultObj[0], resultObj[1], resultObj[2], resultObj[3], resultObj[4], resultObj[5], resultObj[6]);
        });
    }
    
}
