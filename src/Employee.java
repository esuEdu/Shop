public class Employee extends Thread {
    private final Account salaryAccount;
    private final Account investmentAccount;
    private final Bank bank;

    public Employee(Account salaryAccount, Account investmentAccount, Bank bank) {
        this.salaryAccount = salaryAccount;
        this.investmentAccount = investmentAccount;
        this.bank = bank;
    }

    public int getRoutingNumber() {
        return salaryAccount.getRoutingNumber();
    }

    public Double getSalary() {
        return 1400.0;
    }

    public void investSalary(Double amount) {
        bank.transfer(salaryAccount, investmentAccount, amount);
    }
}
