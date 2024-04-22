public class Shop {
    private final Account storeAccount;
    private final Employee[] employees;
    private final Bank bank;
    private int currentEmployeeIndex = 0; // To keep track of the last employee paid

    public Shop(Account storeAccount, Employee[] employees, Bank bank) {
        this.storeAccount = storeAccount;
        this.employees = employees;
        this.bank = bank;
    }

    public int getRoutingNumber() {
        return storeAccount.getRoutingNumber();
    }

    public void payEmployees() {
        while (storeAccount.getBalance() >= employees[currentEmployeeIndex].getSalary()) {
            bank.transfer(storeAccount, bank.findAccountByRoutingNumber(employees[currentEmployeeIndex].getRoutingNumber()), employees[currentEmployeeIndex].getSalary());
            // Move to the next employee
            currentEmployeeIndex = (currentEmployeeIndex + 1) % employees.length;
        }
    }
}

