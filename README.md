# Bank Management System

This project simulates a simple bank management system where customers can transfer money to shops and employees, shops can pay employees, and employees can invest a portion of their salary.

## Table of Contents

- [Bank Class](#bank-class)
- [Account Class](#account-class)
- [Employee Class](#employee-class)
- [Shop Class](#shop-class)
- [Customer Class](#customer-class)
- [Main Class](#main-class)

---

### Bank Class

The `Bank` class represents a bank that manages a collection of accounts. It provides methods to add accounts, retrieve all accounts, find an account by its routing number, and transfer money between accounts.

**Features:**

- Thread-safe transfer of money between accounts using locks.
- Property change support to notify listeners about transfers.

---

### Account Class

The `Account` class represents a simple bank account with methods to get balance, withdraw funds, deposit funds, and set balance.

**Attributes:**

- Routing Number
- Balance

**Methods:**

- `getBalance()`: Retrieve the current balance.
- `withdraw(amount)`: Withdraw funds from the account.
- `deposit(amount)`: Deposit funds into the account.
- `toString()`: Override to provide a string representation of the account.

---

### Employee Class

The `Employee` class represents an employee with a salary account, an investment account, and a bank to facilitate transactions. It extends the Thread class but doesn't currently override the run method.

**Attributes:**

- Salary Account
- Investment Account
- Bank

**Methods:**

- `getSalary()`: Retrieve the fixed salary amount.
- `investSalary()`: Invest a portion of the salary.

---

### Shop Class

The `Shop` class represents a shop with a store account, employees, and a bank. It manages the payment of employees based on the balance of the store account.

**Attributes:**

- Store Account
- Employees
- Bank

**Methods:**

- `payEmployees()`: Transfer salary to employees and invest a portion of their salary.

---

### Customer Class

The `Customer` class represents a customer with an account. The customer can make purchases from multiple shops using the bank.

**Attributes:**

- Account
- Shops
- Bank

**Methods:**

- `run()`: Behavior of the customer thread.

---

### Main Class

The `Main` class is the entry point of the application. It initializes accounts, shops, employees, and customers. It also manages the flow of the application by starting threads and waiting for them to finish.

**Flow:**

1. Initialize accounts for customers, stores, employees, and investments.
2. Create shops with employees.
3. Start customer threads to make transfers.
4. Wait for all customer threads to finish.
5. Print account details before and after transfers.

---

## Running the Application

To run the application:

1. Clone the repository.
2. Navigate to the project directory.
3. Compile and run the `Main` class.

```bash
javac Main.java
java Main
```

---

Feel free to raise issues if you find any! 😄

---
