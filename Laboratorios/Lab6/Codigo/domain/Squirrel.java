package domain;

import java.awt.Color;
import java.util.Random;
import java.io.Serializable;

/**
 * Clase que contiene la lógica de la ardilla en el bosque.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */
public class Squirrel extends LivingThing implements Thing, Serializable {
    private Forest forest;
    protected int row;
    protected int column;
    protected Color color;
    private int tictac;

    /**
     * Constructor de la clase Squirrel.
     * 
     * @param forest El bosque donde estará la Ardilla.
     */
    public Squirrel(Forest forest) {
       this.forest = forest;
       Random posicionAleatoria = new Random();
       this.row = posicionAleatoria.nextInt(16);
       this.column = posicionAleatoria.nextInt(16);
       Color cafe = new Color(150, 75, 0);
       this.color = cafe;
       this.tictac = 0;
       this.forest.setThing(this.row, this.column, (Thing)this);
    } // Cierre del Constructor
    
    /**
     * Constructor para crear una nueva ardilla a partir de la reproducción de otras 2.
     * 
     * @param forest El bosque donde estará la Ardilla.
     * @param row La fila donde estará la nueva Ardilla.
     * @param column La columna donde estará la Ardilla.
     */
    public Squirrel(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.color = new Color(150, 75, 0);
        this.forest.setThing(this.row, this.column, (Thing)this);
    } // Cierre del Constructor

    /**
     * Método que retorna la fila donde estará la Ardilla.
     * 
     * @return int Retorna la posición en fila donde está la Ardilla.
     */
    public final int getRow(){
        return row;
    } // Cierre del método

    /**
     * Método que retorna la columna donde estará la Ardilla.
     * 
     * @return int Retorna la posición en columna donde está la Ardilla.
     */
    public final int getColumn(){
        return column;
    } // Cierre del método

    /**
     * Método que retorna el color de la Ardilla.
     * 
     * @return Color Retorna el color de la Ardilla.
     */
    public final Color getColor(){
        return color;
    } // Cierre del método

    /**
     * Método que retorna la forma de la Ardilla.
     * 
     * @return int Retorna el tamaño de cómo se visualiza la Ardilla.
     */
    public final int shape(){
        return Thing.SQUARE;
    } // Cierre del método

    /**
     * Método que tiene como fin, marcar los pasos que realiza la Ardilla a través de
     * los años, cambiando de color y muriendo cuando tiene 10 años.
     */
    @Override
    public void ticTac(){
        tictac++;
        if(years < 2) {
            this.color = new Color(150, 75, 0); // color café
        } else if(years < 6) {
            this.color = Color.YELLOW;
        } else {
            this.color = new Color(222, 148, 29); // color naranja
        }
        int tiempoArdilla = tictac % 4;
        
        switch(tiempoArdilla) {
            case 0:
                move();
                break;
            case 1:
                years += 1;
                break;
            case 2:
                newSquirrel();
                break;
            case 3:
                boolean OK = step();
                if(!OK) {
                    die();
                }
                break;
        }
        
        if(years >= 10) {
            die();
            return;
        }
    } // Cierre del método
      
    /**
     * Método que hace morir la Ardilla.
     */
    public void die(){
        forest.setThing(row, column, null);
    } // Cierre del método
    
    // Métodos auxiliares
    /**
     * Método privado que permite mover a la Ardilla aleatoriamente 
     * en todo el bosque.
     */
    private void move() {
        Random posicionAleatoria = new Random();
        int nuevaFila = posicionAleatoria.nextInt(16);
        int nuevaColumna = posicionAleatoria.nextInt(16);
        
        Thing posicionNueva = forest.getThing(nuevaFila, nuevaColumna);
        
        if(posicionNueva == null || posicionNueva instanceof Ground) {
            forest.setThing(this.row, this.column, new Ground(forest, this.row, this.column));
            this.row = nuevaFila;
            this.column = nuevaColumna;
        
            forest.setThing(this.row, this.column, (Thing)this);
        }
    } // Cierre del método
    
    /**
     * Método privado que contiene la lógica de la reproducción entre dos Ardillas.
     */
    private void newSquirrel() {
        int[][] seReproduce = {
            {this.row - 2, this.column, this.row - 1, this.column}, 
            {this.row + 2, this.column, this.row + 1, this.column}, 
            {this.row, this.column - 2, this.row, this.column - 1}, 
            {this.row, this.column + 2, this.row, this.column + 1}};
        
        for(int[] p: seReproduce) {
            int ardilla1 = p[0];
            int ardilla2 = p[1];
            
            if(ardilla1 >= 0 && ardilla1 < 16 && ardilla2 >= 0 && ardilla2 < 16) {
                Thing pareja = forest.getThing(ardilla1, ardilla2);
            
            if(pareja != null && pareja.isSquirrel()) {
                if(forest.getThing(this.row + 1, this.column) == null) {
                    new Squirrel(this.forest, this.row + 1, this.column);
                    return;
                    }
                }
            }
        }
    } // Cierre del método
    
    /**
     * Método que verifica si la cosa es una Ardilla. Sobre-escribiendo el método de
     * la Interfaz Thing.
     * 
     * @return boolean Retorna un booleano que verifica si es una Ardilla.
     */
    @Override
    public boolean isSquirrel() {
        return true;
    } // Cierre del método
} // Cierre de la clase