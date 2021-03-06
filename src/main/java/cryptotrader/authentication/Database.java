package cryptotrader.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import cryptotrader.trade.StrategyCreator;
import cryptotrader.trade.TraderList;
import cryptotrader.trade.TradingBroker;
import cryptotrader.trade.TradingStrategy;
import cryptotrader.user.User;
import cryptotrader.view.TradeLog;
import cryptotrader.view.TradeResult;

/**
 * A class that connects to and performs queries on the project database.
 * Implements the singleton pattern as only one database should be active at any time.
 * @author Oscar Yu, David Tran
 * @version 1.0
 */
public class Database implements DatabaseAuthenticate, GetFromDatabase, AddToDatabase {
    /**
     * Databse contains 3 tables:
     * - creds(user text, pass text)
     * - brokers(user text, name text, numTrades int, coinList text, strategy text)
     * - results(user text, name text, strategy text, coinName text, action text, quantity int, price real, date text)
     */

    // the singleton instance
    private static Database instance = new Database();
    // the SQL connection to the database
    private Connection connection;

    /**
     * Default construct the database by initializing the SQL connection.
     */
    private Database() {
        try {
            // create connection to database file
            connection = DriverManager.getConnection("jdbc:sqlite:./db/auth.db");
        } catch(SQLException e) {
            // handle SQL exception when creating connection to database file
            System.out.println("An SQLException has occurred. Error message:");
            System.out.println(e);
        }
    }
     
    /**
     * Returns a boolean value specifying the success or failure of a login attempt.
     * The username provided is used to perform a password lookup in the user credential 
     * database. If the provided password matches the lookup, the method will return true.
     * 
     * @param username  a string containing the username of the user attempting login
     * @param password  a string containing the password of the user attempting login
     * @return          boolean success or failure of authentication (true/false)
     */
    @Override
    public boolean authenticate(String username, String password) 
    {
        // Retrieve salt from row with matching username
        String getSalt = "SELECT salt FROM creds WHERE user = ?";
        // Count rows with matching user/hashed password combination
        String auth = "SELECT COUNT(*) as total FROM creds WHERE user = ? AND pass = ?";
        try 
        {
            // if user exists, get matching salt and prepend to password
            PreparedStatement saltStatement = connection.prepareStatement(getSalt);
            saltStatement.setString(1, username);
            ResultSet result = saltStatement.executeQuery();
            if(!result.next()) // no entry exists with given username; return false
                return false;
            String salt = result.getString("salt");
            String salted = salt + password;

            PreparedStatement authStatement = connection.prepareStatement(auth);
            authStatement.setString(1, username);

            // hash salted password
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(salted.getBytes(StandardCharsets.UTF_8));
            String passEncoded = Base64.getEncoder().encodeToString(hash);
            authStatement.setString(2, passEncoded);

            result = authStatement.executeQuery();
            int matches = result.getInt("total");
            result.close();

            // if matching row exists, return true
            if(matches > 0)
                return true;
                
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Error hashing password:");
            System.out.println(e);
        }
        catch(SQLException e) 
        {
            System.out.println("An SQL error has occured during authentication:");
            System.out.println(e);
        }
        return false;
    }

    /**
     * Returns the active instance of Database, or creates it if not initialized.
     * @return active instance of Database object
     */
    public static Database getInstance()
    {
        if(instance == null)
            instance = new Database();
        return instance;
    }

    /**
     * Stores TradingBroker objects in the database
     * @param traders a TraderList object containing all the TradingBroker objects associated with the current User instance
     */
    @Override
    public void addTraders(TraderList traders) {
        if(traders == null)
            return;
        String add = "INSERT INTO brokers(user, name, numTrades, coinList, strategy, active) VALUES(?, ?, ?, ?, ?, ?)";
        for(TradingBroker trader : traders.getList())
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(add);
                statement.setString(1, User.getInstance().getUsername());
                statement.setString(2, trader.getName());
                statement.setInt(3, trader.getNumTrades());
                statement.setString(4, String.join(",", trader.getCoinList()));
                statement.setString(5, trader.getStrategy().getName());
                statement.setString(6, String.valueOf(trader.getActive()));
                statement.executeUpdate();
            }
            catch(SQLException e)
            {
                System.out.println("An SQL error has occured while storing trader data:");
                System.out.println(e);
            }
        }
    }

    /**
     * Stores TradeResult objects in the database
     * @param log a TradeLog object containing all TradeResult objects associated with current User instance
     */
    @Override
    public void addTradeLog(TradeLog log) {
        if(log == null)
            return;
        String add = "INSERT INTO results(user, name, strategy, coinName, action, quantity, price, date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        for(TradeResult result : log.getResults())
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(add);
                statement.setString(1, User.getInstance().getUsername());
                statement.setString(2, result.getBroker().getName());
                statement.setString(3, result.getStrategy().getName());
                statement.setString(4, result.getCoinName());
                statement.setString(5, result.getActionType());
                statement.setInt(6, result.getQuantity());
                statement.setDouble(7, result.getPrice());
                statement.setString(8, result.getTimestamp());
                statement.executeUpdate();
            }
            catch(SQLException e)
            {
                System.out.println("An SQL error has occured while storing trade result data:");
                System.out.println(e);
            }
        }
    }

    /**
     * Retrieves all traders associated with currently active User instance from database
     * @return TraderList of all traders saved in database with matching username
     */
    @Override
    public TraderList getTraders() {
        TraderList list = new TraderList();
        String get = "SELECT * from brokers WHERE user = ?";
        try
        {
            PreparedStatement statement = connection.prepareStatement(get);
            statement.setString(1, User.getInstance().getUsername());
            ResultSet results = statement.executeQuery();
            TradingBroker tempBroker;
            StrategyCreator creator = new StrategyCreator();
            while(results.next())
            {
                tempBroker = new TradingBroker(results.getString("name"));
                String strategy = results.getString("strategy");
                tempBroker.updateStrategy(creator.create(strategy));
                tempBroker.setNumTrades(results.getInt("numTrades"));
                tempBroker.updateCoins(new ArrayList<String>(Arrays.asList(results.getString("coinList").split(",", 0))));
                tempBroker.setActive(Boolean.valueOf(results.getString("active")));
                list.addTrader(tempBroker);
            }
            System.out.println("Successfully retrieved trading broker data.");

            // Remove all records associated with user to prevent duplication when saving next time
            statement = connection.prepareStatement("DELETE FROM brokers WHERE user = ?");
            statement.setString(1, User.getInstance().getUsername());
            statement.executeUpdate();
            System.out.println("Deleted user's broker data from database.");
            
            return list;
        }
        catch(SQLException e)
        {
            System.out.println("An SQL error has occured while storing retrieving broker data:");
            System.out.println(e);
        }
        return list;
    }

    /**
     * Get the list of trade results stored in the database for the current user and
     * re-constructs a trade log.
     * @param traderList a TraderList with which brokers in the TradeLog will be matched to
     */
    @Override
    public TradeLog getTradeLog(TraderList traderList) {
        String get = "SELECT * from results WHERE user = ?";
        TradeLog tradeLog = new TradeLog();
        try {
            PreparedStatement statement = connection.prepareStatement(get);
            statement.setString(1, User.getInstance().getUsername());
            ResultSet results = statement.executeQuery();
            StrategyCreator creator = new StrategyCreator();
            ArrayList<TradeResult> tradeResults = new ArrayList<TradeResult>();
            while (results.next()) {
                // get the trader from the trader list with the specified name
                TradingBroker trader = traderList.getTrader(results.getString("name"));
                // create a new strategy object according to the strategy name of this result
                TradingStrategy strategy = creator.create(results.getString("strategy"));
                // create the trade result to be appended to the trade log
                TradeResult tradeResult = new TradeResult(
                    trader,
                    strategy,
                    results.getString("coinName"),
                    results.getString("action"),
                    results.getInt("quantity"),
                    results.getDouble("price"),
                    results.getString("date")
                );
                tradeResults.add(tradeResult);
            }
            tradeLog.addResults(tradeResults);
            System.out.println("Successfully retrieved trade log data.");
            
            // Remove all records associated with user to prevent duplication when saving next time
            statement = connection.prepareStatement("DELETE FROM results WHERE user = ?");
            statement.setString(1, User.getInstance().getUsername());
            statement.executeUpdate();
            System.out.println("Deleted user's trade results data from database.");

            return tradeLog;
        } catch (SQLException e) {
            System.out.println("An SQL error has occured while retrieving trade log data:");
            System.out.println(e);
        }
        return tradeLog;
    }

    // ----------------------------------------------------------------------------------
    // Database helper functions

    // add new row entry to database table creds with given username and hashed password
    public boolean addUser(String username, String password)
    {
        // count number of rows with matching username
        String check = "SELECT COUNT(*) as total FROM creds WHERE user = ?";
        try
        {
            PreparedStatement statement = connection.prepareStatement(check);
            statement.setString(1, username);
            // if count of matching user entries is not 0 (user exists)
            if(statement.executeQuery().getInt("total") != 0) 
            {
                System.out.println("This user already exists.");
                return false;
            }  

            // insert username, salted/hashed password, and salt into db
            String add = "INSERT INTO creds(user, pass, salt) VALUES(?, ?, ?)";
            statement = connection.prepareStatement(add);
            statement.setString(1, username);

            // generate salt
            SecureRandom random = new SecureRandom();
            byte[] sBytes = new byte[32];
            random.nextBytes(sBytes);
            String salt = Base64.getEncoder().encodeToString(sBytes);
            statement.setString(3, salt); // add salt to db

            // prepend salt to password
            String salted = salt + password;

            // hash salted password
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(salted.getBytes(StandardCharsets.UTF_8));
            String passEncoded = Base64.getEncoder().encodeToString(hash);
            statement.setString(2, passEncoded); // add salted and hashed password to db

            statement.executeUpdate();
            return true;
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Error hashing password:");
            System.out.println(e);
        }
        catch(SQLException e)
        {
            System.out.println("An SQL error has occured during user creation:");
            System.out.println(e);
        }
        return false;
    }

    // delete all entries in database table creds
    public void clearCreds()
    {
        String clear = "DELETE FROM creds";
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(clear);
        }
        catch(SQLException e)
        {
            System.out.println("An SQL error has occured while clearing the creds table:");
            System.out.println(e);
        }
    }

    // display table containing all username/hashed password pairs
    public void showCreds()
    {
        String selectAll = "SELECT * FROM creds";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectAll);
            while(result.next()) // print username, hashed password, and salt
            {
                String user = result.getString("user");
                String pass = result.getString("pass");
                String salt = result.getString("salt");
                System.out.println("User: " + user + " | Pass: " + pass + " | Salt: " + salt);
            }
            result.close();
        }
        catch(SQLException e)
        {
            System.out.println("An SQL error has occured during credential display:");
            System.out.println(e);
        }
    }

    // run test cases for database authentication
    public static void main(String args[])
    {
        // true
        System.out.println(Database.getInstance().authenticate("admin", "password"));
        // false
        System.out.println(Database.getInstance().authenticate("notadmin", "password"));
        // false
        System.out.println(Database.getInstance().authenticate("admin", "notpassword"));
        // false
        System.out.println(Database.getInstance().authenticate("notadmin", "notpassword"));
        // false
        System.out.println(Database.getInstance().authenticate("AND", "WHERE"));
        // true
        System.out.println(Database.getInstance().authenticate("oscar", "notpassword"));

    }
}
