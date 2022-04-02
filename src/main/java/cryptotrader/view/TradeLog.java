package cryptotrader.view;

import java.util.ArrayList;

public class TradeLog extends Subject implements UpdateTradeLog {

    private ArrayList<TradeResult> entries;

    public void addResults(ArrayList<TradeResult> tradeResults) {
        // TODO Auto-generated method stub
        entries.addAll(tradeResults);
    }

    public ArrayList<TradeResult> getResults()
    {
        return entries;
    }
    
}