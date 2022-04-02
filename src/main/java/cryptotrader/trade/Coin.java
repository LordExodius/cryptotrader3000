package cryptotrader.trade;

public class Coin {
    private double price;
    private double volume;
    private double marketCap;

    /**
     * Constructor
     * @param price
     * @param volume
     * @param marketCap
     */
    public Coin (double price, double volume, double marketCap) {
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * 
     * @return price
     */
    public double getPrice () {
        return price;
    }

    /**
     * 
     * @return volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * 
     * @return marektCap
     */
    public double getmarketCap() {
        return marketCap;
    }
}
