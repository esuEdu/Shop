import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Shop class represents a shop with a store account, employees, and a bank.
 * It manages the payment of employees based on the balance of the store account.
 */
public class Shop {
    private final Account storeAccount;  // Store account to manage shop finances
    private final Employee[] employees;  // Array of employees
    private final Bank bank;             // Bank to facilitate transactions
    private int currentEmployeeIndex = 0; // To keep track of the last employee paid
    // Lock to synchronize access to shared resources
    private final Lock lock = new ReentrantLock();
    /**
     * Constructor for Shop class.
     *
     * @param storeAccount the store account
     * @param employees    array of employees
     * @param bank         the bank
     */
    public Shop(Account storeAccount, Employee[] employees, Bank bank) {
        this.storeAccount = storeAccount;
        this.employees = employees;
        this.bank = bank;

        // Add a property change listener to the bank
        this.bank.addPropertyChangeListener(evt -> {
            // Check if the property change is related to the store account
            if (evt.getPropertyName().equals(Integer.toString(storeAccount.getRoutingNumber()))) {
                // Check if the store account balance is sufficient to pay employees
                if (storeAccount.getBalance() >= 1400.0) {
                    payEmployees();
                }
            }
        });
    }

    /**
     * Retrieve the routing number of the store account.
     *
     * @return the routing number of the store account
     */
    public int getRoutingNumber() {
        return storeAccount.getRoutingNumber();
    }

    /**
     * Method to pay employees.
     * It transfers salary to employees from the store account.
     */
    public void payEmployees() {
        // Acquire the lock to ensure thread-safe transfer
        lock.lock();
        try {
            while (storeAccount.getBalance() >= employees[currentEmployeeIndex].getSalary()) {
                // Transfer salary to the employee
                bank.transfer(storeAccount, bank.findAccountByRoutingNumber(employees[currentEmployeeIndex].getRoutingNumber()), 1400.0);
                employees[currentEmployeeIndex].investSalary();
                // Move to the next employee
                currentEmployeeIndex = (currentEmployeeIndex + 1) % employees.length;
            }
        } finally {
            // Release the lock
            lock.unlock();
        }
    }
}
