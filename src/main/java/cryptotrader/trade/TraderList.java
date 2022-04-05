package cryptotrader.trade;

import cryptotrader.view.TradeResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A class that holds and performs operations on the active list of traders
 * @author Ben Asokanthan, Jackson Howe
 * @version 1.0
 */

public class TraderList implements UpdateTraderList {

    private ArrayList<TradingBroker> traderList;
    // all coins that at least one trader is interested in
    private HashSet<String> interestedCoins;

    /**
     * Constructor
     */
    public TraderList() {
        traderList = new ArrayList<TradingBroker>();
        interestedCoins = new HashSet<String>();
    }

    @Override
    /**
     * Method that checks if a trader to be added is already in the trade list, then adds it in
     * @param newTrader the trader to add to the list
     */
    public boolean addTrader(TradingBroker newTrader) {
        if (!(traderList.contains(newTrader))) {
            traderList.add(newTrader);

            for (String coin : newTrader.getCoinList()) {
                if (!interestedCoins.contains(coin)) {
                    interestedCoins.add(coin);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    /**
     * A method that removes and returns a specified trader
     * @param brokerName the trader to remove from the list
     * @return removedTrader
     */
    public TradingBroker removeTrader(String brokerName) {
        TradingBroker removedTrader = null;
        for (TradingBroker trader : traderList) {
            if (trader.getName().equals(brokerName))
                removedTrader = trader;
        }
        
        if (removedTrader != null) {
            traderList.remove(removedTrader);
        }

        return removedTrader;
    }

    @Override
    /**
     * Method that adds a coin to a broker in the trader list
     * 
     * @param brokerName name of the trader to update
     * @param newCoins   list of coin names (tickers) that this trader is interested
     *                   in
     * @return boolean
     */
    public boolean updateCoins(String brokerName, ArrayList<String> newCoins) {
        for (TradingBroker trader : traderList) {
            if (trader.getName().equals(brokerName)) {
                trader.updateCoins(newCoins);
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * Method that updates a trader's strategy
     * 
     * @param brokerName name of the trader to update
     * @param strategy   list of coin names (tickers) that this trader is interested
     *                   in
     * @return boolean
     */
    public boolean updateStrategy(String brokerName, TradingStrategy strategy) {
        for (TradingBroker trader : traderList) {
            if (trader.getName().equals(brokerName)) {
                trader.updateStrategy(strategy);;
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns the trader list
     * @return traderList
     */
    public ArrayList<TradingBroker> getList()
    {
        return traderList;
    }

    /**
     * Get the coin names where each coin is of interest to at least one trader.
     * @return interestedCoins
     */
    public HashSet<String> getInterestedCoins() {
        return interestedCoins;
    }

    /**
     * Get a trader by its ID. If a trader with the specified ID does not exist,
     * returns null.
     * 
     * @param brokerName the name of the trading broker
     */
    public TradingBroker getTrader(String brokerName) {
        for (TradingBroker trader : traderList) {
            if (trader.getName().equals(brokerName)) {
                return trader;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Coin btc = new Coin("BTC", 23.45, 2413.345, 2345.6);
        Coin eth = new Coin("ETH", 23.45, 2413.345, 2345.6);
        Coin ltc = new Coin("LTC", 23.45, 2413.345, 2345.6);
        Coin ada = new Coin("ADA", 23.45, 2413.345, 2345.6);
        Coin sol = new Coin("SOL", 23.45, 2413.345, 2345.6);

        ArrayList<String> allCoins = new ArrayList<String>() {{
            add("BTC");
            add("ETH");
            add("LTC");
            add("ADA");
            add("SOL");
        }};

        TradingStrategy a = new StrategyA();
        TradingStrategy b = new StrategyB();
        TradingStrategy c = new StrategyC();
        TradingStrategy d = new StrategyD();

        TradingBroker trader1 = new TradingBroker("John");
        TradingBroker trader2 = new TradingBroker("2");
        TradingBroker trader3 = new TradingBroker("3");
        TradingBroker trader4 = new TradingBroker("4");

        trader1.updateStrategy(a);
        trader2.updateStrategy(b);
        trader3.updateStrategy(c);
        trader4.updateStrategy(d);

        trader1.updateCoins(allCoins);
        trader2.updateCoins(allCoins);
        trader3.updateCoins(allCoins);
        trader4.updateCoins(allCoins);

        HashMap<String, Coin> testMap = new HashMap<String, Coin>() {{
            put("BTC", btc);
            put("ETH", eth);
            put("LTC", ltc);
            put("SOL", sol);
            put("ADA", ada);
        }};

        // Test 1
        TradeResult res1 =  trader1.executeTrade(testMap);
        TradeResult res2 =  trader2.executeTrade(testMap);
        TradeResult res3 =  trader3.executeTrade(testMap);
        TradeResult res4 =  trader4.executeTrade(testMap);

        // Test 1
        if (res1.getCoinName() == "ADA" && res2.getCoinName() == "SOL" && res3 == null && res4 == null) {
            System.out.println("Test 1 passed");
        } else {
            System.out.println("Test 2 failed");
        }

        TraderList traders = new TraderList();
        traders.addTrader(trader1);
        traders.addTrader(trader2);
        traders.addTrader(trader3);
        traders.addTrader(trader4);

        TradingBroker trader5 = new TradingBroker();
        trader5.updateStrategy(a);
        trader5.updateCoins(allCoins);

        // Test 2
        if (!traders.addTrader(trader1) && traders.addTrader(trader5)) {
            System.out.println("Test 2 passed");
        } else {
            System.out.println("Test 2 failed");
        }

        trader1.updateName("John");

        // Test 3
        if (traders.removeTrader("John").getName().equals("John")) {
            System.out.println("Test 3 passed");
        } else {
            System.out.println("Test 3 failed");
        }

        // Test 4
        if (traders.updateCoins("2", new ArrayList<String>(Arrays.asList("sdjkl")))) {
            System.out.println("Test 4 passed");
        } else {
            System.out.println("Test 4 failed");
        }

        // Test 5
        if (traders.updateStrategy("3", d)) {
            System.out.println("Test 5 passed");
        } else {
            System.out.println("Test 5 failed");
        }
    }

    
    
}
