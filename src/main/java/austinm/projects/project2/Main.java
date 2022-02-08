package austinm.projects.project2;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int NUMBER_MIN = 1;
    private static final int NUMBER_MAX = 20;
    private static final int AMOUNT_OF_GUESSES = 6;
    private static final int OUT_OF_BOUNDS_ERROR_CODE = NUMBER_MIN - 1;

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Hello! What is your name?");
            String name = scan.nextLine();

            playGame(name, scan);
        }
    }

    private static void playGame(String name, Scanner scan) {
        int ans = ThreadLocalRandom.current().nextInt(NUMBER_MIN, NUMBER_MAX+1);
        int guesses = 1;
        String input;

        System.out.println("Well, " + name + ", I am thinking of a number between " + NUMBER_MIN + " and " + NUMBER_MAX +".");
        System.out.println("You have " + AMOUNT_OF_GUESSES + " chances to guess my number. Good luck :)");

        while (guesses <= AMOUNT_OF_GUESSES) {
            System.out.println("Take a guess.");
            input = scan.nextLine();

            int temp = parseGuess(input);
            if (temp >= NUMBER_MIN && temp <= NUMBER_MAX) {
                if (temp > ans) {
                    System.out.println("Your guess is too high.");
                } else if (temp < ans) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Good job, " + name + "! You guessed my number in " + guesses + " guesses!");
                    break;
                }
                if (guesses == AMOUNT_OF_GUESSES) {
                    System.out.println("Oh no " + name + "! You ran out of guesses. Better luck next time!");
                    break;
                }

                guesses++;
            } else {
                System.out.println("Please enter a number between " + NUMBER_MIN + " and " + NUMBER_MAX +".");
            }
        }

        System.out.println("Would you like to play again? (y or n)");
        input = scan.nextLine();
        if (input.charAt(0) == 'y') playGame(name, scan);
        else { scan.close(); System.exit(0);}
    }

    private static int parseGuess(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Not a valid input");
            return OUT_OF_BOUNDS_ERROR_CODE;
        }
    }
}