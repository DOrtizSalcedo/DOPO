package domain;
import java.awt.Color;
import java.io.Serializable;

/**
 * Clase que representa un Pino en el bosque.
 * A diferencia de un árbol normal, el pino siempre es verde oscuro y resiste más.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */
public class Pine extends Tree implements Serializable {
    
    /**
     * Constructor para la clase Pine.
     * 
     * @param forest el bosque
     * @param row fila
     * @param column columna
     */
    public Pine(Forest forest, int row, int column) {
        super(forest, row, column);
        this.color = new Color(0, 100, 0);
        setEnergy(150); 
    } // Cierre del Constructor
    
    /**
     * El ticTac del pino es similar al del árbol pero no cambia de color
     * con las estaciones.
     */
    @Override
    public void ticTac() {
        super.ticTac();
        this.color = new Color(0, 100, 0);
    } // Cierre del método
} // Cierre de la clase