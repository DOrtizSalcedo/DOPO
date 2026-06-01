package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Interfaz que contiene la lógica de una cosa que puede estar en el bosque.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */

public interface Thing {
    public static final int SQUARE = 2;
    public static final int ROUND = 1;
    
    /**
     * Método que obtiene la fila de una cosa.
     */
    public default int getRow() {
        return -1;
    }// Cierre del método
    
    /**
     * Método que obtiene la columna de una cosa.
     */
    public default int getColumn() {
        return -1;
    }// Cierre del método
    
    /**
     * Método que las cosas deben implementar para realizar los pasos de acuerdo a su criterio.
     */
    public void ticTac(); // Cierre del método
    
    /**
     * Método que las cosas deben implementar para determinar su forma.
     * 
     * @return int El tamaño que tiene cada cosa por defecto.
     */
    public default int shape(){
        return SQUARE;
    } // Cierre del método
    
    /**
     * Método que las cosas deben implementar para saber su color.
     * 
     * @return Color El color que representa a cada cosa.
     */
    public default Color getColor(){
      return Color.black;
    } // Cierre del método
    
    /**
     * Método que permite saber si es una sóla cosa.
     * 
     * @return boolean Para saber si es una sóla cosa.
     */
    public default boolean isOnlyThing(){
        return true;
    } // Cierre del método
    
    /**
     * Método que la clase abstracta LivingThing implementa para saber si es una cosa viviente.
     * 
     * @return boolean Determinado por defecto en falso, dado que no se sabe si sí es una cosa viviente o no.
     */
    public default boolean isLivingThing(){
        return false;
    } // Cierre del método
    
    /**
     * Método que las cosas deben implementar para saber si es una ardilla o no.
     * 
     * @return boolean Comienza por defecto en falso porque no se sabe si es una ardilla o no.
     */
    default boolean isSquirrel() {
        return false;
    } // Cierre del método
    
} // Cierre de la clase