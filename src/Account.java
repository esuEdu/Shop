/**
 * The Account class represents a simple bank account with methods to get balance,
 * withdraw funds, deposit funds, and set balance.
 */
public class Account {

    private final String username;

    // Private variable to store the routing number of the account
    private final int routingNumber;

    // Private variable to store the balance of the account
    private Double balance;

    /**
     * Constructor for Account class with initial balance and routing number.
     *
     * @param balance       initial balance of the account
     * @param routingNumber the routing number of the account
     */
    public Account(Double balance, int routingNumber, String username) {
        this.username = username;
        this.balance = balance;
        this.routingNumber = routingNumber;
    }

    /**
     * Retrieve the routing number of the account.
     *
     * @return the routing number
     */
    public int getRoutingNumber() {
        return routingNumber;
    }

    /**
     * Retrieve the username of the account.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
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
        // Deduct the amount from the balance
        this.balance -= amount;
    }

    /**
     * Method to deposit funds into the account.
     *
     * @param amount amount to be deposited
     */
    public void deposit(Double amount) {
        // Add the amount to the balance
        this.balance += amount;
    }

    /**
     * Override the toString method to provide a string representation of the Account object.
     *
     * @return a string representation of the Account object
     */
    @Override
    public String toString() {
        return "Username: " + username
                + ", balance = $" + balance
                + ", routingNumber=" + routingNumber
                ;
    }
}
