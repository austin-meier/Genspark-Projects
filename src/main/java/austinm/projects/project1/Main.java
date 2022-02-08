package austinm.projects.project1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("You are in a land full of dragons. In front of you,\nyou see two caves. In one cave, the dragon is friendly\nand will share his treasure with you. The other dragon\nis greedy and hungry and will eat you on sight.\nWhich cave will you go into? (1 or 2)");

            int choice = 0;

            while (choice == 0) {
                String input = scan.nextLine();
                choice = parseGuess(input);
            }

            DragonCave game = new DragonCave(choice);
            game.playGame();

        }
    }

    private static int parseGuess(String input) {
        try {
            Pattern numberPattern = Pattern.compile("[1-2]");
            Matcher m = numberPattern.matcher(input);
            if (m.matches()) return Integer.parseInt(input);
            System.out.println("Not a valid input, please enter '1' or '2'");
            return 0;
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Not a valid input, please enter '1' or '2'");
            return 0;
        }
    }
}