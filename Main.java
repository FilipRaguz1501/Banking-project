import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import constants.TransactionType.TRANSACTION;
import constants.AccountType.TYPE;
import pojo.Checking;
import pojo.Credit;
import repository.AccountRepository;
import service.AccountService;
import service.CheckingService;
import service.CreditService;

public class Main {

    static Path[] paths = new Path[] {Paths.get("data/accounts.txt"), Paths.get("data/transactions.txt")};
    static AccountRepository repository = new AccountRepository();
    static CheckingService checkingService = new CheckingService(repository);
    static CreditService creditService = new CreditService(repository);

    public static void main(String[] args) {
        
        try {
            loadAccounts();
            applyTransactions();
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }

        
    }

    
    

    public static void loadAccounts() throws IOException {
        Files.lines(paths[0])
            .forEach(line -> {
                String[] words = line.split(" ");
                if(words[0].equals(TYPE.CHECKING.toString())) {
                    checkingService.createAccount(new Checking(words[1], new BigDecimal(words[2])));
                } else {
                    creditService.createAccount(new Credit(words[1], new BigDecimal(words[2])));
                }
            });
    }

    public static void applyTransactions() throws IOException {
        Files.lines(paths[1])
        .forEach(line -> {
            String[] words = line.split(" ");
            boolean isChecking = words[0].equals(TYPE.CHECKING.toString());
            AccountService accountService = isChecking ? checkingService : creditService;
            TRANSACTION transactionType = TRANSACTION.valueOf(words[2]);
            if(transactionType.equals(TRANSACTION.DEPOSIT)) {
                accountService.deposit(words[1], new BigDecimal(words[3]));
            } else {
                accountService.withdraw(words[1], new BigDecimal(words[3]));
            }
        });
    }
    
}