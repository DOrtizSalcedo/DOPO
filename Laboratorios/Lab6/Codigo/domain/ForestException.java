package domain;
import presentation.*;

/**
 * La clase excepción del simulador Forest.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */
public class ForestException extends Exception {
    /**
     * Constructor de la clase excepción ForestException.
     * 
     * @param message La excepción a mostrar.
     */
    public ForestException(String mensaje) {
        super(mensaje);
    } // Cierre del Constructor
} // Cierre de la Clase