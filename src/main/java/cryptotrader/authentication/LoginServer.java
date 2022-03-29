package cryptotrader.authentication;

public class LoginServer implements LoginAuthenticate{
    private static LoginServer instance = new LoginServer();
    private Database database;

    private LoginServer() 
    {
        database = Database.getInstance();
    }
    public static void main(String args[]) 
    {
        LoginServer test = new LoginServer();
    }

    @Override
    public boolean authenticate(String username, String password) 
    {
        return database.authenticate(username, password);
    }
    
    public static LoginServer getInstance() 
    {
        if(instance == null)
            instance = new LoginServer();
        return instance;
    }
    
}
