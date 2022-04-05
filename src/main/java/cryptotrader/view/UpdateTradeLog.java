package cryptotrader.view;

import java.util.ArrayList;

/**
 * An interface to be implemented by a class that allows the updating of its
 * trade log.
 * 
 * @author Ben Asokanthan
 * @version 1.0
 */
public interface UpdateTradeLog {

     /**
      * Adds TradeResults to the entries in the TradeLog, and notifies the users.
      * @param tradeResults a list of results to be logged.
      */
    public void addResults(ArrayList<TradeResult> tradeResults);
}