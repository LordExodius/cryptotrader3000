package cryptotrader.view;

import java.util.ArrayList;

public class TradeLog extends Subject implements UpdateTradeLog {

    public ArrayList<TradeResult> entries;

    public void addResults(ArrayList<TradeResult> tradeResults) {
        // TODO Auto-generated method stub
        entries.addAll(tradeResults);
    }
    
}