package domain;
import java.awt.Color;

/**
 * Clase que contiene la lógica de un árbol en el bosque.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 18/03/2026
 */
public class Tree extends LivingThing implements Thing{
    private Forest forest;
    
    protected int row,column;    
    protected Color color;
    private int season; 
    private int tictac;
    
    /**
     * Constructor de la clase Tree.
     */
    public Tree(Forest forest,int row, int column){
        this.forest=forest;
        this.row=row;
        this.column=column; 
        this.color=Color.PINK;
        this.season=0;
        this.tictac=0;
        this.forest.setThing(row,column,(Thing)this);    
    } // Cierre del Constructor

    /**
     * Método que obtiene la posición de fila del árbol.
     * 
     * @return int Retorna la posición de fila.
     */
    public final int getRow(){
        return row;
    } // Cierre del método
    
    /**
     * Método que obtiene la posición de columna del árbol.
     * 
     * @return int Retorna la posición de columna.
     */
    public final int getColumn(){
        return column;
    } // Cierre del método

    
    /**
     * Método que retorna el color del árbol.
     * 
     * @return Color Retorna el color determinado del árbol.
     */
    public final Color getColor(){
        return color;
    } // Cierre del método

    /**
     * Método que retorna la forma del árbol.
     * 
     * @return int El tamaño de la figura que representa al árbol.
     */
    @Override
    public final int shape(){
        return Thing.ROUND;
    } // Cierre del método

    /**
     * Método que sobre-escribe la lógica de los pasos, en este caso, para un árbol. 
     * Muere cuando pierde energía hasta 203 tictacs.
     * 
     */
    @Override
    public void ticTac(){
        tictac++;
        color=(tictac % 4==0? Color.PINK:
               tictac % 4==1? Color.GREEN:
               tictac % 4==2? Color.ORANGE:
               Color.GRAY);
        if (tictac % 4 == 1){
            years+=1;
        }
        if (tictac % 4 == 3){
            boolean OK=step();
            if (! OK){
                die();
            }
        }
    } // Cierre del método
      
    /**
     * Método que permite morir al árbol.
     */
    public void die(){
        forest.setThing(row, column,null);
    } // Cierre del método
} // Cierre de la clase