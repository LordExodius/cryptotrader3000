package cryptotrader.trade;

/**
 * A class that throws an Exception if the Coin API is called too many times in 1 minute
 * @author Jackson Howe
 * @version 1.0
 */

public class CoinAPIException extends RuntimeException {
    public CoinAPIException (String message) {
        super(message);
    }
}
