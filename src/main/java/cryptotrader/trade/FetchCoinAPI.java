package cryptotrader.trade;

import com.google.gson.JsonObject;
import java.util.ArrayList;

public interface FetchCoinAPI {
    public JsonObject getData(ArrayList<String> dataIn);
}
