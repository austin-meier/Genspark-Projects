package austinm.projects.project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Hangman {

    public final int AMOUNT_OF_LINES = 58109;

    void start() {
        boolean playing = true;
        String answer = randomWord(loadFile("wordlist.txt"));
        String word = generateWordDisplay(answer.length());
        ArrayList<String> missedLetters = new ArrayList<>();

        try (Scanner scan = new Scanner(System.in)) {
            while (playing) {
                int guesses = missedLetters.size();

                if (checkForWin(answer, word)) {
                    System.out.println("Yes!, The secret word is " + answer + "! You have won!");
                    if (gameOver(scan)) break;
                    else {
                        answer = randomWord(loadFile("wordlist.txt"));
                        word = generateWordDisplay(answer.length());
                        missedLetters = new ArrayList<>();
                    }
                }

                System.out.println(getBoard(guesses));
                System.out.println("Missed Letters" + missedLetters);
                System.out.println(word);

                if (guesses == 6) {
                    System.out.println("Oh no!, you have ran out of guesses. The secret word was " + answer + ". Better luck next time.");
                    if (gameOver(scan)) break;
                    else {
                        answer = randomWord(loadFile("wordlist.txt"));
                        word = generateWordDisplay(answer.length());
                        missedLetters = new ArrayList<>();
                    }

                }

                String guess = getInput(scan, "Guess a Letter.");

                if (!validInput(guess, "^[A-Z]")) {
                    System.out.println("That is not a valid guess. Please try again and enter a single letter guess");
                    continue;
                }
                if (checkDuplicateGuess(missedLetters, word, guess)) {
                    System.out.println("You have already guessed that letter. Choose again.");
                    continue;
                }

                if (answerContainsGuess(answer, guess)) {
                    word = updateWordDisplay(word, answer, guess);
                } else {
                    missedLetters = addMissedGuess(missedLetters, guess);
                }

            }
        }
    }

    boolean gameOver(Scanner scan) {
        String input = getInput(scan, "Do you want to play again? (yes or no)");
        if (validInput(input, "^((YES)|(NO))")) {
            if (input.equals("YES")) return true;
        } else {
            System.out.println("That is not a valid choice, please enter 'yes' or 'no'");
            gameOver(scan);
        }
        return false;
    }


    String getInput(Scanner scan, String text) {
        System.out.println(text);
        return scan.nextLine().toUpperCase(Locale.ROOT);
    }

    boolean validInput(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) return true;
        return false;
    }

    ArrayList<String> addMissedGuess(ArrayList<String> missed, String guess) { missed.add(guess); return missed; }

    boolean checkDuplicateGuess(ArrayList<String> missed, String word, String guess) {
        if (word.indexOf(guess) != -1 || missed.indexOf(guess) != -1) return true;
        return false;
    }

    boolean answerContainsGuess(String answer, String guess) {
        if (answer.indexOf(guess) != -1) return true;
        return false;
    }

    boolean checkForWin(String answer, String word) {return answer.equals(word);}

    File loadFile(String name) {
        try {
            return new File(Thread.currentThread().getContextClassLoader().getResource(name).getPath());
        } catch (NullPointerException e) {
            return null;
        }
    }

    String randomWord(File file) {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines.skip(ThreadLocalRandom.current().nextInt(AMOUNT_OF_LINES)).findFirst().get().toUpperCase(Locale.ROOT);
        } catch(IOException e) {
            System.out.println("Exception choosing a random word. Returning a default word");
        }
        return "WORD";
    }

    String generateWordDisplay(int length) {
        String word = "";
        for (int i = 0; i < length; i++) word += "_";
        return word;
    }

    String updateWordDisplay(String word, String answer, String guess) {
        int index = answer.indexOf(guess);
        while (index >= 0) {
            word = word.substring(0, index) + guess + word.substring(index+1);
            index = answer.indexOf(guess, index + 1);
        }
        return word;
    }

    String getBoard(int i) {
        switch (i) {
            default:
                return """
                         H A N G M A N
                         +---+
                             |
                             |
                             |
                            ===
                        """;
            case 1:
                return """
                         +---+
                         O   |
                             |
                             |
                            ===
                        """;
            case 2:
                return """
                         +---+
                         O   |
                         |   |
                             |
                            ===
                        """;
            case 3:
                return """
                         +---+
                         O   |
                        /|   |
                             |
                            ===
                        """;
            case 4:
                return """
                         +---+
                         O   |
                        /|\\  |
                             |
                            ===
                        """;
            case 5:
                return """
                         +---+
                         O   |
                        /|\\  |
                        /    |
                            ===
                        """;
            case 6:
                return """
                         +---+
                         O   |
                        /|\\  |
                        / \\  |
                            ===
                        """;
        }
    }


}
