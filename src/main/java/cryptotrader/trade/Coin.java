package cryptotrader.trade;

/**
 * A class that represents a coin object generated from the CoinGecko API call.
 * @author Jackson Howe
 * @version 1.0
 */

public class Coin {
    private String name;
    private double price;
    private double volume;
    private double marketCap;

    /**
     * Constructor
     * @param name the name of the coin
     * @param price the current price of the coin in CAD
     * @param volume the trading volume of the coin in CAD
     * @param marketCap the market cap of the coin in CAD
     */
    public Coin (String name, double price, double volume, double marketCap) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * Return coin price
     * @return price
     */
    public double getPrice () {
        return price;
    }

    /**
     * Return coin volume
     * @return volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Return coin market cap
     * @return marketCap
     */
    public double getmarketCap() {
        return marketCap;
    }

    /**
     * Return 3 letter name identifier of coin
     * @return name
     */
    public String getName() {
        return name;
    }
}
