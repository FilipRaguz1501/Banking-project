package service;
import repository.AccountRepository;
import pojo.*;
import java.math.BigDecimal;

public class CreditService implements AccountService {
    AccountRepository accountRepository;


    public CreditService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        this.accountRepository.createAccount(account);
   }

   public Credit retrieveAccount(String id) {
        return (Credit)this.accountRepository.retrieveAccount(id);
   }

   public void updateAccount(Account account) {
        this.accountRepository.updateAccount(account);
   }

   public void deleteAccount(String id) {
        this.accountRepository.deleteAccount(id);
   }

   @Override
    public void deposit(String id, BigDecimal amount) {
        Account account = accountRepository.retrieveAccount(id);
        if (account instanceof Credit) {
            Credit creditAccount = (Credit) account;
            creditAccount.setCredit(creditAccount.getCredit().add(amount));
            accountRepository.updateAccount(creditAccount);
    }
}

    @Override
    public void withdraw(String id, BigDecimal amount) {
        Account account = accountRepository.retrieveAccount(id);
        if (account instanceof Credit) {
            Credit creditAccount = (Credit) account;
            creditAccount.setCredit(creditAccount.getCredit().subtract(amount));
            accountRepository.updateAccount(creditAccount);
    }
}

}



