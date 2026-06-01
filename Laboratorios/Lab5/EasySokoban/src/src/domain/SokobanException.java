package domain;

/**
 * Class that has exception for certain errors that can be done by the player.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 21/04/2026
 */
public class SokobanException extends Exception {
    public static final String BOARD_TOO_SMALL = "The board is too small to be created.";
    public static final String BOARD_TOO_BIG = "The board is too big to be created.";

    /**
     * The constructor for the SokobanException class.
     *
     * @param message The content message according to the different exceptions.
     */
    public SokobanException(String message) {
        super(message);
    } // Constructor Closed
} // Class Closed
