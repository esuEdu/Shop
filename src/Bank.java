import java.util.ArrayList;
import java.util.List;

/**
 * The Bank class represents a bank that manages a collection of accounts.
 */
public class Bank {

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

    /**
     * Method to transfer funds between two accounts in the bank.
     *
     * @param fromAccount the account to transfer funds from
     * @param toAccount the account to transfer funds to
     * @param amount the amount to transfer
     * @return true if the transfer was successful, false otherwise
     */
    public boolean transferFunds(Account fromAccount, Account toAccount, Double amount) {
        if (fromAccount.getBalance() >= amount) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }
}

