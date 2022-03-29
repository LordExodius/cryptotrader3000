package cryptotrader.authentication;
import java.sql.*;

public class LoginServer {
    // Select database file for user info
    Connection connection;

    public LoginServer()
    {
        try{
            // change path to working one
            connection = DriverManager.getConnection("jdbc:sqlite:/db/auth.db");
        }
            
        catch(SQLException e) {
            System.out.println("An SQLException has occurred. Error message:");
            System.out.println(e);
        }
    }

    public static void main(String args[])
    {
        LoginServer test = new LoginServer();
    }
    
}
