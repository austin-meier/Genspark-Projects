package austinm.projects.project1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons. In front of you,\nyou see two caves. In one cave, the dragon is friendly\nand will share his treasure with you. The other dragon\nis greedy and hungry and will eat you on sight.\nWhich cave will you go into? (1 or 2)");

        try (Scanner scan = new Scanner(System.in)) {

            int i = 0;
            Pattern p = Pattern.compile("[1-2]");

            int c = (Math.random() <= 0.5) ? 1: 2;

            while (i == 0) {
                String input = scan.nextLine();
                i = parseGuess(input, p);
            }

            if (i == c) {
                System.out.println("You approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\nGobbles you down in one bite!");
            } else {
                System.out.println("You approach the cave...\nYou didn't die, nice.");
            }

        }

    }
    private static int parseGuess(String input, Pattern numberPattern) {
        try {
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