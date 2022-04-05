package cryptotrader.view;

import java.util.ArrayList;

public interface UpdateTradeLog {

     /**
      * Adds TradeResults to the entries in the TradeLog, and notifies the users.
      * @param tradeResults a list of results to be logged.
      */
    public void addResults(ArrayList<TradeResult> tradeResults);
}