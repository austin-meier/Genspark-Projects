package austinm.projects.project2;

import java.util.Scanner;

public class Main {
    // Note: Regex is designed to match within the bounds of 0-9999
    private static final int ANSWER_MIN = 100;
    private static final int ANSWER_MAX = 105;
    private static final int AMOUNT_OF_GUESSES = 6;
    static GuessTheNumber game;

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Hello! What is your name?");
            String input = scan.nextLine();

            playGame(input, scan);
        }
    }

    public static void playGame(String name, Scanner scan) {

        game = new GuessTheNumber(name, AMOUNT_OF_GUESSES, ANSWER_MIN, ANSWER_MAX);

        System.out.println("Well, " + game.getName() + ", I am thinking of a number between " + game.getAnswerMin() + " and " + game.getAnswerMax() +".");
        System.out.println("You have " + game.getMaxGuesses() + " chances to guess my number. Good luck :)");
        String input;
        while (!game.gameIsOver()) {
            System.out.println("Take a guess.");
            input = scan.nextLine();
            game.makeGuess(input);
        }

        System.out.println("Would you like to play again? (y or n)");
        input = scan.nextLine();
        if (input.charAt(0) == 'y') playGame(name, scan);
        else { scan.close(); System.exit(0);}
    }



}