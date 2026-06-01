package domain;

/**
 * Class that has all the logic of a box.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 21/04/2026
 */

public class Box {
    private int row;
    private int column;
    private boolean onDestination;

    /**
     * The constructor of the Box class
     *
     * @param row The row position of the box.
     * @param column The column position of the box.
     */
    public Box(int row, int column) {
        this.row = row;
        this.column = column;
        this.onDestination = false;
    } // Method Closed

    /**
     * Method that gets the row position of the box.
     *
     * @return int The row position of the box.
     */
    public int getRow() {
        return row;
    } // Method Closed

    /**
     * Method that gets the column position of the box.
     *
     * @return int The column position of the box.
     */
    public int getColumn() {
        return column;
    } // Method Closed

    /**
     * Method that verifies if the box is on the destination.
     *
     * @return boolean According to the value of the box.
     */
    public boolean isOnDestination() {
        return onDestination;
    } // Method Closed

    /**
     * Method that sets the row position of the box.
     * @param row The new row position of the box.
     */
    public void setRow(int row) {
        this.row = row;
    } // Method Closed

    /**
     * Method that sets the column position of the box.
     * @param column The new column position of the box.
     */
    public void setColumn(int column) {
        this.column = column;
    } // Method Closed

    /**
     * Method that sets the value of the destination if the box is on it.
     *
     * @param onDestination The boolean value if the box is on the destination.
     */
    public void setOnDestination(boolean onDestination) {
        this.onDestination = onDestination;
    } // Method Closed
} // Class Closed
