import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Bank class represents a bank that manages a collection of accounts.
 */
public class Bank {
    // Lock to synchronize access to shared resources
    private final Lock lock = new ReentrantLock();

    // Private list to store the accounts managed by the bank
    private final List<Account> accounts;

    /**
     * Constructor for Bank class.
     */
    public Bank() {
        // Initialize the list of accounts
        this.accounts = new ArrayList<>();
    }

    /**
     * Method to add an account to the bank.
     *
     * @param account the account to be added
     */
    public void addAccount(Account account) {
        // Add the account to the list
        accounts.add(account);
    }

    /**
     * Method to find an account by its routing number.
     *
     * @param routingNumber the routing number to search for
     * @return the account with the given routing number, or null if not found
     */
    public Account findAccountByRoutingNumber(int routingNumber) {
        // Search for the account with the given routing number
        return accounts.stream()
                .filter(account -> account.getRoutingNumber() == routingNumber)
                .findFirst()
                .orElse(null);
    }

    /**
     * Method to transfer money between two accounts.
     *
     * @param fromAccount the account to transfer money from
     * @param toAccount   the account to transfer money to
     * @param amount      the amount to transfer
     */
    public void transfer(Account fromAccount, Account toAccount, Double amount) {
        // Acquire the lock to ensure thread-safe transfer
        lock.lock();
        try {
            // Check if the fromAccount has sufficient balance
            if (fromAccount.getBalance() >= amount) {
                // Withdraw money from the fromAccount
                fromAccount.withdraw(amount);
                // Deposit money to the toAccount
                toAccount.deposit(amount);
                // Print transfer details
                System.out.println("__________________Transfer__________________");
                System.out.println("Transfer successful:");
                System.out.println("Amount transferred: " + amount);
                System.out.println("From Account: " + fromAccount.getRoutingNumber());
                System.out.println("To Account: " + toAccount.getRoutingNumber());
                System.out.println("From Account Balance after transfer: " + fromAccount.getBalance());
                System.out.println("To Account Balance after transfer: " + toAccount.getBalance());
                System.out.println("__________________End__________________");
            } else {
                System.out.println("Insufficient balance in the fromAccount.");
            }
        } finally {
            // Release the lock
            lock.unlock();
        }
    }
}
