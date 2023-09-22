package activity;


abstract class BankAccount {
    protected String accountNumber;
    protected double balance;

    // Constructor
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Abstract methods
    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    // Getter for balance
    public double getBalance() {
        return balance;
    }
}


class CheckingAccount extends BankAccount {
   
    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // Implementation of deposit method
   
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " into account " + accountNumber);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Implementation of withdraw method
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount + " from account " + accountNumber);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

public class AbstractClass2 {
    public static void main(String[] args) {
        
        CheckingAccount myAccount = new CheckingAccount("123456", 1000.0);

        // Deposit and withdraw
        myAccount.deposit(500.0);
        myAccount.withdraw(200.0);
        myAccount.withdraw(1500.0);  

        // Check the final balance
        System.out.println("Final Balance: $" + myAccount.getBalance());
    }
}
