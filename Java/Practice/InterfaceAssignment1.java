package activity;

interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
}
class CheckingAccount implements BankAccount {
    private double balance;

	public CheckingAccount(double initialBalance) {
		 balance = initialBalance;
	}

	@Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawl $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class InterfaceAssignment1 {
    public static void main(String[] args) {
        
        CheckingAccount account = new CheckingAccount(1000.0);

        account.deposit(500.0);

        account.withdraw(200.0);

        System.out.println("Current Balance: $" + account.getBalance());
    }
}
