/**
 * The Account class represents a simple bank account with methods to get balance,
 * withdraw funds, deposit funds, and set balance.
 */
public class Account {

    // Private variable to store the routing number of the account
    private final int routingNumber;

    // Private variable to store the balance of the account
    private Double balance;

    /**
     * Constructor for Account class with initial balance and routing number.
     *
     * @param balance initial balance of the account
     * @param routingNumber the routing number of the account
     */
    public Account(Double balance, int routingNumber) {
        this.balance = balance;
        this.routingNumber = routingNumber;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    /**
     * Method to get the current balance of the account.
     *
     * @return the balance of the account
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Method to withdraw funds from the account.
     *
     * @param amount amount to be withdrawn
     */
    public void withdraw(Double amount) {
        this.balance -= amount;
    }

    /**
     * Method to deposit funds into the account.
     *
     * @param amount amount to be deposited
     */
    public void deposit(Double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Account{"
                + "balance=" + balance
                + ", routingNumber=" + routingNumber
                + '}';
    }
}
