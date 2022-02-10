package austinm.projects.project5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    Hangman game;
    @BeforeEach
    void setUp() {
        game = new Hangman();
    }

    @DisplayName("Test File Loading")
    @Test
    void testFile() {
        assertEquals(new File("C:\\Users\\Austin\\IdeaProjects\\Austin_Meier_Project1\\target\\classes\\wordlist.txt"), game.loadFile("wordlist.txt"));
    }

    @DisplayName("Test Random Word Generation")
    @Test
    void testRandomWord() {
        assertNotEquals("WORD", game.randomWord(game.loadFile("wordlist.txt")));
    }

    @DisplayName("Test Board Generation")
    @Test
    void testBoard() {
        assertEquals("""
                         H A N G M A N
                         +---+
                             |
                             |
                             |
                            ===
                        """, game.getBoard(0));
    }

    @DisplayName("Test Answer Contains Guess")
    @Test
    void testAnswerContainsGuess() {
        assertEquals(true, game.answerContainsGuess("austin", "a"));
        assertEquals(false, game.answerContainsGuess("austin", "h"));
    }

    @DisplayName("Test For Duplicated Guess")
    @Test
    void testWordForDuplicate() {
        String[] missedArray = {"b", "c", "f"};
        ArrayList<String> missed = new ArrayList<>(Arrays.asList(missedArray));

        assertEquals(true, game.checkDuplicateGuess(missed,"au__in", "a"));
        assertEquals(false, game.answerContainsGuess("", "h"));
    }

    @DisplayName("Test Add Missed Guess")
    @Test
    void testAddMissedGuess() {
        String[] missedArray = {"b", "c", "f"};
        ArrayList<String> missed = new ArrayList<>(Arrays.asList(missedArray));

        String[] expectedMissedArray = {"b", "c", "f", "g"};
        ArrayList<String> expectedMissed = new ArrayList<>(Arrays.asList(expectedMissedArray));

        assertEquals(expectedMissed, game.addMissedGuess(missed, "g") );
    }

    @DisplayName("Test Valid Input")
    @Test
    void testValidInput() {
        String pattern = "^[A-Z]";
        assertEquals(true, game.validInput("A", pattern));
        assertEquals(false, game.validInput("a", pattern));
        assertEquals(false, game.validInput("AA", pattern));
    }

    @DisplayName("Test Generate Word Display")
    @Test
    void testGenerateWordDisplay() {
        assertEquals("_____", game.generateWordDisplay(5));
    }

    @DisplayName("Test Update Word Display")
    @Test
    void testUpdateWordDisplay() {
        assertEquals("au_tin", game.updateWordDisplay("au__in", "austin", "t"));
        assertEquals("au__in", game.updateWordDisplay("au__in", "austin", "h"));
    }

    @DisplayName("Test Check For Win")
    @Test
    void testCheckForWin() {
        assertEquals(true, game.checkForWin("austin", "austin"));
        assertEquals(false, game.checkForWin("austin", "a_stin"));
    }

    @AfterEach
    void tearDown() {
    }
}