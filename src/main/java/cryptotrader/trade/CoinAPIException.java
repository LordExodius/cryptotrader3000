package cryptotrader.trade;

/**
 * This custom-exception class is thrown for errors occuring in the CoinAPI class.
 * @author Jackson Howe
 * @version 1.0
 */

public class CoinAPIException extends RuntimeException {
    public CoinAPIException (String message) {
        super(message);
    }
}
