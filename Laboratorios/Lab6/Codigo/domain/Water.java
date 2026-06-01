package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Clase que representa al Agua en el bosque.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */
public class Water implements Thing, Serializable {
    private Forest forest;
    private int row, column;
    private Color color;
    
    /**
     * Constructor de la clase Water.
     */
    public Water(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.color = Color.CYAN;
        this.forest.setThing(row, column, this);
    } // Cierre del Constructor
    
    /**
     * Método que retorna la forma que representa al agua.
     * 
     * @return int Retorna el tamaño que representa al agua.
     */
    public final int shape() {
        return Thing.SQUARE;
    } // Cierre del método
    
    /**
     * Método que retorna el color del agua.
     * 
     * @return Color Retorna el color que determina al agua.
     */
    public final Color getColor() {
        return color;
    } // Cierre del método
    
    /**
     * Método que contiene la lógica de pasos del agua.
     */
    public void ticTac() {
        if (isSurroundedByGround()) {
            int newRow = row + 1;
            if (newRow < forest.getSize()) {
                Thing south = forest.getThing(newRow, column);
                if (south instanceof Ground) {
                    new Ground(forest, row, column); 
                    this.row = newRow;
                    forest.setThing(this.row, this.column, this);
                }
            }
        }
    } // Cierre del método
    
    // Método auxiliar
    /**
     * Método que verifica si el agua está rodeada de tierra.
     * 
     * @return boolean Retorna verdadero o falso si a su lado tiene tierra.
     */
    private boolean isSurroundedByGround() {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int nr = row + dr;
                int nc = column + dc;
                if (nr >= 0 && nr < forest.getSize() && nc >= 0 && nc < forest.getSize()) {
                    Thing t = forest.getThing(nr, nc);
                    if (!(t instanceof Ground)) {
                        return false; 
                    }
                }
            }
        }
        return true;
    } // Cierre del método
} // Cierre de la clase