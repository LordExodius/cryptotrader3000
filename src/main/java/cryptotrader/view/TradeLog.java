package cryptotrader.view;

import java.util.ArrayList;

/**
 * Class acts as the model or the subject for the past trades to be observered by the observers/views.
 * 
 * @author Ben Asokanthan
 * @version 1.0
 */
public class TradeLog extends Subject implements UpdateTradeLog {

    /**
     * List of TradeResults from the past trade activities.
     */
    private ArrayList<TradeResult> entries;

    /**
     * Constructor
     */
    public TradeLog() {
        this.entries = new ArrayList<TradeResult>();
    }

    /**
     * Add trade results to all observers (UI elements)
     * @param tradeResults The list of TradeResults to add to the TradeLog.
     */
    public void addResults(ArrayList<TradeResult> tradeResults) {
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