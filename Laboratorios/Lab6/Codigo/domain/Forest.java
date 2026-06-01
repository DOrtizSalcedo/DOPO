package domain;
import java.util.*;
import java.io.*;

/**
 * Clase que contiene la lógica del bosque.
 * 
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 04/05/2026
 */

public class Forest implements Serializable {
    static private int SIZE=25;
    private Thing[][] places;
    
    /**
     * Constructor de la clase Forest.
     */
    public Forest() {
        places=new Thing[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                places[r][c]=null;
            }
        }
        someThings();
        
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                if (places[r][c] == null) {
                    new Ground(this, r, c);
                }
            }
        }
    } // Cierre del Constructor
    
    /**
     * Método que obtiene el tamaño del bosque.
     * 
     * @return int El tamaño del bosque.
     */
    public int getSize(){
        return SIZE;
    } // Cierre del método
    
    /**
     * Método que obtiene la posición de una cosa.
     * 
     * @return Thing La cosa en la posición determinada.
     */
    public Thing getThing(int r,int c){
        return places[r][c];
    } // Cierre del método
    
    /**
     * Método que pone la cosa en una posición.
     * 
     */
    public void setThing(int r, int c, Thing e){
        places[r][c]=e;
    } // Cierre del método
    
    /**
     * Método que contiene los objetos pedidos en el laboratorio.
     */
    public void someThings(){
        Tree beard = new Tree(this, 10, 10);
        Tree soul = new Tree(this, 15, 15);
        places[10][10] = beard;
        places[15][15] = soul;
        Squirrel scrat = new Squirrel(this);
        Squirrel sandy = new Squirrel(this);
        Shadow thief = new Shadow(this, scrat);
        Shadow lass = new Shadow(this, sandy);
        Pine pine1 = new Pine(this, 8, 8); 
        Water water1 = new Water(this, 9, 10);
        Fire fire1 = new Fire(this, 12, 12);
        Fire fire2 = new Fire(this, 5, 5);
        Ground g1 = new Ground(this, 5, 5);
        Ground g2 = new Ground(this, 5, 6);
        Firefighter bombero = new Firefighter(this, 2, 2);
    } // Cierre del método
    
    /**
     * Método que permite saber si dos objetos son iguales en una posición.
     * 
     * @return int El número de veces que se tiene vecinos iguales.
     */
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inForest(r,c) && places[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inForest(r+dr,c+dc) && 
                    (places[r+dr][c+dc]!=null) &&  (places[r][c].getClass()==places[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    } // Cierre del método
    
    /**
     * Método que verifica si la casilla de una matriz está vacía.
     * 
     * @return boolean Retorna verdadero o falso verificando casilla por casilla.
     */
    public boolean isEmpty(int r, int c){
        return (inForest(r,c) && places[r][c]==null);
    } // Cierre del método
    
    /**
     * Método que verifica si la cosa está dentro del rango del bosque (matríz).
     * 
     * @return boolean Retorna verdadero o falso si está en el rango o no.
     */
    private boolean inForest(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    } // Cierre del método
    
    /**
     * Método que implementa los pasos generales de una cosa en el bosque.
     */
    public void ticTac(){
        for(int filas = 0; filas < places.length; filas++) {
            for(int columnas = 0; columnas < places[filas].length; columnas++) {
                Thing thing = places[filas][columnas];
                if(thing != null) {
                    thing.ticTac();
                }
            }
        }
    } // Cierre del método
    
    /**
     * Método que lanza la excepción para la entrada.
     * 
     * @param entrada El archivo que se quiere abrir.
     */
    public void open00(File entrada) throws ForestException {
        throw new ForestException("Opción open en construcción. Archivo " + entrada.getName());
    } // Cierre del método

    /**
     * Método que lanza la excepción para el guardado.
     * 
     * @param guardar El archivo que se quiere guardar.
     */
    public void saveAs00(File guardar) throws ForestException {
        throw new ForestException("Opción Salvar como en construcción. Archivo " + guardar.getName());
    } // Cierre del método
    
    /**
     * Método que abre un archivo .dat.
     * 
     * @return Forest Un objeto Forest.
     * @param entrada El archivo que se quiere abrir.
     */
    public static Forest open(File entrada) throws ForestException { 
        if(entrada == null || !entrada.exists()) {
            throw new ForestException("Archivo inexistente");
        }
        
        try (ObjectInputStream entradaArchivo = new ObjectInputStream (new FileInputStream(entrada))){
            return (Forest) entradaArchivo.readObject();
        } catch (Exception excepcion) {
            throw new ForestException(excepcion.getMessage());
        }
    } // Cierre del método 

    /**
     * Método que guarda un archivo .dat.
     * 
     * @param guardar El archivo que se quiere guardar.
     */
    public void saveAs(File guardar) throws ForestException {
        if(guardar == null) {
            throw new ForestException("El archivo no se puede guardar porque no existe");
        }
        try (ObjectOutputStream salida = new ObjectOutputStream (new FileOutputStream(guardar))){
            salida.writeObject(this);
        } catch (IOException excepcion) {
            throw new ForestException("Error al guardar el archivo: " + excepcion.getMessage());
        }
    } // Cierre del método

    /**
     * Método que lanza la excepción al importar.
     * 
     * @param El archivo que se va a importar.
     */
    public void import00(File importar) throws ForestException { 
        throw new ForestException("Opción importar en construcción\n" + "Archivo: " + importar.getName());
    } // Cierre del método 

    /**
     * Método que lanza la excepción al exportar.
     * 
     * @param exportar El archivo que se va a exportar.
     */
    public void exportAs00(File exportar) throws ForestException {
        throw new ForestException("Opción exportar en construcción\n" + "Archivo: " + exportar.getName());
    } // Cierre del método
    
    /**
     * Método que exporta a un archivo de texto el estado actual del simulador.
     * 
     * @param exportar El archivo de texto que se va a convertir.
     */
    public void exportAs(File exportar) throws ForestException {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(exportar))) {
            escritor.println("Tamaño del bosque actual: " + this.getSize());
            
            for(int t = 0; t < getSize(); t++) {
                for(int n = 0; n < getSize(); n++) {
                    Thing cosa = getThing(t, n);
                    if(cosa != null) {
                        escritor.println(cosa.getClass().getSimpleName() + " " + t + " " + n);
                        }
                    }
                }
            } catch(IOException excepcion) {
                throw new ForestException(excepcion.getMessage());
            }
    } // Cierre del método
    
    /**
     * Método que importa de un archivo de texto.
     * 
     * @param importar El archivo que se va a leer en el simulador.
     */
    public void importFile(File importar) throws ForestException {
        try (BufferedReader lector = new BufferedReader(new FileReader(importar))) {
            String linea;
            linea = lector.readLine();
            
            while((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if(linea.isEmpty()) continue;
                try {
                    String[] espacios = linea.split(" ");
                    String tipo = espacios[0];
                    int leerFila = Integer.parseInt(espacios[1]);
                    int leerColumna = Integer.parseInt(espacios[2]);
                    switch(tipo) {
                        case "Tree":
                            new Tree(this, leerFila, leerColumna);
                            break;
                        case "Squirrel":
                            new Squirrel(this, leerFila, leerColumna);
                            break;
                        case "Firefighter":
                            new Firefighter(this, leerFila, leerColumna);
                            break;
                        case "Water":
                            new Water(this, leerFila, leerColumna);
                            break;
                        case "Fire":
                            new Fire(this, leerFila, leerColumna);
                            break;
                        case "Pine":
                            new Pine(this, leerFila, leerColumna);
                            break;
                    }
                } catch (NumberFormatException excepcion) {
                    throw new ForestException(excepcion.getMessage());
                }
            }
        } catch (IOException excepcion) {
            throw new ForestException(excepcion.getMessage());
        }
    } // Cierre del método
} // Cierre de la clase