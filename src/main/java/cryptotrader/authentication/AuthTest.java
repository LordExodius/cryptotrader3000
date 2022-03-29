package cryptotrader.authentication;

public class AuthTest {
    public static void main(String args[])
    {
        // Database tests

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

        // LoginServer tests

        // true
        System.out.println(LoginServer.getInstance().authenticate("admin", "password"));
        // false
        System.out.println(LoginServer.getInstance().authenticate("notadmin", "password")); 
        
    }

}
