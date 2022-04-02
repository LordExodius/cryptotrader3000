package cryptotrader.authentication;

import cryptotrader.trade.TraderList;
import cryptotrader.view.TradeLog;

public interface AddToDatabase {
    public void addTraders(TraderList traders);
    public void addTradeLog(TradeLog log);
}
