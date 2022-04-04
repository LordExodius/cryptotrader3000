package cryptotrader.trade;

import java.util.ArrayList;
import java.util.HashMap;

import cryptotrader.view.TradeResult;

/**
 * A class that represents a trading broker
 * @author Ben Asokanthan, Jackson Howe
 * @version 1.0
 */

public class TradingBroker implements UpdateBroker, ExecuteTrade {
    private String name;
    private int numTrades;
    private ArrayList<String> coinList;
    private TradingStrategy strategy;
    private boolean active;

    /**
     * Constructor
     */
    public TradingBroker() {
        this.numTrades = 0;
        this.coinList = new ArrayList<String>();
        this.strategy = null;
        this.active = false;
    }

    public TradingBroker(String brokerName) {
        this();
        this.name = brokerName;
    }

    /**
     * Get method for broker's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get method for number of trades broker has completed
     * @return numTrades
     */
    public int getNumTrades() {
        return numTrades;
    }

    /**
     * Get method for list of coins broker is interested in
     * @return coinList
     */
    public ArrayList<String> getCoinList() {
        return coinList;
    }

    /**
     * Get method for trading broker's strategy
     * @return strategy
     */
    public TradingStrategy getStrategy() {
        return strategy;
    }

    @Override
    /**
     * Calls the strategy's trade method. Returns null if no strategy exist
     * or the strategy is not satisfied.
     * @param coinInfo
     * @return TradeResult 
     */
    public TradeResult executeTrade(HashMap<String, Coin> coinInfo) {
        if (strategy != null) {
            TradeResult result = strategy.trade(coinInfo);
            if (result == null)
                return null;
            result.setBroker(this);
            return result;
        } else {
            return null;
        }
    }

    @Override
    /**
     * Update the trader's name
     * @param name
     */
    public void updateName(String name) {
        this.name = name;
    }

    @Override
    /**
     * Updates (changes) the trader's list of coins
     * @param newCoins
     */
    public void updateCoins(ArrayList<String> newCoins) {
        this.coinList = newCoins;
    }

    /**
     * Method that adds a new coin to a trader's list
     * @param newCoin
     */
    public void addCoin(String newCoin) {
        if (!coinList.contains(newCoin)) {
            coinList.add(newCoin);
        }
    }
    @Override
    /**
     * Changes the trader's trading strategy
     * @param strategy
     */
    public void updateStrategy(TradingStrategy strategy) {
       this.strategy = strategy;
    }

    /**
     * Sets the number of trades this trader has completed
     * @param numTrades
     */
    public void setNumTrades(int numTrades)
    {
        this.numTrades = numTrades;
    }

    /**
     * Compares if two traders are the same
     * @param other
     * @return boolean
     */
    public boolean equals(TradingBroker other) {
        return this.getName().equals(other.getName());
    }

    public boolean getActive() {
        return this.active; 
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
