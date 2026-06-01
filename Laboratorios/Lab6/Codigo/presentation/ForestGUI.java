package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase que contiene la UI del simulador Forest Fire Model.
 *  
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo 
 * @version 04/05/2026
 */
public class ForestGUI extends JFrame{  
    public static final int SIDE=20;

    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private PhotoForest photo;
    private Forest theForest;
    private JMenuBar forestMenu;
    private JMenuItem itemNuevo;
    private JMenuItem itemAbrir;
    private JMenuItem itemGuardar;
    private JMenuItem itemImportar;
    private JMenuItem itemExportar;
    private JMenuItem itemSalir;
   
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
        prepareElementsMenu();
    } // Cierre del método
    
    /**
     * Método que prepara los elementos del menú.
     */
    private void prepareElementsMenu() {
        forestMenu = new JMenuBar();
        
        JMenu menu = new JMenu("Menú");
        itemNuevo = new JMenuItem("Nuevo");
        itemAbrir = new JMenuItem("Abrir");
        itemGuardar = new JMenuItem("Guardar");
        itemImportar = new JMenuItem("Importar");
        itemExportar = new JMenuItem("Exportar como");
        itemSalir = new JMenuItem("Salir");
        
        menu.add(itemNuevo);
        menu.add(itemAbrir);
        menu.add(itemGuardar);
        menu.addSeparator();
        menu.add(itemImportar);
        menu.add(itemExportar);
        menu.addSeparator();
        menu.add(itemSalir);
        forestMenu.add(menu);
        setJMenuBar(forestMenu);
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
        prepareActionsMenu();
    } // Cierre del método
    
    /**
     * Método que prepara las acciones del menú con listeners.
     */
    private void prepareActionsMenu() {
        itemNuevo.addActionListener(event -> optionNew()); 
        itemAbrir.addActionListener(event -> optionOpen());
        itemGuardar.addActionListener(event -> optionSaveAs()); 
        itemImportar.addActionListener(event -> optionImport()); 
        itemExportar.addActionListener(event -> optionExportAs()); 
        itemSalir.addActionListener(event -> optionExit()); 
    } // Cierre del método
    
    /**
     * Método que permite crear un nuevo archivo forest dentro del simulador.
     */
    private void optionNew() {
        theForest = new Forest();
        photo.repaint();
        
        JOptionPane.showMessageDialog(null, "Forest creado", "Confirmación", 
        JOptionPane.INFORMATION_MESSAGE);
    } // Cierre del método
    
    /**
     * Método que permite abrir un archivo dentro del simulador.
     */
    private void optionOpen() {
        JFileChooser seleccionarArchivo = new JFileChooser();
        int resultado = seleccionarArchivo.showOpenDialog(null);
        
        if(resultado == JFileChooser.APPROVE_OPTION) {
            try {
                this.theForest = Forest.open(seleccionarArchivo.getSelectedFile());
                photo.repaint();
            } catch (ForestException excepcion) {
                JOptionPane.showMessageDialog(null, excepcion.getMessage(),
                "Información a tener en cuenta", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } // Cierre del método
    
    /**
     * Método que permite guardar un archivo.
     */
    private void optionSaveAs() {
        JFileChooser seleccionarArchivo = new JFileChooser();
        int resultado = seleccionarArchivo.showSaveDialog(null);
        
        if(resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = seleccionarArchivo.getSelectedFile();
            try {
                theForest.saveAs(archivoSeleccionado);
            } catch (ForestException excepcion) {
                JOptionPane.showMessageDialog(null, excepcion.getMessage(),
                "Información a tener en cuenta", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } // Cierre del método
    
    /**
     * Método que permite importar un archivo.
     */
    private void optionImport() {
        JFileChooser seleccionarArchivo = new JFileChooser();
        int resultado = seleccionarArchivo.showOpenDialog(null);
        
        if(resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = seleccionarArchivo.getSelectedFile();
            try {
                theForest.importFile(archivoSeleccionado);
                photo.repaint();
                this.revalidate();
            } catch(ForestException excepcion) {
                JOptionPane.showMessageDialog(null, excepcion.getMessage());
            }
        }
    } // Cierre del método
    
    /**
     * Método que permite exportar un archivo.
     */
    private void optionExportAs() {
        JFileChooser seleccionarArchivo = new JFileChooser();
        int resultado = seleccionarArchivo.showSaveDialog(null);
        
        if(resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = seleccionarArchivo.getSelectedFile();
            try {
                theForest.exportAs(archivoSeleccionado);
            } catch (ForestException excepcion) {
                JOptionPane.showMessageDialog(null, excepcion.getMessage(),
                "Información a tener en cuenta", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } // Cierre del método
    
    /**
     * Método que permite salir del simulador.
     */
    private void optionExit() {
        System.exit(0);
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