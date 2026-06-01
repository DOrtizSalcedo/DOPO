package domain;

import java.awt.Color;
import java.util.Random;
import java.io.Serializable;

/**
 * Clase que representa a la Tierra en el bosque.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */
public class Ground implements Thing, Serializable {
    private Forest forest;
    private int row, column;
    private Color color;
    private Random random;
    
    /**
     * Constructor de la clase Ground.
     */
    public Ground(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.color = new Color(139, 69, 19); // color café
        this.random = new Random();
        this.forest.setThing(row, column, this);
    } // Cierre del Constructor
    
    /**
     * Método que retorna la forma de la tierra.
     * 
     * @return int Retorna el tamaño de la figura que representa la tierra.
     */
    public final int shape() {
        return Thing.SQUARE;
    } // Cierre del método
    
    /**
     * Método que retorna el color de la tierra.
     * 
     * @return Color Retorna el color que caracteriza a la tierra.
     */
    public final Color getColor() {
        return color;
    } // Cierre del método
    
    /**
     * Método que contiene la lógica de pasos de la tierra.
     */
    public void ticTac() {
        double r = random.nextDouble();
        if (r < 0.10) {
            new Fire(forest, row, column);
        } else if (r < 0.15) { 
            new Water(forest, row, column);
        }
    } // Cierre del método
} // Cierre de la clase