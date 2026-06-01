package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import domain.*;

/**
 * The UI of the Sokoban game. Has everything so the game can be visible
 * to the player
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 25/04/2026
 */

public class SokobanGUI extends JFrame {
    private JMenuBar sokobanMenu;
    private JMenuItem itemNew;
    private JMenuItem itemOpen;
    private JMenuItem itemSave;
    private JMenuItem itemExit;
    private JMenuItem itemChangeBoxColor;
    private Color boxColor = Color.ORANGE;
    private JMenuItem itemChangeDestinationColor;
    private Color destinationColor = Color.PINK;
    private JMenuItem itemChangeBoardSize;
    private JButton restartButton;
    private Sokoban sokoban;
    private JPanel[] cells;

    /**
     * The constructor of the presentation.SokobanGUI class
     */
    private SokobanGUI() {
        sokoban = new Sokoban();
        prepareElements();
        prepareActions();
        renderBoard(sokoban.getBoardState());
    } // Method closed

    /**
     * Method that prepares the components of the interface.
     */
    private void prepareElements() {
        setTitle("EasySokoban");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int windowWidth = width / 4;
        int windowHeight = height / 4;
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        prepareElementsSokobanMenu();
        prepareElementsBoard();
        refresh();
    } // Method Closed

    /**
     * Method that prepares the elements of the menu.
     */
    private void prepareElementsSokobanMenu() {
        sokobanMenu = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        itemNew = new JMenuItem("New");
        itemOpen = new JMenuItem("Open");
        itemSave = new JMenuItem("Save");
        itemExit = new JMenuItem("Exit");

        menu.add(itemNew);
        menu.add(itemOpen);
        menu.add(itemSave);
        menu.add(itemExit);

        JMenu features = new JMenu("Features");
        itemChangeBoardSize = new JMenuItem("Change Board Size");
        itemChangeBoxColor = new JMenuItem("Change Box Color");
        itemChangeDestinationColor = new JMenuItem("Change Destination Color");

        features.add(itemChangeBoardSize);
        features.add(itemChangeBoxColor);
        features.add(itemChangeDestinationColor);

        restartButton = new JButton("Restart Game");

        sokobanMenu.add(menu);
        sokobanMenu.add(features);
        sokobanMenu.add(restartButton);

        setJMenuBar(sokobanMenu);
    } // Method Closed

    /**
     * Method that prepares the elements of the sokoban board, the board is a gridlayout
     */
    private void prepareElementsBoard() {
        int width = sokoban.getWidth();
        int height = sokoban.getHeight();

        setLayout(new GridLayout(height, width));
        cells = new JPanel[width * height];

        for(int i = 0; i < (height * width); i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cells[i] = cell;
            add(cell);
        }
        setVisible(true);
    } // Method Closed

    /**
     * Method that defines the color of a sokoban object (player, box, destination, and more)
     *
     * @return Color The color of the object (follows the same colors given in the paper)
     */
    private Color sokobanObjectColors(int cellType) {
        switch(cellType) {
            case Sokoban.WALL:
                return Color.DARK_GRAY;
            case Sokoban.DESTINATION:
                return destinationColor;
            case Sokoban.BOX:
                return boxColor;
            case Sokoban.BOX_DESTINATION:
                return new Color(111, 78,55);
            case Sokoban.PLAYER:
                return Color.BLACK;
            case Sokoban.PLAYER_DESTINATION:
                return Color.BLUE;
            default:
                return Color.WHITE;
        }
    } // Method Closed

    /**
     * Method that changes the color of the boxes.
     */
    private void changeBoxColor() {
        Color color = JColorChooser.showDialog(this, "Choose a color of your preference", boxColor);
        if (color != null) {
            boxColor = color;
            refresh();
            renderBoard(sokoban.getBoardState());
        }
    } // Method Closed

    /**
     * Method that changes the color of the destinations.
     */
    private void changeDestinationColor() {
        Color color = JColorChooser.showDialog(this, "Choose a color of your preference", boxColor);
        if (color != null) {
            destinationColor = color;
            refresh();
            renderBoard(sokoban.getBoardState());
        }
    } // Method Closed

    /**
     * Method that resets the board.
     */
    private void refresh() {
        getContentPane().removeAll();
        prepareElementsBoard();
        revalidate();
        repaint();
    } // Method Closed

    /**
     * Prepares the actions that can be done by the player.
     */
    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitFile();
            }
        });
        prepareActionsMenu();
        setUpKeyBindings();
    } // Method Closed

    /**
     * Method that prepares the actions of the menu.
     */
    private void prepareActionsMenu() {
        itemExit.addActionListener(event -> exitFile());
        itemOpen.addActionListener(event -> openFile());
        itemSave.addActionListener(event -> saveFile());
        itemChangeBoxColor.addActionListener(event -> changeBoxColor());
        itemChangeDestinationColor.addActionListener(event -> changeDestinationColor());
        restartButton.addActionListener(event -> restartGame());
        itemChangeBoardSize.addActionListener(event -> changeBoardSize());
    } // Method Closed

    /**
     * Method that closes the application.
     */
    private void exitFile() {
        int confirm = JOptionPane.showConfirmDialog(
                this, "Do you want to exit the application?",
                "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
        );
        if(confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    } // Method Closed

    /**
     * Method that opens a file inside the application (NOT IMPLEMENTED YET).
     */
    private void openFile() {
        JFileChooser selectFile = new JFileChooser();
        int result = selectFile.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = selectFile.getSelectedFile();
            String fileName = selectedFile.getName();
            String partial_message = "Action: Open File\n" + "Selected File: "
                    + fileName + "\n" + "..This function is not available yet";

            JOptionPane.showMessageDialog(null, partial_message, "Warning Message", JOptionPane.INFORMATION_MESSAGE);
        }
    } // Method Closed

    /**
     * Method that its goal is saving a file inside the application (NOT IMPLEMENTED YET).
     */
    private void saveFile() {
        JFileChooser selectFile = new JFileChooser();
        int result = selectFile.showSaveDialog(null);

        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = selectFile.getSelectedFile();
            String fileName = selectedFile.getName();
            String partial_message = "Action: Save File\n" + "Selected File: "
                    + fileName + "\n" + "..This function is not available yet";

            JOptionPane.showMessageDialog(null, partial_message,
                    "Warning Message", JOptionPane.INFORMATION_MESSAGE);
        }
    } // Method Closed

    /**
     * Method that translates the keys from the keyboard to actions.
     */
    private void setUpKeyBindings() {
        InputMap inpMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actMap = getRootPane().getActionMap();

        inpMap.put(KeyStroke.getKeyStroke("UP"),    "moveUp");
        inpMap.put(KeyStroke.getKeyStroke("DOWN"),  "moveDown");
        inpMap.put(KeyStroke.getKeyStroke("LEFT"),  "moveLeft");
        inpMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        actMap.put("moveUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                renderBoard(sokoban.movePlayer(-1, 0));
            }
        });
        actMap.put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                renderBoard(sokoban.movePlayer(1, 0));
            }
        });
        actMap.put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                renderBoard(sokoban.movePlayer(0, -1));
            }
        });
        actMap.put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                renderBoard(sokoban.movePlayer(0, 1));
            }
        });
    } // Method Closed

    /**
     * Paints every cell according to the board state.
     *
     * @param state list of the position of the elements.
     */
    private void renderBoard(ArrayList<int[]> state) {
        int cols = sokoban.getWidth();
        for (int[] cell : state) {
            int row      = cell[0];
            int col      = cell[1];
            int cellType = cell[2];
            cells[row * cols + col].setBackground(sokobanObjectColors(cellType));
        }
        revalidate();
        repaint();
    } // Method Closed

    // Bono: Reiniciar el juego y Cambiar el tamaño del tablero del juego.

    /**
     * Method that restarts a Sokoban game.
     */
    private void restartGame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to restart the game?",
                 "Restarting Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if(confirm == JOptionPane.YES_OPTION) {
            sokoban = new Sokoban();
            renderBoard(sokoban.getBoardState());
        }
    } // Method Closed

    /**
     * Method that changes the size of the board.
     */
    private void changeBoardSize() {
        String entryWidth = JOptionPane.showInputDialog(this, "Width:");
        String entryHeight = JOptionPane.showInputDialog(this, "Height:");

        if(entryWidth != null && entryHeight != null) {
            try {
                int width = Integer.parseInt(entryWidth);
                int height = Integer.parseInt(entryHeight);
                sokoban.changeBoardSize(width, height);
                sokoban.verifyBoardSize();
                refresh();
                renderBoard(sokoban.getBoardState());
            } catch (SokobanException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Invalid board size :(", JOptionPane.ERROR_MESSAGE);
            }
        }
    } // Method Closed

    /**
     * Main method instances a SokobanGUI.
     * @param args the arguments.
     */
    public static void main(String args[]) {
        SokobanGUI gui = new SokobanGUI();
        gui.setVisible(true);
    } // Method Closed
} // Class Closed