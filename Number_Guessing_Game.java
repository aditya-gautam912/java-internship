// • Use Random class to generate a number between 1-100
// • User tries to guess the number
// • Show hints: "Too low", "Too high", "Correct"
// • Track number of attempts

import java.util.Random;
import java.util.Scanner;
public class Number_Guessing_Game {
    public static void main(String[] args) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // Generate a number between 1 and 100]
        int attempts = 0;
        boolean guessedCorrectly = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100. Can you guess it?");

        while (!guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                guessedCorrectly = true;
                System.out.println("Correct! You've guessed the number in " + attempts + " attempts.");
            }
        }

        scanner.close();
    }
}