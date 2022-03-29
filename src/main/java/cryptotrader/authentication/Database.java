package cryptotrader.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;

public class Database implements DatabaseAuthenticate {
    // NOTE: Implement GetFromDatabase when TraderList and TradeLog are aded

    /**
     * Databse contains 3 tables:
     * - creds(user text, pass text)
     * - traders(user text, )
     * - tradelogs()
     */

    private static Database instance = new Database();
    private Connection connection;

    // Constructor for Database class
    private Database()
    {
        try 
        {
            // create connection to database file
            connection = DriverManager.getConnection("jdbc:sqlite:./db/auth.db");
        }
            
        // handle SQL exception when creating connection to database file
        catch(SQLException e) 
        {
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
        // Count rows with matching user/hashed password combination
        String sql = "SELECT COUNT(*) as total FROM creds WHERE user = ? AND pass = ?";
        try 
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            // hash password
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String passEncoded = Base64.getEncoder().encodeToString(hash);
            statement.setString(2, passEncoded);

            ResultSet result = statement.executeQuery();
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

    public static Database getInstance()
    {
        if(instance == null)
            instance = new Database();
        return instance;
    }

    // ---------------------------------------------------------------------------
    // Database helper functions

    // add new row entry to database table creds with given username and hashed password
    public boolean addUser(String username, String password)
    {
        String check = "SELECT COUNT(*) as total FROM creds WHERE user = ?";
        try
        {
            PreparedStatement statement = connection.prepareStatement(check);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            int matches = result.getInt("total");
            result.close();

            if(matches == 0)
            {
                String add = "INSERT INTO creds(user, pass) VALUES(?, ?)";
                statement = connection.prepareStatement(add);
                statement.setString(1, username);

                // hash password
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                String passEncoded = Base64.getEncoder().encodeToString(hash);
                statement.setString(2, passEncoded);

                statement.executeUpdate();
                return true;
            }
            else
            {
                System.out.println("User already exists. Not added.");
            }
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
            System.out.println("An SQL error has occured during authentication:");
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
            while(result.next())
            {
                String user = result.getString("user");
                String pass = result.getString("pass");
                System.out.println("User: " + user + " | Pass: " + pass);
            }
        }
        catch(SQLException e)
        {
            System.out.println("An SQL error has occured during authentication:");
            System.out.println(e);
        }
    }

    public static void main(String args[])
    {
        //Database.getInstance().addUser("admin", "password");
        // Database.getInstance().clearCreds();
        Database.getInstance().showCreds();
    }
}
