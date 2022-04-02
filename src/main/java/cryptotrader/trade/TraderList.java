package cryptotrader.trade;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class TraderList implements UpdateTraderList {

    private ArrayList<TradingBroker> traderList;
    // all coins that at least one trader is interested in
    private HashSet<String> interestedCoins;

    public TraderList() {
        traderList = new ArrayList<TradingBroker>();
        interestedCoins = new HashSet<String>();
    }

    @Override
    public boolean addTrader(TradingBroker newTrader) {
        // TODO Auto-generated method stub
        traderList.add(newTrader);
        return true;
    }

    @Override
    public TradingBroker removeTrader(int brokerID) {
        // TODO Auto-generated method stub
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
    public boolean updateName(int brokerID, String brokerName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addCoin(int brokerID, String newCoin) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateStrategy(int brokerID, TradingStrategy strategy) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Get the coin names where each coin is of interest to at least one trader.
     * @return
     */
    public HashSet<String> getInterestedCoins() {
        return interestedCoins;
    }
    
}
