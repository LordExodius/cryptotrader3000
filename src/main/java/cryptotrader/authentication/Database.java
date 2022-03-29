package cryptotrader.authentication;

import java.sql.*;

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
        String sql = "SELECT COUNT(*) as total FROM creds WHERE user = ? AND pass = ?";
        try 
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            int matches = result.getInt("total");
            result.close();

            if(matches > 0)
                return true;
        }
        catch(SQLException e) 
        {
            System.out.println("An SQL error has occured during authentication:");
            System.out.println(e);
        }
        return false;
    }
    
    public static void main(String args[])
    {
        System.out.println(getInstance().authenticate("admin", "password"));
        System.out.println(getInstance().authenticate("admin", "notpassword"));
        System.out.println(getInstance().authenticate("notadmin", "password"));
    }

    public static Database getInstance()
    {
        if(instance == null)
            instance = new Database();
        return instance;
    }
}
