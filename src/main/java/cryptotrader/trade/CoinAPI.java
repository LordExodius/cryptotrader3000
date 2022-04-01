package cryptotrader.trade;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.ArrayDeque;


public class CoinAPI {

	ArrayDeque<Long> cache;
	Long base;
	public CoinAPI() {
		cache = new ArrayDeque<Long>(50);
		base = Long.valueOf((long)(System.currentTimeMillis() / 1000));
		for (int i = 0; i < 50; i++) {
			cache.addFirst(base);
		}
	}

	/**
	 * A method that makes sure the CoinGecko API isn't called more than 50 times in a minute
	 * @throws CoinAPIException
	 */
	private void failSafe () throws CoinAPIException {
		Long currentTime = Long.valueOf((long)(System.currentTimeMillis() / 1000));
		Long marker = cache.removeFirst();

		if (currentTime - marker < 60 && base != marker) {
			throw new CoinAPIException("CoinGecko API has been accessed too many times in the last minute. Please wait.");
		}

		cache.addLast(currentTime);
	}

	private JsonObject getDataForCrypto(String id, String date) throws CoinAPIException {

		this.failSafe();

		String urlString = String.format(
				"https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				return jsonObject;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	
	public double getPriceForCoin(String id, String date) throws CoinAPIException {
		double price = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
			price = currentPrice.get("cad").getAsDouble();
		}
		
		return price;
	}
	
	public double getMarketCapForCoin(String id, String date) throws CoinAPIException {
		double marketCap = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("market_cap").getAsJsonObject();
			marketCap = currentPrice.get("cad").getAsDouble();
		}
		
		return marketCap;
	}
	
	public double getVolumeForCoin(String id, String date) throws CoinAPIException {
		double volume = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("total_volume").getAsJsonObject();
			volume = currentPrice.get("cad").getAsDouble();
		}
		
		return volume;
	}



	/**
	 * This method is the driver method for the class, taking in a list of coins a trader is interested in, and outputting a JsonObject with relatd info for it. It gets the current date, then creates a JsonObject of JsonObjects to return.
	 * 
	 * @param dataIn
	 * @return outData
	 */
	public JsonObject getData (ArrayList<String> dataIn) throws CoinAPIException {
		JsonObject outData = new JsonObject();

		// Get the current date
		Date rawDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(rawDate);

		// For each coin, get the relevant info, and add it to the outDate JsonObject
		for (String coin: dataIn) {
			double price = this.getPriceForCoin(coin, date);
			double marketCap = this.getMarketCapForCoin(coin, date);
			double volume = this.getVolumeForCoin(coin, date);
			
			JsonObject coinInfo = new JsonObject();
			coinInfo.addProperty("price", price);
			coinInfo.addProperty("marketCap", marketCap);
			coinInfo.addProperty("volume", volume);

			outData.add(coin, coinInfo);
		}

		return outData;

	}
	
	public static void main(String[] args) throws CoinAPIException {
		CoinAPI fetcher = new CoinAPI();
		double price = fetcher.getPriceForCoin("bitcoin", "08-09-2021");
		double marketCap = fetcher.getMarketCapForCoin("bitcoin", "08-09-2021");
		double volume = fetcher.getVolumeForCoin("bitcoin", "08-09-2021");
		
		System.out.println("Bitcoin=>\tPrice: " + price + 
								"\n\t\tMarket Cap: " + marketCap + 
								"\n\t\tVolume: "+volume);

		ArrayList<String> test = new ArrayList<String>();
		test.add("bitcoin");
		test.add("ethereum");

		System.out.println(fetcher.getData(test));
		
	}
}
