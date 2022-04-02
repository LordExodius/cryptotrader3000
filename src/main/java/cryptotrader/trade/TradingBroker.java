package cryptotrader.trade;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import cryptotrader.view.TradeResult;

public class TradingBroker implements UpdateBroker, ExecuteTrade {
    private static int nextID = 1;
    public int brokerID;
    public String name;
    public int numTrades;
    public ArrayList<String> coinList;
    public TradingStrategy strategy;

    public TradingBroker(String brokerID) {
        this.brokerID = nextID;
        this.nextID++;
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
    public TradeResult executeTrade() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void updateName(String name) {
        // TODO Auto-generated method stub
        this.name = name;
    }
    @Override
    public void addCoin(String newCoinName) {
        // TODO Auto-generated method stub
        coinList.add(newCoinName);
    }
    @Override
    public void updateStrategy(TradingStrategy strategy) {
        // TODO Auto-generated method stub
       this.strategy = strategy;
    }

    public boolean equals(TradingBroker other) {
        return this.brokerID == other.brokerID;
    }
}
