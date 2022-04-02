package cryptotrader.user;

import com.google.gson.JsonObject;
import java.util.HashMap;

import cryptotrader.trade.TraderList;
import cryptotrader.trade.CoinAPI;
import cryptotrader.view.TradeLog;

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

    private User() {}

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
     * TODO
     */
    public void performTrades() {
        return;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTradeLog(TradeLog tradeLog) {
        this.tradeLog = tradeLog;
    }

    public void setTraderList(TraderList traderList) {
        this.traderList = traderList;
    }

    public String getUsername() {
        return username;
    }

    public TradeLog getTradeLog() {
        return tradeLog;
    }

    public TraderList getTraderList() {
        return traderList;
    }
}
