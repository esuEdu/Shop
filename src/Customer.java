/**
 * The Customer class represents a customer with an account.
 * The customer can make purchases from multiple shops using the bank.
 * It extends the Thread class but doesn't currently override the run method.
 */
public class Customer extends Thread {
    private final Account account;  // Customer's account
    private final Shop[] shops;     // Array of shops to purchase from
    private final Bank bank;        // Bank to facilitate transactions

    /**
     * Constructor for Customer class.
     *
     * @param account the customer's account
     * @param shops   array of shops to purchase from
     * @param bank    the bank
     */
    public Customer(Account account, Shop[] shops, Bank bank) {
        this.account = account;
        this.shops = shops;
        this.bank = bank;
    }

    /**
     * Overridden run method of the Thread class.
     * This method represents the behavior of the customer thread.
     */
    @Override
    public void run() {
        // Continue shopping as long as the account balance is sufficient
        while (account.getBalance() >= 100.0) {
            for (Shop shop : shops) {
                // Transfer 100.0 to the shop if the account balance is sufficient
                if (account.getBalance() >= 100.0) {
                    bank.transfer(account, bank.findAccountByRoutingNumber(shop.getRoutingNumber()), 100.0);
                }

                // Transfer 200.0 to the shop if the account balance is sufficient
                if (account.getBalance() >= 200.0) {
                    bank.transfer(account, bank.findAccountByRoutingNumber(shop.getRoutingNumber()), 200.0);
                }
            }
        }
    }
}
