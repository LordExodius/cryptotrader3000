package cryptotrader.authentication;

import cryptotrader.trade.TraderList;
import cryptotrader.view.TradeLog;

public interface GetFromDatabase {
    public TraderList getTraders();
    public TradeLog getTradeLog();
}
