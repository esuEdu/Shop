public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Account[] customerAccounts = new Account[5];
        for (int i = 0; i < 5; i++) {
            customerAccounts[i] = new Account(1000.0, i + 1); // routingNumber will be 1 to 5
            bank.addAccount(customerAccounts[i]);
        }

        Account[] storeAccounts = new Account[2];
        for (int i = 0; i < 2; i++) {
            storeAccounts[i] = new Account(0.0, i + 100); // routingNumber will be 100 and 101
            bank.addAccount(storeAccounts[i]);
        }

        Shop[] shops = new Shop[2];
        for (int i = 0; i < 2; i++) {
            Employee[] employees = new Employee[2];
            for (int j = 0; j < 2; j++) {
                employees[j] = new Employee(storeAccounts[i], new Account(0.0, 200 + j), bank); // routingNumber will be 200 and 201
                employees[j].start();
            }
            shops[i] = new Shop(storeAccounts[i], employees, bank);
        }

        Customer[] customers = new Customer[5];
        for (int i = 0; i < 5; i++) {
            customers[i] = new Customer(customerAccounts[i], shops, bank);
        }

        for (int i = 0; i < 5; i++) {
            customers[i].start();
        }
    }
}
