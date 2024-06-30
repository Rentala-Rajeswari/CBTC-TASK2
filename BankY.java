import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class BankY {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Bank bank = new Bank();

		while (true) {
			System.out.println("\nBankY - Choose an option:");
			System.out.println("1. Create Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer");
			System.out.println("5. Check Balance");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				System.out.print("Enter account number: ");
				String accNum = scanner.nextLine();
				System.out.print("Enter initial balance: ");
				double initialBalance = scanner.nextDouble();
				bank.createAccount(accNum, initialBalance);
				break;
			case 2:
				System.out.print("Enter account number: ");
				String depositAccNum = scanner.nextLine();
				System.out.print("Enter deposit amount: ");
				double depositAmount = scanner.nextDouble();
				bank.deposit(depositAccNum, depositAmount);
				break;
			case 3:
				System.out.print("Enter account number: ");
				String withdrawAccNum = scanner.nextLine();
				System.out.print("Enter withdrawal amount: ");
				double withdrawAmount = scanner.nextDouble();
				bank.withdraw(withdrawAccNum, withdrawAmount);
				break;
			case 4:
				System.out.print("Enter from account number: ");
				String fromAccNum = scanner.nextLine();
				System.out.print("Enter to account number: ");
				String toAccNum = scanner.nextLine();
				System.out.print("Enter transfer amount: ");
				double transferAmount = scanner.nextDouble();
				bank.transfer(fromAccNum, toAccNum, transferAmount);
				break;
			case 5:
			    System.out.print("Enter account number: ");
				String AccNum = scanner.nextLine();
				bank.checkBalance(AccNum);
				break;
			case 6:
				System.out.println("Exiting BankY. Goodbye!");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}


class Bank {
	private Map<String, Double> accounts;

	public Bank() {
		this.accounts = new HashMap<>();
	}

	public void createAccount(String accountNumber, double initialBalance) {
		if (!accounts.containsKey(accountNumber)) {
			if(initialBalance>=500){
				accounts.put(accountNumber, initialBalance);
				System.out.println("Account created successfully.");
			}else{
				System.out.println("Initial Balance should be Greather than or equal to 500");
			}
			
		} else {
			System.out.println("Account number already exists.");
		}
	}

	public void deposit(String accountNumber, double amount) {
		if (accounts.containsKey(accountNumber)) {
			double currentBalance = accounts.get(accountNumber);
			accounts.put(accountNumber, currentBalance + amount);
			System.out.println("Deposit successful. New balance: " + accounts.get(accountNumber));
		} else {
			System.out.println("Account number does not exist.");
		}
	}

	public void withdraw(String accountNumber, double amount) {
		if (accounts.containsKey(accountNumber)) {
			double currentBalance = accounts.get(accountNumber);
			if (currentBalance >= amount+500) {
				accounts.put(accountNumber, currentBalance - amount);
				System.out.println("Withdrawal successful. New balance: " + accounts.get(accountNumber));
			} else {
				System.out.println("Insufficient funds.");
			}
		} else {
			System.out.println("Account number does not exist.");
		}
	}

	public void transfer(String fromAccount, String toAccount, double amount) {
		if (accounts.containsKey(fromAccount) && accounts.containsKey(toAccount)) {
			double fromBalance = accounts.get(fromAccount);
			if (fromBalance >= amount+500) {
				double toBalance = accounts.get(toAccount);
				accounts.put(fromAccount, fromBalance - amount);
				accounts.put(toAccount, toBalance + amount);
				System.out.println("Transfer successful.");
				System.out.println("From Account " + fromAccount + " Balance: " + accounts.get(fromAccount));
				System.out.println("To Account " + toAccount + " Balance: " + accounts.get(toAccount));
			} else {
				System.out.println("Insufficient funds.");
			}
		} else {
			System.out.println("One or both account numbers do not exist.");
		}
	}
	public void checkBalance(String accNum){
	    if (accounts.containsKey(accNum)) {
			double currentBalance = accounts.get(accNum);
			System.out.println("Current Balance: "+currentBalance);
		} else {
			System.out.println("Account number does not exist.");
		}
	}
}