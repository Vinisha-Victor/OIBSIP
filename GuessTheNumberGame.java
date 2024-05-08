import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private static final int MAX_ATTEMPTS = 5; // maximum number of attempts per round
    private static final int MAX_NUMBER = 100; // Maximum number to be guessed

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the number between 1 and " + MAX_NUMBER);

        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = random.nextInt(MAX_NUMBER) + 1;
            int attempts = 0;
            int chance = 5;
            boolean guessedCorrectly = false;

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                attempts++;
                chance--;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number (" + randomNumber + ") correctly in "
                            + attempts + " attempts.");
                    score += MAX_ATTEMPTS - attempts + 1; // Increase score based on remaining attempts
                    guessedCorrectly = true;
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Too low. Try again. You have " + chance + " attempts left.");
                } else {
                    System.out.println("Too high. Try again. You have " + chance + " attempts left.");
                }

            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you didn't guess the number. It was " + randomNumber + ".");
            }

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.nextLine().toLowerCase();
            playAgain = playAgainInput.equals("yes");
        }

        System.out.println("Thanks for playing Guess the Number Game! Your final score is: " + score);
        scanner.close();
    }
}
