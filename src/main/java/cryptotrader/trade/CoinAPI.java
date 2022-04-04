package cryptotrader.trade;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * A class CoinAPI that calls the CoinGecko API. It packages the info it receives in a HashMap
 * of String and Coin.
 * @author Jackson Howe
 * @version 1.0
 */

public class CoinAPI {

	ArrayDeque<Long> cache;
	long base;
	public CoinAPI() {
		cache = new ArrayDeque<Long>(50);
		long timeMillis = System.currentTimeMillis();
		base = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
		for (int i = 0; i < 50; i++) {
			cache.addFirst(base);
		}
	}

	/**
	 * A method that makes sure the CoinGecko API isn't called more than 50 times in a minute
	 * @throws CoinAPIException
	 */
	private void failSafe () throws CoinAPIException {
		long millis = System.currentTimeMillis();
		long currentTime = TimeUnit.MILLISECONDS.toSeconds(millis);
		long marker = cache.removeFirst();

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
	 * Takes in the 3 letter name identifier of a coin and creates coin objects from the API call, then returns the map of coins
	 * 
	 * @param dataIn
	 * @return outData
	 */
	public HashMap<String, Coin> getData (ArrayList<String> dataIn) throws CoinAPIException {
		HashMap<String, Coin> coinInfo = new HashMap<String, Coin>();

		// Get the current date
		Date rawDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(rawDate);

		// For each coin, get the relevant info, and create a Coin object. Add this Coin to the coin list
		for (String coin: dataIn) {
	
			String name;
			if (coin == "BTC") {
				name = "bitcoin";
			} else if (coin == "ETH") {
				name = "ethereum";
			} else if (coin == "LTC") {
				name = "litecoin";
			} else if (coin == "ADA") {
				name = "cardano";
			} else if (coin == "SOL") {
				name = "solana";
			} else {
				continue;
			}

			double price = this.getPriceForCoin(name, date);
			double marketCap = this.getMarketCapForCoin(name, date);
			double volume = this.getVolumeForCoin(name, date);

			Coin newCoin = new Coin(coin, price, marketCap, volume);
			coinInfo.put(coin, newCoin);
		}

		return coinInfo;

	}
	
	public static void main(String[] args) throws CoinAPIException {

		CoinAPI test = new CoinAPI();
		// Test 1
		ArrayList<String> testIn = new ArrayList<String>() {{
			add("bitcoin");
			add("ethereum");
			add("litecoin");
			add("solana");
			add("cardano");
		}};
		// HashMap<String, Coin> test1 = test.getData(testIn);
		// for (Coin coin : test1.values()) {
		// 	System.out.println(coin.getName() + ", price: " + coin.getPrice() + ", volume: " + coin.getVolume() + ", market cap: " + coin.getmarketCap());
		// }

		// Test 2
		for (int i = 0; i < 60; i++) {
			try {
				test.getData(testIn);
				System.out.println("Call " + i + " passed");
			} catch (CoinAPIException e) {
				System.out.println("Call " +  " blocked");
			}
		}

		// CoinAPI fetcher = new CoinAPI();
		// double price = fetcher.getPriceForCoin("bitcoin", "08-09-2021");
		// double marketCap = fetcher.getMarketCapForCoin("bitcoin", "08-09-2021");
		// double volume = fetcher.getVolumeForCoin("bitcoin", "08-09-2021");
		
		// System.out.println("Bitcoin=>\tPrice: " + price + 
		// 						"\n\t\tMarket Cap: " + marketCap + 
		// 						"\n\t\tVolume: "+volume);

		// ArrayList<String> test = new ArrayList<String>();
		// test.add("bitcoin");
		// test.add("ethereum");

		// System.out.println(fetcher.getData(test));
		
	}
}
