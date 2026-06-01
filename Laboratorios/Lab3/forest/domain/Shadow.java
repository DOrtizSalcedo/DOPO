package domain;
import java.awt.Color;

/**
 * Clase que contiene la lógica de la sombra en el bosque (únicamente teniendo en cuenta a la Ardilla).
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 21/03/2026
 */
public class Shadow implements Thing {
    protected int row,column;
    protected Color color;
    private Forest bosque;
    private Thing dueñoSombra;
    private int tictac;

    /**
     * Constructor de la clase Shadow.
     * 
     * @param bosque El bosque donde estará la sombra.
     * @param sombra La sombra que tiene una cosa.
     */
    public Shadow(Forest bosque, Thing sombraCosa) {
        this.row = sombraCosa.getRow();
        this.column = sombraCosa.getColumn() + 1;
        this.color = Color.black;
        this.bosque = bosque;
        this.dueñoSombra = sombraCosa;
        this.tictac = 0;
        this.bosque.setThing(this.row, this.column, this);
    } // Cierre del Constructor

    /**
     * Método que retorna la forma de la sombra.
     * 
     * @return int Retorna el tamaño de la figura.
     */
    @Override
    public int shape() {
        return Thing.ROUND;
    } // Cierre del método
    
    /**
     * Método que retorna el color de la sombra.
     * 
     * @return Color Retorna el color de la sombra (color negro de acuerdo a lo dado en el laboratorio).
     */
    public Color getColor() {
        return Color.black;
    } // Cierre del método
    
    /**
     * Método que sobre-escribe y contiene la lógica de los pasos de la sombra (sólamente de la Ardilla).
     * 
     */
    @Override
    public void ticTac() {
    tictac++;

    int filaAnterior = this.row;
    int columnaAnterior = this.column;
    
    int nuevaFila = dueñoSombra.getRow();
    int nuevaCol = dueñoSombra.getColumn() + 1; 

    Thing dueñoVivo = bosque.getThing(dueñoSombra.getRow(), dueñoSombra.getColumn());

    if (dueñoVivo != null && (dueñoVivo.isSquirrel())) {
        bosque.setThing(filaAnterior, columnaAnterior, null);
        
        this.row = nuevaFila;
        this.column = nuevaCol;
        
        if (this.column < 16 && (bosque.getThing(this.row, this.column) == null)) {
            bosque.setThing(this.row, this.column, this);
            }
        } else {
        bosque.setThing(filaAnterior, columnaAnterior, null);
        }
    } // Cierre del método
    
    /**
     * Método que retorna la fila de la sombra.
     * 
     * @return int Retorna la posición en fila de la sombra.
     */
    public final int getRow() {
        return row;
    } // Cierre del método
    
    /**
     * Método que retorna la columna de la sombra.
     * 
     * @return int Retorna la posición en columna de la sombra.
     */
    public final int getColumn() {
        return column;
    } // Cierre del método
} // Cierre de la clase