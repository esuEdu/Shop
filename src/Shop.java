import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Shop {
    private final Account storeAccount;
    private final Employee[] employees;
    private final Bank bank;
    private int currentEmployeeIndex = 0; // To keep track of the last employee paid

    public Shop(Account storeAccount, Employee[] employees, Bank bank) {
        this.storeAccount = storeAccount;
        this.employees = employees;
        this.bank = bank;
        this.storeAccount.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println(Integer.toString(storeAccount.getRoutingNumber()));
                if (evt.getPropertyName().equals(Integer.toString(storeAccount.getRoutingNumber()))) {
                    if (storeAccount.getBalance() >= 1400.0 ) {
                        payEmployees();
                    }
                }
            }
        });
    }

    public int getRoutingNumber() {
        return storeAccount.getRoutingNumber();
    }

    public void payEmployees() {
        while (storeAccount.getBalance() >= employees[currentEmployeeIndex].getSalary()) {
            bank.transfer(storeAccount, bank.findAccountByRoutingNumber(employees[currentEmployeeIndex].getRoutingNumber()), 1400.0);
            employees[currentEmployeeIndex].investSalary();
            // Move to the next employee
            currentEmployeeIndex = (currentEmployeeIndex + 1) % employees.length;
        }
    }
}

