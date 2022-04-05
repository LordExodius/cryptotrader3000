package cryptotrader.authentication;

/**
 * An interface for a database class that implements authentication.
 * 
 * @author Oscar Yu, David Tran
 * @version 1.0
 */
public interface DatabaseAuthenticate {
    /**
     * Authenticate a user with a given username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return Returns true if the user is authorized, otherwise returns false.
     */
    public boolean authenticate(String username, String password);
}
