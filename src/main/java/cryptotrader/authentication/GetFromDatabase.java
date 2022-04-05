package cryptotrader.authentication;

import cryptotrader.trade.TraderList;
import cryptotrader.view.TradeLog;

/**
 * An interface for a database class that implements retrieval of a TraderList
 * and TradeLog.
 * 
 * @author Oscar Yu, David Tran
 * @version 1.0
 */
public interface GetFromDatabase {
    /**
     * Retrieve a TraderList from the database.
     * @return The TraderList stored in the database.
     */
    public TraderList getTraders();

    /**
     * Retrieve a TradeLog from the database.
     * @param traderList The TraderList associated with the trades in the trade log
     * @return The TradeLog stored in the database.
     */
    public TradeLog getTradeLog(TraderList traderList);
}
