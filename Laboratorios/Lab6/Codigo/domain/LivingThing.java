package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Clase que contiene la lógica de una cosa viviente dentro del bosque.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */

public abstract class LivingThing implements Serializable {
    
    protected int years;
    private int energy;

    /**
     * Constructor de la clase LivingThing.
     * 
     */
    public LivingThing(){
        energy=100;
        years=0;
    } // Cierre del método
    
    /** 
     * Método que pone cierta cantidad de energía a la cosa.
     */
    protected void setEnergy(int amount) {
        energy = amount;
    }

    /**
     * Método que contiene la lógica de los pasos de una cosa viviente.
     * 
     * @return boolean Verifica que cada vez se oprima el botón "Tic-tac", la energía disminuya hasta morir, retornando false.
     */
    final boolean step(){
        boolean ok=false;
        if (energy>=1){
            energy-=1;
            ok=true;
        }
        return ok;
    } // Cierre del método
    
     /**
      * Método que obtiene la energía de una cosa viviente.
      * 
      * @return int La cantidad de energía de la cosa viviente.
     */   
    public final int getEnergy(){
        return energy;
    } // Cierre del método

    /**
     * Método que verifica si la cosa es una cosa viviente o no.
     * 
     * @return boolean Retorna verdadero o falso si es una cosa viviente o no.
     */
    public final boolean isLivingThing(){
        return true;
    } // Cierre del método 
} // Cierre de la clase