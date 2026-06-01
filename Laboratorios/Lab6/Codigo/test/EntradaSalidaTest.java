package test;

import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

/**
 * Realiza casos de prueba para la entrada y salida de la fachada.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 05/05/2026
 */
public class EntradaSalidaTest {
    private Forest bosque;
    
    /**
     * Método que se llama antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
        bosque = new Forest();
    } // Cierre del método.
    
    /**
     * Caso de prueba que verifica que se abra un archivo .dat.
     */
    @Test
    public void shouldOpenADatFile() {
        File archivoPrueba = new File("pruebaForest.dat");
        Tree arbolito = new Tree(bosque, 10, 3);
        Squirrel dante = new Squirrel(bosque, 14, 4);
        Squirrel morris = new Squirrel(bosque, 8, 7);
        try {
            bosque.saveAs(archivoPrueba);
            Forest cargarArchivo = Forest.open(archivoPrueba);
            assertEquals(bosque.getSize(), cargarArchivo.getSize());
        } catch (ForestException excepcion) {
            fail("No se debe lanzar una excepción");
        }
    } // Cierre del caso de prueba.
    
    /**
     * Caso de prueba que determina que no se pueda un archivo no
     * compatible.
     */
    @Test
    public void shouldNotOpenIncompatibleFile() {
        File archivoPrueba = new File("prueba2Forest.dat");
        try {
            bosque.open(archivoPrueba);
            fail("Se debió lanzar la excepción");
        } catch (ForestException excepcion) {
            assertNotNull(excepcion.getMessage());
        }
    } // Cierre del caso de prueba.
    
    /**
     * Caso de prueba que verifica que un archivo .dat se guarde.
     */
    @Test
    public void shouldSaveAFileNormally() {
        File archivoPrueba = new File("prueba3Forest.dat");
        try {
            bosque.saveAs(archivoPrueba);
            assertTrue(archivoPrueba.exists());
        } catch(ForestException excepcion) {
            fail("No se debe lanzar la excepción");
        }
    } // Cierre del caso de prueba.
    
    /**
     * Caso de prueba que comprueba que no se pueda guardar un
     * archivo nulo.
     */
    @Test
    public void shouldNotSaveANullFile() {
        try {
            bosque.saveAs(null);
            fail("Se debió lanzar una excepción");
        } catch(ForestException excepcion) {
            assertNotNull(excepcion.getMessage());
        }
    } // Cierre del caso de prueba.

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del método.
} // Cierre de la clase