package test;

import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

/**
 * Realiza casos de prueba para comprobar que se puedan importar de archivos
 * al simulador y se pueda exportar archivos desde el simulador correctamente.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 05/05/2026
 */
public class ImportarExportarTest {
    private Forest bosque;
    
    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
        bosque = new Forest();
    } // Cierre del método
    
    /**
     * Caso de prueba que verifica que un archivo pueda ser importado correctamente.
     */
    @Test
    public void shouldImportAValidFile() {
        File archivoPrueba = new File("archivoPrueba2.txt");
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(archivoPrueba));
            escritor.println("Tamaño del bosque 20");
            escritor.println("Pine 10 4");
            escritor.println("Water 1 6");
            escritor.close();
            
            bosque.importFile(archivoPrueba);
            assertNotNull(bosque.getThing(10, 4));
            assertNotNull(bosque.getThing(1, 6));
        } catch (ForestException excepcion) {
            fail("No debe lanzar una excepción");
        } catch (IOException excepcion) {
            fail("No debe lanzar una excepción");
        }
    } // Cierre del caso de prueba
    
    /**
     * Caso de prueba que mira que no se pueda importar un archivo que no
     * sea compatible.
     */
    @Test
    public void shouldNotImportIncompatibleFile() {
        File archivoPrueba = new File("archivoMaloDePrueba.txt");
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(archivoPrueba));
            escritor.println("Tamaño");
            escritor.println("Fire diez doce");
            escritor.close();
            
            bosque.importFile(archivoPrueba);
            fail("Se debió lanzar una excepción");
        } catch (ForestException excepcion) {
            assertNotNull("La excepción es correcta", excepcion.getMessage());
        } catch (IOException excepcion) {
            fail("Archivo de prueba no creado");
        }
    } // Cierre del caso de prueba
    
    /**
     * Caso de prueba que verifica que el archivo de texto pueda ser legible.
     * Es decir, se muestren los datos independientemente 
     * de qué aplicación se use.
     */
    @Test
    public void shouldHaveAllObjects() {
        File archivoPrueba = new File("archivoPrueba4.txt");
        try {
            bosque.exportAs(archivoPrueba);
            assertTrue(archivoPrueba.length() > 0);
        } catch(ForestException excepcion) {
            fail("No debe lanzar excepción");
        }
    } // Cierre del caso de prueba
    
    /**
     * Caso de prueba que mira si el archivo está vacío.
     */
    @Test
    public void shouldExportIfFileIsEmpty() {
        File archivoPrueba = new File("");
        try {
            bosque.exportAs(archivoPrueba);
            fail("Se debió lanzar una excepción");
        } catch(ForestException excepcion) {
            assertNotNull(excepcion.getMessage());
        }
    } // Cierre del caso de prueba

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del método
} // Cierre de la clase