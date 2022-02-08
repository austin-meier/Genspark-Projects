package austinm.projects.project1;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    DragonCave game;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        game = new DragonCave(1);
    }

    @org.junit.jupiter.api.Test
    void winTest() {
        game.setDragonChoice(2);
        assertEquals(true, game.playGame());
    }

    @org.junit.jupiter.api.Test
    void loseTest() {
        game.setChoice(game.getDragonChoice());
        assertEquals(false, game.playGame());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}