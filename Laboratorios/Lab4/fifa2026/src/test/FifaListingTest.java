package test;
import domain.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FifaListingTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FifaListingTest {
    private Fifa fifa;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() throws FifaException {
        fifa = new Fifa();
    }
    
    @Test
    public void shouldListAllParticipants() {
        String resultado = fifa.toString();
        assertTrue(resultado.contains("L.DIAZ"));
        assertTrue(resultado.contains("COLOMBIA"));
    }
    
    @Test
    public void shouldListParticipantsAfterAddingPlayers() {
        fifa.addPlayer("DAVID", "552", "A","4400000","Sport Club");
        fifa.addPlayer("SANTIAGO", "552", "S", "5000000","Inter");
        fifa.addPlayer("MESSI", "1420", "D", "15000000", "Inter");
        String resultado = fifa.toString();
        assertTrue(resultado.contains("DAVID"));
        assertTrue(resultado.contains("SANTIAGO"));
        assertTrue(resultado.contains("MESSI"));
    }
    
    @Test
    public void shouldCountCorrectAmountOfParticipants() {
        assertEquals(6, fifa.numberParticipants());
    }
    
    @Test
    public void shouldSearchPlayersByName() {
        String resultado = fifa.search("James");
        assertNotNull(resultado);
        assertTrue(resultado.contains("JAMES"));
    }
    
    @Test
    public void shouldReturnFalseForEmptyResults() throws FifaException {
        String resultado = fifa.search("Falcao");
        assertFalse(resultado.contains("Falcao"));
    }
    
    // Propuesta
    @Test 

    public void shouldSelectPlayersByPrefix() { 
        ArrayList<Participant> resultado = fifa.select("LUCU"); 
        assertEquals(1, resultado.size()); 
        assertEquals("LUCUMI", resultado.get(0).name()); 
    } 

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        fifa = null;
    }
}