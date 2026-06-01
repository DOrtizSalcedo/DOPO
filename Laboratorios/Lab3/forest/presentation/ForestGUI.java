package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Clase que contiene la UI del simulador Forest Fire Model.
 *  
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo 
 * @version 18/03/2026
 */
public class ForestGUI extends JFrame{  
    public static final int SIDE=20;

    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private PhotoForest photo;
    private Forest theForest;
   
    /**
     * Método privado que permite construir la UI del simulador.
     */
    private ForestGUI() {
        theForest=new Forest();
        SIZE=theForest.getSize();
        prepareElements();
        prepareActions();
    } // Cierre del Constructor
    
    /**
     * Método que prepara el botón y matriz del simulador.
     */
    private void prepareElements() {
        setTitle("Schelling Forest");
        photo=new PhotoForest(this);
        ticTacButton=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(ticTacButton,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE+15,SIDE*SIZE+72)); 
        setResizable(false);
        photo.repaint();
    } // Cierre del método
    
    /**
     * Método que contiene la acción que se puede realizar con el botón "Tic-tac".
     */
    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        ticTacButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    ticTacButtonAction();
                }
            });

    } // Cierre del método
    
    /**
     * Método que contiene la lógica del botón.
     */
    private void ticTacButtonAction() {
        theForest.ticTac();
        photo.repaint();
    } // Cierre del método
    
    /**
     * Método que obtiene un bosque.
     * 
     * @return Forest El bosque que el usuario instanció.
     */
    public Forest gettheForest(){
        return theForest;
    } // Cierre del método
    
    /**
     * Método principal.
     */
    public static void main(String[] args) {
        ForestGUI cg=new ForestGUI();
        cg.setVisible(true);
    } // Cierre del método
}

/**
 * Clase que contiene la visualización de la UI (matriz, fondo blanco y tamaño).
 */
class PhotoForest extends JPanel{
    private ForestGUI gui;

    public PhotoForest(ForestGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE+10, gui.SIDE*gui.SIZE+10));         
    } // Cierre del método
    
    /**
     * Método que contiene la lógica de visualización de la matriz.
     */
    public void paintComponent(Graphics g){
        Forest theForest=gui.gettheForest();
        super.paintComponent(g);
         
        for (int c=0;c<=theForest.getSize();c++){
            g.drawLine(c*gui.SIDE,0,c*gui.SIDE,theForest.getSize()*gui.SIDE);
        }
        for (int f=0;f<=theForest.getSize();f++){
            g.drawLine(0,f*gui.SIDE,theForest.getSize()*gui.SIDE,f*gui.SIDE);
        }       
        for (int f=0;f<theForest.getSize();f++){
            for(int c=0;c<theForest.getSize();c++){
                if (theForest.getThing(f,c)!=null){
                    g.setColor(theForest.getThing(f,c).getColor());
                    if (theForest.getThing(f,c).shape()==Thing.SQUARE){                  
                        g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);   
                    }else {
                        g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                    }
                    if (theForest.getThing(f,c).isLivingThing()){
                        g.setColor(Color.red);
                        if (((LivingThing)theForest.getThing(f,c)).getEnergy()>=50){
                            g.drawString("+",gui.SIDE*c+6,gui.SIDE*f+15);
                        } else {
                            g.drawString("~",gui.SIDE*c+6,gui.SIDE*f+17);
                        }
                    }    
                }
            }
        }
    } // Cierre del método
} // Cierre de la clase