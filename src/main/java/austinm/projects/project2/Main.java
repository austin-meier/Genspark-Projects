package austinm.projects.project2;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int AMOUNT_OF_GUESSES = 6;
    private static String name;

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Hello! What is your name?");
            name = scan.nextLine();

            playGame(scan);
        }
    }

    private static void playGame(Scanner scan) {
        int ans = ThreadLocalRandom.current().nextInt(1, 20+1);
        int guesses = 1;
        String input;

        System.out.println("Well," + name + ", I am thinking of a number between 1 and 20.");

        while (guesses <= AMOUNT_OF_GUESSES) {
            System.out.println("Take a guess.");
            input = scan.nextLine();

            int temp = Integer.parseInt(input);
            if (temp > ans) {
                System.out.println("Your guess is too high.");
            } else if (temp < ans) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Good job, " + name + "! You guessed my number in " + guesses +" guesses!");
                break;
            }
            if (guesses == AMOUNT_OF_GUESSES) {
                System.out.println("Oh no " + name + "! You ran out of guesses. Better luck next time!");
                break;
            }

            guesses++;
        }

        System.out.println("Would you like to play again? (y or n)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("y")) playGame(scan);
        else { scan.close(); System.exit(0);}
    }
}