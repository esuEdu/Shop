import java.util.List;
import java.util.logging.Logger;

/**
 * The Main class is the entry point of the application.
 * It initializes accounts, shops, employees, and customers.
 * It also manages the flow of the application by starting threads and waiting for them to finish.
 */
public class Main {

    // Create a Logger instance
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Create a new bank
        Bank bank = new Bank();

        // Initialize different types of accounts
        initializeCustomerAccounts(bank);
        initializeStoreAccounts(bank);
        initializeEmployeeAccounts(bank);
        initializeInvestmentAccounts(bank);

        // Initialize shops
        Shop[] shops = initializeShops(bank);

        // Print account details before transfers
        System.out.println("_________________Initial Account Details________________");
        printAccountDetails(bank);

        // Initialize and start customers
        System.out.println("__________________Transfers__________________");
        Customer[] customers = new Customer[5];
        startCustomers(shops, bank, customers);

        // Wait for all customers to finish
        waitForCustomersToFinish(customers);

        // Print account details after transfers
        System.out.println("_________________Updated Account Details________________");
        printAccountDetails(bank);

        System.out.println("______________________________________________");
    }

    private static void initializeCustomerAccounts(Bank bank) {
        Account[] customerAccounts = new Account[5];
        for (int i = 0; i < 5; i++) {
            customerAccounts[i] = new Account(1000.0, i + 1); // routingNumber will be 1 to 5
            bank.addAccount(customerAccounts[i]);
        }
    }

    private static void initializeStoreAccounts(Bank bank) {
        Account[] storeAccounts = new Account[2];
        for (int i = 0; i < 2; i++) {
            storeAccounts[i] = new Account(0.0, i + 100); // routingNumber will be 100 and 101
            bank.addAccount(storeAccounts[i]);
        }
    }

    private static void initializeEmployeeAccounts(Bank bank) {
        Account[] employeeAccounts = new Account[4];
        for (int i = 0; i < 4; i++) {
            employeeAccounts[i] = new Account(0.0, i + 200);
            bank.addAccount(employeeAccounts[i]);
        }
    }

    private static void initializeInvestmentAccounts(Bank bank) {
        Account[] investmentAccounts = new Account[4];
        for (int i = 0; i < 4; i++) {
            investmentAccounts[i] = new Account(0.0, i + 300);
            bank.addAccount(investmentAccounts[i]);
        }
    }

    private static Shop[] initializeShops(Bank bank) {
        Shop[] shops = new Shop[2];
        for (int i = 0; i < 2; i++) {
            Employee[] employees = new Employee[2];
            for (int j = 0; j < 2; j++) {
                employees[j] = createEmployee(i, j, bank);
                employees[j].start(); // Start the employee threads
            }
            shops[i] = new Shop(bank.findAccountByRoutingNumber(i + 100), employees, bank);
        }
        return shops;
    }

    private static Employee createEmployee(int shopIndex, int employeeIndex, Bank bank) {
        if (shopIndex == 0) {
            return new Employee(bank.findAccountByRoutingNumber(employeeIndex + 200),
                    bank.findAccountByRoutingNumber(employeeIndex + 300), bank);
        } else {
            return new Employee(bank.findAccountByRoutingNumber(shopIndex + 201),
                    bank.findAccountByRoutingNumber(shopIndex + 301), bank);
        }
    }

    private static void printAccountDetails(Bank bank) {
        List<Account> allAccounts = bank.getAllAccounts();
        for (Account account : allAccounts) {
            System.out.println(account);
        }
    }

    private static void startCustomers(Shop[] shops, Bank bank, Customer[] customers) {
        for (int i = 0; i < 5; i++) {
            customers[i] = new Customer(bank.findAccountByRoutingNumber(i + 1), shops, bank);
            customers[i].start();
        }
    }

    private static void waitForCustomersToFinish(Customer[] customers) {
        for (Customer customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                logger.severe("Thread was interrupted while waiting for customer to finish: " + e.getMessage());
                logger.severe("StackTrace: " + e);
            }
        }
    }
}
