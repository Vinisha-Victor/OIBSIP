import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM");

        // Create ATM
        ATMSystem atmSystem = new ATMSystem();

        // Prompt for user ID and PIN
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        // Authenticate user
        User user = new User(userId, pin);
        if (atmSystem.authenticateUser(user)) {
            System.out.println("Authentication successful.");
            atmSystem.showMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }
}

class ATMSystem {
    private Account account;
    private Scanner scanner = new Scanner(System.in);

    public ATMSystem() {
        this.account = new Account("user123", "1234", 1000);
    }

    public boolean authenticateUser(User user) {
        return account.authenticate(user);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    account.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    String recipientId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    account.transfer(recipientId, transferAmount);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class User {
    private String userId;
    private String pin;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }
}

class Account {
    private String userId;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(User user) {
        return userId.equals(user.getUserId()) && pin.equals(user.getPin());
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: " + amount);
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: " + amount);
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(String recipientId, double amount) {
        // Simulate transfer process
        // In a real-world scenario, this would involve updating balances of both
        // accounts
        transactionHistory.add("Transfer to " + recipientId + ": " + amount);
        System.out.println("Transfer successful.");
    }
}