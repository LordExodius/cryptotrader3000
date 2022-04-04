package cryptotrader.user;

import java.util.HashMap;
import java.util.ArrayList;

import cryptotrader.trade.TraderList;
import cryptotrader.trade.CoinAPI;
import cryptotrader.trade.Coin;
import cryptotrader.trade.CoinAPIException;
import cryptotrader.trade.TradingBroker;
import cryptotrader.view.TradeLog;
import cryptotrader.view.TradeResult;

/**
 * This class represents the User class. In the MVC architecture, this
 * is the controller.
 * @author David Tran
 * @version 1.0
 */

public class User {
    // The singleton instance of this class
    private static User instance;
    // history of trades executed by this user
    private TradeLog tradeLog;
    // traders created by this user
    private TraderList traderList;
    // the Coin API used to fetch coin info
    private CoinAPI coinAPI = new CoinAPI();
    // currently active user
    private String username;

    private User() {
        this.tradeLog = new TradeLog();
        this.traderList = new TraderList();
        
    }

    /**
     * Returns a singleton instance for this class.
     * Lazily constructs the instance on the first call.
     * 
     * @return the singleton instance of this class.
     */
    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    /**
     * Fetch relevant coin info from the CoinAPI and perform trades for all
     * interested trading brokers in this user's trader list.
     * 
     */
    public void performTrades() {
        ArrayList<String> coinNames = new ArrayList<String>(traderList.getInterestedCoins());
        HashMap<String, Coin> coinInfo;
        ArrayList<TradeResult> tradeResults = new ArrayList<TradeResult>();
        try {
            coinInfo = coinAPI.getData(coinNames);
            // pass each trader the coins that they are interested in
            for (TradingBroker trader : traderList.getList()) {
                if (trader.getActive()) {
                    // filter out the coins that this trader is interested in
                    HashMap<String, Coin> interestedCoins = new HashMap<String, Coin>();
                    for (String interestedCoinName : trader.getCoinList()) {
                        interestedCoins.put(interestedCoinName, coinInfo.get(interestedCoinName));
                    }
                    // execute the trade for this trader
                    TradeResult result = trader.executeTrade(interestedCoins);
                    tradeResults.add(result);
                }
            }
        } catch (CoinAPIException e) {
            System.out.println(e.getMessage());
            return;
        }
        return;
    }

    /**
     * set the User's username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the trade log
     * @param tradeLog
     */
    public void setTradeLog(TradeLog tradeLog) {
        this.tradeLog = tradeLog;
    }

    /**
     * Set the trader list
     * @param traderList
     */
    public void setTraderList(TraderList traderList) {
        this.traderList = traderList;
    }

    /**
     * Get the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the trade log
     * @return tradelog
     */
    public TradeLog getTradeLog() {
        return tradeLog;
    }

    /**
     * Get the list of traders
     * @return traderList
     */
    public TraderList getTraderList() {
        return traderList;
    }
}
