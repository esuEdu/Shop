/**
 * The Employee class represents an employee with a salary account,
 * an investment account, and a bank to facilitate transactions.
 * It extends the Thread class but doesn't currently override the run method.
 */
public class Employee extends Thread {
    private final Account salaryAccount;      // Salary account of the employee
    private final Account investmentAccount;  // Investment account to invest salary
    private final Bank bank;                 // Bank to facilitate transactions

    /**
     * Constructor for Employee class.
     *
     * @param salaryAccount      the salary account
     * @param investmentAccount  the investment account
     * @param bank               the bank
     */
    public Employee(Account salaryAccount, Account investmentAccount, Bank bank) {
        this.salaryAccount = salaryAccount;
        this.investmentAccount = investmentAccount;
        this.bank = bank;
    }

    /**
     * Overridden run method of the Thread class.
     * This method can be extended to define the behavior of the employee thread.
     */
    @Override
    public void run() {
        // The behavior of the employee can be implemented here.
    }

    /**
     * Retrieve the routing number of the salary account.
     *
     * @return the routing number of the salary account
     */
    public int getRoutingNumber() {
        return salaryAccount.getRoutingNumber();
    }

    /**
     * Retrieve the salary amount.
     *
     * @return the salary amount
     */
    public Double getSalary() {
        return 1400.0;  // Fixed salary amount
    }

    /**
     * Method to invest a portion of the salary.
     * Transfers 20% of the salary from the salary account to the investment account.
     */
    public void investSalary() {
        // Transfer 20% of the salary to the investment account
        bank.transfer(salaryAccount, investmentAccount, getSalary() * 0.20);
    }
}
