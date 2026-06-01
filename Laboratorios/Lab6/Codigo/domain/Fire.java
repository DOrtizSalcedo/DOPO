package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Clase que representa el Fuego en el bosque.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */
public class Fire implements Thing, Serializable {
    private Forest forest;
    private int row, column;
    private Color color;
    private int cyclesSurroundedByEarth;
    
    /**
     * Constructor de la clase Fire.
     * 
     * @param forest El bosque en donde se encuentra el fuego.
     * @param row La posición de fila donde está el fuego.
     * @param column La posición de columna donde está el fuego.
     */
    public Fire(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.color = Color.RED;
        this.cyclesSurroundedByEarth = 0;
        this.forest.setThing(row, column, this);
    } // Cierre del Constructor
    
    /**
     * Método que retorna la forma del fuego.
     * 
     * @return int El tamaño de la forma que representa al fuego.
     */
    public final int shape() {
        return Thing.ROUND;
    } // Cierre del método
    
    /**
     * Método que retorna el color que representa al fuego.
     * 
     * @return Color El color del fuego.
     */
    public final Color getColor() {
        return color;
    } // Cierre del método
    
    /**
     * Método que contiene la lógica de los pasos del fuego.
     * 
     */
    public void ticTac() {
        if (isNearWater()) {
            new Ground(forest, row, column);
        } else if (isSurroundedByGround()) {
            cyclesSurroundedByEarth++;
            if (cyclesSurroundedByEarth >= 5) {
                new Ground(forest, row, column);
            }
        } else {
            cyclesSurroundedByEarth = 0;
        }
    } // Cierre del método
    
    // Métodos auxiliares
    /**
     * Método que verifica si el fuego está cerca del agua.
     * 
     * @return boolean Retorna verdadero o falso para ver si está cerca del agua.
     */
    private boolean isNearWater() {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int nr = row + dr;
                int nc = column + dc;
                if (nr >= 0 && nr < forest.getSize() && nc >= 0 && nc < forest.getSize()) {
                    if (forest.getThing(nr, nc) instanceof Water) {
                        return true;
                    }
                }
            }
        }
        return false;
    } // Cierre del método
    
    /**
     * Método que verifica si el fuego está rodeado de tierra.
     * 
     * @return Retorna verdadero o falso donde se vé si alrededor de este tiene tierra.
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