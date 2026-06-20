import java.util.Scanner;

public class ATM {
    private static final int PIN = 1234; // Example PIN
    private static double balance = 1000.00; // Initial balance

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Welcome to the ATM =====");

        // PIN authentication
        if (!authenticate(sc)) {
            System.out.println("Too many incorrect attempts. Card blocked.");
            sc.close();
            return;
        }

        // Main menu loop
        int choice;
        do {
            displayMenu();
            choice = getValidInt(sc, "Choose an option: ");

            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> deposit(sc);
                case 3 -> withdraw(sc);
                case 4 -> System.out.println("Thank you for using the ATM. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    // Authenticate user with PIN
    private static boolean authenticate(Scanner sc) {
        int attempts = 0;
        while (attempts < 3) {
            int enteredPin = getValidInt(sc, "Enter your 4-digit PIN: ");
            if (enteredPin == PIN) {
                return true;
            }
            attempts++;
            System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
        }
        return false;
    }

    // Display ATM menu
    private static void displayMenu() {
        System.out.println("\n===== ATM Menu =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    // Check balance
    private static void checkBalance() {
        System.out.printf("Your current balance is: ₹12%.2f%n", balance);
    }

    // Deposit money
    private static void deposit(Scanner sc) {
        double amount = getValidDouble(sc, "Enter amount to deposit: ₹");
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("₹%.2f deposited successfully. New balance: ₹%.2f%n", amount, balance);
    }

    // Withdraw money
    private static void withdraw(Scanner sc) {
        double amount = getValidDouble(sc, "Enter amount to withdraw: ₹");
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        System.out.printf("₹%.2f withdrawn successfully. New balance: ₹%.2f%n", amount, balance);
    }

    // Get valid integer input
    private static int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // clear invalid input
            }
        }
    }

    // Get valid double input
    private static double getValidDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a valid amount.");
                sc.next(); // clear invalid input
            }
        }
    }
}
