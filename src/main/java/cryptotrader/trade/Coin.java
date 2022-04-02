package cryptotrader.trade;

public class Coin {
    private String name;
    private double price;
    private double volume;
    private double marketCap;

    /**
     * Constructor
     * @param name
     * @param price
     * @param volume
     * @param marketCap
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
