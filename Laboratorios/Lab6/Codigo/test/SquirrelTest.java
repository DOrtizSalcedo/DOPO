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
public class SquirrelTest {
    
    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
    } // Cierre del caso de prueba

    /**
     * Verifica que las ardillas mueran en 40 tictacs.
     */
    @Test
    public void shouldDieAt40TicTacs() {
        Forest bosque = new Forest();
        Squirrel luffy = new Squirrel(bosque, 6, 5);
        
        for(int i = 0; i < 50; i++) {
            bosque.ticTac();
        }
        assertNull(bosque.getThing(6, 5));
    }
    
    /**
     * Verifica que la ardilla no vaya a eliminar un árbol.
     */
    @Test
    public void shouldNotDeleteTreesWhenMoving() {
        Forest bosque = new Forest();
        Squirrel franky = new Squirrel(bosque, 3, 13);
        new Tree(bosque, 4, 5);
        new Tree(bosque, 4, 3);
        new Tree(bosque, 6, 7);
        
        for(int i = 0; i < 10; i++) {
            bosque.ticTac();
        }
        
        int contador = 0;
        for(int ardilla1 = 0; ardilla1 < 16; ardilla1++) {
            for(int ardilla2 = 0; ardilla2 < 16; ardilla2++) {
                Thing cosa = bosque.getThing(ardilla1, ardilla2);
                if(cosa != null && cosa.isSquirrel()) {
                    contador++;
                }
            }
        }
        assertEquals(3, contador);
    }

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba
} // Cierre de la clase