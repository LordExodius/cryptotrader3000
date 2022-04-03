package cryptotrader.view;

import java.util.ArrayList;

public class TradeLog extends Subject implements UpdateTradeLog {

    private ArrayList<TradeResult> entries;

    public TradeLog() {
        this.entries = new ArrayList();
    }

    public void addResults(ArrayList<TradeResult> tradeResults) {
        // TODO Auto-generated method stub
        entries.addAll(tradeResults);
        notifyObservers();
    }

    public ArrayList<TradeResult> getResults() {
        return entries;
    }
    
}