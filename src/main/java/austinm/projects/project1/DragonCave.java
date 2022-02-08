package austinm.projects.project1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DragonCave {
    private int choice;
    private int dragonChoice;

    public DragonCave(int choice, int dragonChoice) {
        this.choice = choice;
        this.dragonChoice = dragonChoice;
    }

    public DragonCave(int choice) {
        this.choice = choice;
        this.dragonChoice = (Math.random() <= 0.5) ? 1 : 2;;
    }

    public boolean playGame() {
        if (this.choice == this.dragonChoice) {
            System.out.println("You approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\nGobbles you down in one bite!");
            return false;
        } else {
            System.out.println("You approach the cave...\nYou didn't die, nice.");
            return true;
        }
    }

    public int getChoice() {
        return this.choice;
    }

    public int getDragonChoice() {
        return this.dragonChoice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void setDragonChoice(int dragonChoice) {
        this.dragonChoice = dragonChoice;
    }
}

