// Simulate an ATM with options like:
// • Check balance
// • Deposit amount
// • Withdraw amount
// • Exit
// Use: Loops,conditions, and basic methods.

import java.util.Scanner;
public class ATM_Simulation {
    private static double balance = 1000.00; // Initial balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWelcome to the ATM Simulation!");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositAmount(scanner);
                    break;
                case 3:
                    withdrawAmount(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM Simulation. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
    }

    private static void depositAmount(Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            System.out.printf("You have deposited: $%.2f%n", amount);
            checkBalance();
        } else {
            System.out.println("Invalid amount! Please enter a positive number.");
        }
    }

    private static void withdrawAmount(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("You have withdrawn: $%.2f%n", amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient funds! Please enter an amount less than or equal to your balance.");
        } else {
            System.out.println("Invalid amount! Please enter a positive number.");
        }
    }
}