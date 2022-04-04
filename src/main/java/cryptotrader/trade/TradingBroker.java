package cryptotrader.trade;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonObject;

import cryptotrader.view.TradeResult;

public class TradingBroker implements UpdateBroker, ExecuteTrade {
    private static int nextID = 1;
    private int brokerID;
    private String name;
    private int numTrades;
    private ArrayList<String> coinList;
    private TradingStrategy strategy;

    public TradingBroker() {
        this.brokerID = nextID;
        nextID++;
        this.numTrades = 0;
        this.coinList = new ArrayList<String>();
        this.strategy = new StrategyA();
    }
    
    public TradingBroker(int brokerID)
    {
        this.brokerID = brokerID;
        if(brokerID > nextID)
            nextID = brokerID + 1;
        this.numTrades = 0;
        this.coinList = new ArrayList<String>();
        this.strategy = new StrategyA();
    }

    public int getID() {
        return brokerID;
    }

    public String getName() {
        return name;
    }

    public int getNumTrades() {
        return numTrades;
    }

    public ArrayList<String> getCoinList() {
        return coinList;
    }

    public TradingStrategy getStrategy() {
        return strategy;
    }

    @Override
    public TradeResult executeTrade(HashMap<String, Coin> coinInfo) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void updateName(String name) {
        // TODO Auto-generated method stub
        this.name = name;
    }
    @Override
    public void updateCoins(ArrayList<String> newCoins) {
        // TODO Auto-generated method stub
        this.coinList = newCoins;
    }
    @Override
    public void updateStrategy(TradingStrategy strategy) {
        // TODO Auto-generated method stub
       this.strategy = strategy;
    }

    public void setNumTrades(int numTrades)
    {
        this.numTrades = numTrades;
    }

    public boolean equals(TradingBroker other) {
        return this.brokerID == other.brokerID;
    }
}
