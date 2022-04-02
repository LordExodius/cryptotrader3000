package cryptotrader.user;

import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.ArrayList;

import cryptotrader.trade.TraderList;
import cryptotrader.trade.CoinAPI;
import cryptotrader.trade.Coin;
import cryptotrader.trade.CoinAPIException;
import cryptotrader.trade.TradingBroker;
import cryptotrader.view.TradeLog;
import cryptotrader.view.TradeResult;

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
     */
    public void performTrades() {
        ArrayList<String> coinNames = new ArrayList<String>(traderList.getInterestedCoins());
        HashMap<String, Coin> coinInfo;
        ArrayList<TradeResult> tradeResults;
        try {
            coinInfo = coinAPI.getData(coinNames);
            // pass each trader the coins that they are interested in
            for (TradingBroker trader : traderList.getList()) {
                // filter out the coins that this trader is interested in
                HashMap<String, Coin> interestedCoins = new HashMap<String, Coin>();
                for (String interestedCoinName : trader.getCoinList()) {
                    interestedCoins.put(interestedCoinName, coinInfo.get(interestedCoinName));
                }
                // execute the trade for this trader
                TradeResult result = trader.executeTrade(interestedCoins);
                tradeResults.add(result);
            }
        } catch (CoinAPIException e) {
            // TODO: How should we handle an error to the API call (e.g. because of timeout)
            return;
        }
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
