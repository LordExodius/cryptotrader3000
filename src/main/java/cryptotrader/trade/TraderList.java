package cryptotrader.trade;

import cryptotrader.view.TradeResult;

import java.util.ArrayList;
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
     * @param newTrader
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
     * @param brokerID
     * @return removedTrader
     */
    public TradingBroker removeTrader(int brokerID) {
        TradingBroker removedTrader = null;
        for (TradingBroker trader : traderList) {
            if (trader.getID() == brokerID)
                removedTrader = trader;
        }
        
        if (removedTrader != null) {
            traderList.remove(removedTrader);
        }

        return removedTrader;
    }

    @Override
    /**
     * Method that updates a trader in trader list's name
     * @param brokerID
     * @param brokerName
     * @return boolean
     */
    public boolean updateName(int brokerID, String brokerName) {
        for (TradingBroker trader : traderList) {
            if (trader.getID() == brokerID) {
                trader.updateName(brokerName);
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * Method that adds a coin to a broker in the trader list
     * @param brokerID
     * @param newCoin
     * @return boolean
     */
    public boolean addCoin(int brokerID, String newCoin) {
        for (TradingBroker trader : traderList) {
            if (trader.getID() == brokerID) {
                trader.addCoin(newCoin);
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * Method that updates a trader's strategy
     * @param brokerID
     * @param strategy
     * @return boolean
     */
    public boolean updateStrategy(int brokerID, TradingStrategy strategy) {
        for (TradingBroker trader : traderList) {
            if (trader.getID() == brokerID) {
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

        TradingBroker trader1 = new TradingBroker();
        TradingBroker trader2 = new TradingBroker();
        TradingBroker trader3 = new TradingBroker();
        TradingBroker trader4 = new TradingBroker();

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
        if (traders.removeTrader(1).getName() == "John") {
            System.out.println("Test 3 passed");
        } else {
            System.out.println("Test 3 failed");
        }

        // Test 4
        if (traders.addCoin(2, "poop")) {
            System.out.println("Test 4 passed");
        } else {
            System.out.println("Test 4 failed");
        }

        // Test 5
        if (traders.updateStrategy(3, d)) {
            System.out.println("Test 5 passed");
        } else {
            System.out.println("Test 5 failed");
        }
    }
    /**
     * Get a trader by its ID. If a trader with the specified ID does not exist,
     * returns null.
     * 
     * @param id the ID of the trader to retrieve
     */
    public TradingBroker getTrader(int id) {
        for (TradingBroker trader : traderList) {
            if (trader.getID() == id) {
                return trader;
            }
        }
        return null;
    }
    
}
