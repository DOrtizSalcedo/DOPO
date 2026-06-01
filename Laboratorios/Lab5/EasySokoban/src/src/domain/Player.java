package domain;

/**
 * This class has all the logic about the behavior of the player.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 21/04/2026
 */

public class Player {
    private int row;
    private int column;

    /**
     * The constructor of the Player class.
     *
     * @param row The position in the row of the Player
     * @param column The position in the column of the Player
     */
    public Player(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Method that gets the Row position of the player.
     *
     * @return int The position in the row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Method that gets the Column position of the player.
     *
     * @return int The position in the column.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Method that sets the Row position of the player in a specific value.
     *
     * @param row The row value to set the new position.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Method that sets the Column position of the player in a specific value.
     *
     * @param column The column value to set the new position.
     */
    public void setColumn(int column) {
        this.column = column;
    }
} // Class Closed
