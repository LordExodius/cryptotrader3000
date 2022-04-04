package cryptotrader.view;

import java.util.ArrayList;

/**
 * Class acts as the model or the subject for the past trades to be observered by the observers/views.
 */
public class TradeLog extends Subject implements UpdateTradeLog {

    /**
     * List of TradeResults from the past trade activities.
     */
    private ArrayList<TradeResult> entries;

    public TradeLog() {
        this.entries = new ArrayList();
    }

    public void addResults(ArrayList<TradeResult> tradeResults) {
        // TODO Auto-generated method stub
        entries.addAll(tradeResults);
        notifyObservers();
    }

    /**
     * Getter method for the past results.
     * @return ArrayList of TradeResults of the past trade activities.
     */
    public ArrayList<TradeResult> getResults() {
        return entries;
    }
    
}