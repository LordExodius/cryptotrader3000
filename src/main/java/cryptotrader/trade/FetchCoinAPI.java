package cryptotrader.trade;

import com.google.gson.JsonObject;
import java.util.ArrayList;

/**
 * An interface that CoinAPI uses to get data from the CoinGecko API
 */

public interface FetchCoinAPI {
    /**
     * A method that fetches the current coin data for each of the required coins and returns the
     * data to the user class
     * @param dataIn
     * @return coinInfo, a HashMap of keys that are Strings, and values that are Coin objects
     */
    public JsonObject getData(ArrayList<String> dataIn);
}
