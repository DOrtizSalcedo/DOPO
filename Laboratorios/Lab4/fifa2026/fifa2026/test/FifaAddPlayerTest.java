package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FifaAddTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FifaAddPlayerTest {
    private Fifa fifa;

    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
        fifa = new Fifa();
    }
    
    @Test
    public void shouldAddAPlayerNormally() {
        try {
            int contador = fifa.numberParticipants();
            fifa.addPlayer("MESSI", "1420", "D","15000000", "Inter");
            assertEquals(contador + 1, fifa.numberParticipants());
        } catch(FifaException e) {
            fail("El jugador tiene datos válidos, no debe lanzar excepción.");
        }
    }
    
    @Test
    public void shouldThrowExceptionIfPlayerAlreadyExists() {
        try {
            fifa.addPlayer("SERGIO", "503", "A", "2200000", "Minnesota");
            fifa.addPlayer("SERGIO", "403", "S", "3000000", "Club Nuevo");
            fail("Debió lanzar una excepción por jugador existente.");
        } catch(FifaException e) {
            assertEquals(FifaException.PLAYER_ALREADY_EXISTS, e.getMessage());
        }
    }
    
    @Test
    public void shouldFailWhenClubIsEmpty() {
        try {
            fifa.addPlayer("DAVID", "552", "A", "4400000", null);
            fail("Se debió lanzar una excepción por club vacío");
        } catch(FifaException e) {
            assertEquals(FifaException.CLUB_UNKNOWN, e.getMessage());
        }
    }
    
    @Test
    public void shouldFailWhenMinutesAreNegative() {
        try {
            fifa.addPlayer("DAVID", "-552", "A", "2200000","Sport Club");
            fail("Debió lanzar una excepción por minutos negativos");
        } catch(FifaException e) {
            assertEquals(FifaException.MINUTES_UNKNOWN, e.getMessage());
        }
    }
    
    @Test
    public void shouldFailWhenValueIsNegative() {
        try {
            fifa.addPlayer("SANTIAGO", "552", "S", "-5000000","Inter");
            fail("Se debió lanzar una excepción por valor negativo");
        } catch(FifaException e) {
            assertEquals(FifaException.VALUE_UNKNOWN, e.getMessage());
        }
    }
    
    @Test
    public void shouldThrowExceptionIfValuesAreNotNumeric() {
        try {
            fifa.addPlayer("JUGADOR2", "1000", "A", "abc", "Club");
            fail("Debio lanzar una excepción por valores no numéricos.");
        } catch(FifaException e) {
            assertEquals(FifaException.INVALID_NUMBER, e.getMessage());
        } catch(Exception e) {
            fail("Excepción incorrecta: " + e.getMessage());
        }
    }
    
    @Test
    public void shouldThrowExceptionIfNameIsEmpty() {
        try {
            fifa.addPlayer(" ", "100", "A", "1000", "Club");
            fail("Se debió lanzar una excepción por tener un nombre vacío.");
        } catch(FifaException e) {
            assertEquals(FifaException.INVALID_NAME, e.getMessage());
        } 
    }
    
    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
        fifa = null;
    }
}