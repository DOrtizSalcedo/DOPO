package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Realiza casos de prueba para la clase Tree.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 18/03/2026
 */
public class TreeTest {
    
    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
    } // Cierre del caso de prueba

    /**
     * Verifica que la energía del árbol haya disminuido después de 1 tic tac.
     */
    @Test
    public void shouldEvolveAfterATicTac() {
        Forest bosque = new Forest();
        Tree arbolito = new Tree(bosque, 10, 3);
        int energiaInicial = arbolito.getEnergy();
        bosque.ticTac();
        assertTrue(arbolito.getEnergy() <= energiaInicial);
    }

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba
} // Cierre de la clase
