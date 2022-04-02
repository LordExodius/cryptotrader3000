package cryptotrader.trade;

import java.util.ArrayList;
import java.util.HashSet;

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
    
}
