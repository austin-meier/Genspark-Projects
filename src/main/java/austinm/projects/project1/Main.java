package austinm.projects.project1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons. In front of you,\nyou see two caves. In one cave, the dragon is friendly\nand will share his treasure with you. The other dragon\nis greedy and hungry and will eat you on sight.\nWhich cave will you go into? (1 or 2)");

        try (Scanner scan = new Scanner(System.in)) {

            String input = scan.nextLine();

            int i = Integer.parseInt(input);
            int c = (Math.random() <= 0.5) ? 1: 2;
            if (i == c) {
                System.out.println("You approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\nGobbles you down in one bite!");
            } else {
                System.out.println("You approach the cave...\nYou didn't die, nice.");
            }

        }

    }
}