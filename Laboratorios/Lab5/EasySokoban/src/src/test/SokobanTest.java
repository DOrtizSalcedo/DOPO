package test;

import domain.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The main class of the Sokoban game has all the logic so the game can be functional.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 25/04/2026
 */

public class SokobanTest {
    private Sokoban sokoban;

    /**
     * Method called before each test.
     */
    @BeforeEach
    public void setUp() {
        sokoban = new Sokoban();
    } // Method Closed

    /**
     * Test that verifies that the player cannot go through a wall.
     */
    @Test
    public void shouldNotMoveThroughWalls() {
        sokoban.movePlayer(0, -1);
        boolean playerPosition = false;
        for(int[] cell : sokoban.getBoardState()) {
            if(cell[2] == Sokoban.PLAYER && cell[0] == 8 && cell[1] == 3) {
                playerPosition = true;
            }
        }
        assertFalse(playerPosition);
    } // Test Closed

    /**
     * Test that verifies boxes cannot be on a destination at the
     * start of a level.
     */
    @Test
    public void shouldNotHaveBoxesOnADestinationAtStart() {
        assertEquals(0, sokoban.countBoxesOnDestination());
    } // Test Closed

    /**
     * Test that validates the size of the board.
     *
     * @throws SokobanException The exceptions according to if the board is small or big.
     */
    @Test
    public void shouldValidateBoardSize() throws SokobanException {
        assertTrue(sokoban.verifyBoardSize());
    } // Test Closed

    /**
     * Test that verifies the board cant have negative values.
     */
    @Test
    public void shouldNotCreateABoardWithNegativeValues() {
        assertThrows(SokobanException.class,
                () -> {sokoban.changeBoardSize(-15, -5); sokoban.verifyBoardSize();});
    } // Test Closed

    /**
     * Method called after each test.
     */
    @AfterEach
    public void tearDown() {
        sokoban = null;
    } // Method Closed
} // Class Closed
