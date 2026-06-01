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
public class Earth implements Thing, Serializable {
    private Forest forest;
    private int row, column;
    private Color color;
    private Random random;

    public Earth(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.color = new Color(139, 69, 19); // Color Café
        this.random = new Random();
        this.forest.setThing(row, column, this);
    }
    
    public final int shape() {
        return Thing.SQUARE;
    }
    
    public final Color getColor() {
        return color;
    }
    
    public void ticTac() {
        // La tierra puede generar fuego con probabilidad de 10%
        // y agua con probabilidad de 5%.
        double r = random.nextDouble();
        if (r < 0.10) {
            new Fire(forest, row, column);
        } else if (r < 0.15) { // 0.10 a 0.15 es 5%
            new Water(forest, row, column);
        }
    }
}
