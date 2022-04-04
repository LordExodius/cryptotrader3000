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

import java.util.HashMap;

/**
 * A class CoinAPI that calls the CoinGecko API. It packages the info it receives in a HashMap
 * of String and Coin.
 * @author Jackson Howe
 * @version 1.0
 */

public class CoinAPI {
	/**
	 * Make a GET request from the CoinGecko API. Returns the information for the coin
	 * specified in `ID` at the date specified in `date`
	 * 
	 * @param id the CoinGecko id for a cryptocoin (e.g. bitcoin, solana, etc.)
	 * @param date the date of the info that should be retrieved
	 * @return A JsonObject containing the coin info as described here 
	 * 		   https://www.coingecko.com/en/api/documentation
	 * @throws CoinAPIException
	 */
	private JsonObject getDataForCrypto(String id, String date) throws CoinAPIException {		
		try {
			// make the GET request to the API
			String urlString = String.format(
					"https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				// success
				// parse the response data
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				return jsonObject;
			} else if (responseCode == 429) {
				// exceeded API calls
				throw new CoinAPIException(
					"The CoinGecko API has been accessed too many times in the last minute. Please wait.");
			} else {
				throw new CoinAPIException("There was an error calling the CoinGecko API: Status code "
					+ responseCode);
			}
		} catch (IOException e) {
			throw new CoinAPIException(
				"The CoinGecko API has been accessed too many times in the last minute. Please wait.");
		}
	}
	
	/**
	 * Get the price of coin with the specified ID at the time `date` in CAD.
	 * 
	 * @param id   the CoinGecko id for a cryptocoin (e.g. bitcoin, solana, etc.)
	 * @param date the date of the info that should be retrieved
	 * @return the price in CAD
	 * @throws CoinAPIException
	 */
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
	
	/**
	 * Get the market cap of the coin with the specified ID at the time `date` in CAD.
	 *
	 * @param id   the CoinGecko id for a cryptocoin (e.g. bitcoin, solana, etc.)
	 * @param date the date of the info that should be retrieved
	 * @return the market cap in CAD
	 * @throws CoinAPIException
	 */
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
	
	/**
	 * Get the trading volume of the coin with the specified ID at the time `date` in CAD.
	 * 
	 * @param id   the CoinGecko id for a cryptocoin (e.g. bitcoin, solana, etc.)
	 * @param date the date of the info that should be retrieved
	 * @return the trading volume in CAD.
	 * @throws CoinAPIException
	 */
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
			// convert ticker into name
			switch (coin) {
				case "BTC":
					name = "bitcoin";
					break;
				case "ETH":
					name = "ethereum";
					break;
				case "LTC":
					name = "litecoin";
					break;
				case "ADA":
					name = "cardano";
					break;
				case "SOL":
					name = "solana";
					break;
				default:
					// ignore this coin if not supported
					System.err.println("Unsupported coin. Use BTC, ETH, LTC, ADA, or SOL");
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
			add("BTC");
			add("ETH");
			add("LTC");
			add("SOL");
			add("ADA");
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
				System.out.println("Call " + i + " blocked");
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
