package cryptotrader.authentication;

import cryptotrader.trade.TraderList;
import cryptotrader.view.TradeLog;

/**
 * An interface for a database class that can store a TraderList and TradeLog.
 * 
 * @author Oscar Yu, David Tran
 * @version 1.0
 */
public interface AddToDatabase {
    /**
     * Add the specified TraderList to the database.
     * @param traders The TraderList to add to the database.
     */
    public void addTraders(TraderList traders);

    /**
     * Add the specified TradeLog to the database.
     * @param log The TradeLog to add to the database.
     */
    public void addTradeLog(TradeLog log);
}
