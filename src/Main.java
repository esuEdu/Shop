import java.util.List;

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

        Account[] employeeAccounts = new Account[4];
        for (int i = 0; i < 4; i++) {
            employeeAccounts[i] = new Account(0.0, i + 200);
            bank.addAccount(employeeAccounts[i]);
        }

        Account[] investmentAccounts = new Account[4];
        for (int i = 0; i < 4; i++) {
            investmentAccounts[i] = new Account(0.0, i + 300);
            bank.addAccount(investmentAccounts[i]);
        }

        Shop[] shops = new Shop[2];
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                Employee[] employees = new Employee[2];
                for ( int j = 0; j < 2; j++) {
                    employees[j] = new Employee(employeeAccounts[j], investmentAccounts[j], bank);
                    employees[j].start();
                }
                shops[i] = new Shop(storeAccounts[i], employees, bank);
            }else {
                Employee[] employees = new Employee[2];
                for ( int j = 0; j < 2; j++) {
                   if (j == 0 ) {
                       employees[j] = new Employee(employeeAccounts[2], investmentAccounts[2], bank);
                   }else {
                       employees[j] = new Employee(employeeAccounts[3], investmentAccounts[3], bank);
                   }
                }
                shops[i] = new Shop(storeAccounts[i], employees, bank);
            }
        }

        Customer[] customers = new Customer[5];
        for (int i = 0; i < 5; i++) {
            customers[i] = new Customer(customerAccounts[i], shops, bank);
            customers[i].start();
        }

// Wait for all customers to finish
        for (Customer customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("-----------All accounts created:----------");
        // Retrieve all accounts from the bank and print their details
        List<Account> allAccounts = bank.getAllAccounts();
        for (Account account : allAccounts) {
            System.out.println(account);
        }
        System.out.println("-----------------------------------------");
    }
}
