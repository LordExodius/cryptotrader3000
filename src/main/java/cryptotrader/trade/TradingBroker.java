package cryptotrader.trade;

import java.util.ArrayList;
import java.util.HashMap;

import cryptotrader.view.TradeResult;

public class TradingBroker implements UpdateBroker, ExecuteTrade {
    private static int nextID = 1;
    private int brokerID;
    private String name;
    private int numTrades;
    private ArrayList<String> coinList;
    private TradingStrategy strategy;

    /**
     * Constructor
     */
    public TradingBroker() {
        this.brokerID = nextID;
        nextID++;
        this.numTrades = 0;
        this.coinList = new ArrayList<String>();
        this.strategy = new StrategyA();
    }

    /**
     * Constructor
     * @param brokerID
     */
    public TradingBroker(int brokerID)
    {
        this.brokerID = brokerID;
        if(brokerID > nextID)
            nextID = brokerID + 1;
        this.numTrades = 0;
        this.coinList = new ArrayList<String>();
        this.strategy = new StrategyA();
    }

    /**
     * Get method for broker's ID
     * @return brokerID
     */
    public int getID() {
        return brokerID;
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
     * Calls the strategy's trade method
     * @return TradeResult
     */
    public TradeResult executeTrade(HashMap<String, Coin> interestedCoins) {
        return strategy.trade(interestedCoins);
        
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
        return this.brokerID == other.brokerID;
    }
}
