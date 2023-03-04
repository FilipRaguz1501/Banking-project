package service;
import repository.AccountRepository;

import java.math.BigDecimal;


import pojo.*;




public class CheckingService implements AccountService {
    AccountRepository accountRepository;


    public CheckingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    public void createAccount(Account account) {
        this.accountRepository.createAccount(account);
   }

   public Checking retrieveAccount(String id) {
        return (Checking)this.accountRepository.retrieveAccount(id);
   }

   public void updateAccount(Account account) {
        this.accountRepository.updateAccount(account);
   }

   public void deleteAccount(String id) {
        this.accountRepository.deleteAccount(id);
   }

   @Override
   public void deposit(String id, BigDecimal amount) {
       Account account = (Checking)accountRepository.retrieveAccount(id);
       if(account instanceof Checking) {
        Checking checking = (Checking)account;
        checking.setBalance(checking.getBalance().add(amount));
        accountRepository.updateAccount(checking);
       } else {
            throw new IllegalArgumentException("Account with ID " + id + " is not a Checking account");
       }
       
       
   }

   @Override
   public void withdraw(String id, BigDecimal amount) {
       Account account = (Checking)accountRepository.retrieveAccount(id);
       if(account instanceof Checking) {
            Checking checking = (Checking)account;
            BigDecimal newBalance = checking.getBalance().subtract(amount);
            if(newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            checking.setBalance(newBalance);
            accountRepository.updateAccount(checking);
       } 
   }


  
}
