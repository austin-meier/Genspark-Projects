package austinm.projects.project2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuessTheNumber {
    private String name;
    private int ANSWER_MIN;
    private int ANSWER_MAX;
    private int MAX_GUESSES;
    private int guesses;
    private int answer;
    private boolean gameIsOver;
    private boolean didWin;

    public GuessTheNumber(String name, int maxGuesses, int answerMin, int answerMax) {
        this.name = name;
        this.guesses = 0;
        this.MAX_GUESSES = maxGuesses;
        this.ANSWER_MIN = answerMin;
        this.ANSWER_MAX = answerMax;
        this.answer = ThreadLocalRandom.current().nextInt(answerMin, answerMax+1);
        this.gameIsOver = false;
    }

    public void makeGuess(String guess) {
        try {
            Pattern p = Pattern.compile("[1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]");
            Matcher m = p.matcher(guess);
            if(m.matches()) {
                checkWin(Integer.parseInt(guess));
            } else {
                System.out.println("Not a valid input option. Please enter a number between " + ANSWER_MIN + " and " + ANSWER_MAX);
            }
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Not a valid input");
        }
    }

    private void checkWin(int guess) {
        this.guesses++;

        if (guess > this.answer) {
            System.out.println("Your guess is too high.");
        } else if (guess < this.answer) {
            System.out.println("Your guess is too low.");
        } else {
            System.out.println("Good job, " + name + "! You guessed my number in " + guesses + " guess" + ((guesses != 1) ? "es" : "") + "!");
            this.gameIsOver = true;
            this.didWin = true;
        }

        if (guesses == MAX_GUESSES && !this.gameIsOver) {
            System.out.println("Oh no " + name + "! You ran out of guesses. Better luck next time!");
            this.gameIsOver = true;
            this.didWin = false;
        }
    }

    public int getAnswerMin() {
        return this.ANSWER_MIN;
    }

    public int getAnswerMax() {
        return this.ANSWER_MAX;
    }

    public int getMaxGuesses() {
        return this.MAX_GUESSES;
    }

    public boolean gameIsOver() {
        return this.gameIsOver;
    }

    public boolean didWin() {
        return this.didWin;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuesses() {
        return this.guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

}
