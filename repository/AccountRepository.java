package repository;
import java.util.HashMap;
import java.util.Map;
import pojo.Account;

public class AccountRepository {

   private Map<String, Account> database = new HashMap<>();

   public void createAccount(Account account) {
        this.database.put(account.getId(), account.clone());
   }

   public Account retrieveAccount(String id) {
        return this.database.get(id).clone();
   }

   public void updateAccount(Account account) {
        this.database.put(account.getId(), account.clone());
   }

   public void deleteAccount(String id) {
        this.database.remove(id);
   }

   


     
   
    
}
