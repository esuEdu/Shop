import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * The Bank class represents a bank that manages a collection of accounts.
 */
public class Bank {
    private final Lock lock = new ReentrantLock();

    // Private list to store the accounts managed by the bank
    private final List<Account> accounts;

    /**
     * Constructor for Bank class.
     */
    public Bank() {
        this.accounts = new ArrayList<>();
    }

    /**
     * Method to add an account to the bank.
     *
     * @param account the account to be added
     */
    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Method to remove an account from the bank.
     *
     * @param account the account to be removed
     */
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    /**
     * Method to get the total balance of all accounts in the bank.
     *
     * @return the total balance of all accounts
     */
    public Double getTotalBalance() {
        return accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    /**
     * Method to find an account by its routing number.
     *
     * @param routingNumber the routing number to search for
     * @return the account with the given routing number, or null if not found
     */
    public Account findAccountByRoutingNumber(int routingNumber) {
        return accounts.stream()
                .filter(account -> account.getRoutingNumber() == routingNumber)
                .findFirst()
                .orElse(null);
    }

    public void transfer(Account fromAccount, Account toAccount, Double amount) {
        lock.lock();
        try {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        } finally {
            lock.unlock();
        }
    }
}

