package test;
import domain.*;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que contiene casos de prueba para la clase Shadow.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ShadowTest {

    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
    }
    
    /**
     * Verifica que cuando una cosa (ardilla) muere, entonces la sombra también desaparece.
     */
    @Test
    public void shouldDieWhenThingDies() {
        Forest bosque = new Forest();
        Squirrel nami = new Squirrel(bosque, 5, 5);
        bosque.setThing(5, 5, null);
        bosque.ticTac();

        assertNull(bosque.getThing(5, 6));
    }

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    }
}