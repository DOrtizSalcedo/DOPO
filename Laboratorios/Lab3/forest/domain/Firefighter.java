package domain;

import java.awt.Color;
import java.util.Random;

/**
 * Clase que representa a un Bombero (Firefighter) en el bosque.
 * Busca celdas con Fuego para extinguirlas. Se mueve de forma aleatoria 
 * o hacia el foco de incendio más cercano.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 21/03/2026
 */
public class Firefighter extends LivingThing implements Thing {
    private Forest forest;
    private int row, column;
    private Color color;
    private Random random;
    
    /**
     * Constructor de la clase Firefighter.
     * 
     * @param forest el bosque en el que se encuentra
     * @param row fila inicial
     * @param column columna inicial
     */
    public Firefighter(Forest forest, int row, int column) {
        super();
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.color = Color.BLUE;
        this.random = new Random();
        this.forest.setThing(row, column, (Thing)this);
    } // Cierre del Constructor
    
    /**
     * Retorna la forma del Bombero.
     * 
     * @return forma
     */
    public final int shape() {
        return Thing.ROUND; 
    } // Cierre del método
    
    /**
     * Retorna el color del Bombero.
     * 
     * @return color
     */
    public final Color getColor() {
        return color;
    } // Cierre del método
    
    /**
     * Acción del bombero en cada turno.
     * Busca el fuego más cercano y se dirige a él para extinguirlo,
     * o se mueve de forma aleatoria.
     */
    public void ticTac() {
        boolean ok = step();
        if (ok) {
            int[] nearestFire = findNearestFire();
            if (nearestFire != null) {
                int targetRow = nearestFire[0];
                int targetCol = nearestFire[1];
       
                if (Math.abs(row - targetRow) <= 1 && Math.abs(column - targetCol) <= 1) {
                    extinguish(targetRow, targetCol);
                } else {
                    moveTowards(targetRow, targetCol);
                }
            } else {
                moveRandomly();
            }
        }
    } // Cierre del método
    
    // Métodos auxiliares
    /**
     * Apaga el fuego en la celda indicada.
     * 
     * @param r fila
     * @param c columna
     */
    private void extinguish(int r, int c) {
        Thing t = forest.getThing(r, c);
        if (t != null && t instanceof Fire) {
            new Ground(forest, r, c);
        }
    } // Cierre del método
    
    /**
     * Mueve el bombero hacia la celda objetivo.
     * 
     * @param targetRow fila objetivo
     * @param targetCol columna objetivo
     */
    private void moveTowards(int targetRow, int targetCol) {
        int dr = Integer.compare(targetRow, row);
        int dc = Integer.compare(targetCol, column);
        
        moveTo(row + dr, column + dc);
    } // Cierre del método
    
    /**
     * Mueve el bombero de forma aleatoria.
     */
    private void moveRandomly() {
        int dr = random.nextInt(3) - 1;
        int dc = random.nextInt(3) - 1;
        
        moveTo(row + dr, column + dc);
    } // Cierre del método
    
    /**
     * Intenta moverse a una nueva celda si es Tierra.
     * 
     * @param newRow nueva fila
     * @param newCol nueva columna
     */
    private void moveTo(int newRow, int newCol) {
        if (newRow >= 0 && newRow < forest.getSize() && newCol >= 0 && newCol < forest.getSize()) {
            Thing target = forest.getThing(newRow, newCol);
            if (target == null || target instanceof Ground) {
                new Ground(forest, row, column); 
                this.row = newRow;
                this.column = newCol;
                forest.setThing(row, column, (Thing)this);
            }
        }
    } // Cierre del método
    
    /**
     * Encuentra la celda con fuego más cercana.
     * 
     * @return arreglo con fila y columna del fuego, o null si no hay fuego.
     */
    private int[] findNearestFire() {
        int[] nearest = null;
        int minDistance = Integer.MAX_VALUE;
        
        for (int r = 0; r < forest.getSize(); r++) {
            for (int c = 0; c < forest.getSize(); c++) {
                Thing t = forest.getThing(r, c);
                if (t != null && t.getClass().getSimpleName().equals("Fire")) {
                    int dist = Math.abs(row - r) + Math.abs(column - c); // Distancia Manhattan
                    if (dist < minDistance) {
                        minDistance = dist;
                        nearest = new int[]{r, c};
                    }
                }
            }
        }
        return nearest;
    } // Cierre del método
} // Cierre de la clase