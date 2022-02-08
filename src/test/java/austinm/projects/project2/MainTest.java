package austinm.projects.project2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    GuessTheNumber game;
    @BeforeEach
    void setUp() {
    }

    @Test
    void winTest() {
        game = new GuessTheNumber("JUnit5", 3, 1, 3);
        game.setAnswer(3);

        game.makeGuess("1");
        game.makeGuess("2");
        game.makeGuess("3");

        assertEquals(true, game.didWin());
    }

    @Test
    void loseTest() {
        game = new GuessTheNumber("JUnit5", 3, 1, 3);
        game.setAnswer(3);

        game.makeGuess("1");
        game.makeGuess("1");
        game.makeGuess("1");

        assertEquals(false, game.didWin());
    }

    @AfterEach
    void tearDown() {
    }

}