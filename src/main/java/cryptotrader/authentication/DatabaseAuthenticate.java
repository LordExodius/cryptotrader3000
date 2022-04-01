package cryptotrader.authentication;

public interface DatabaseAuthenticate {
    public boolean authenticate(String username, String password);
}
