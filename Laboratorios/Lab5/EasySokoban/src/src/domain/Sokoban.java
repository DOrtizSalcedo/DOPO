package domain;

import java.util.ArrayList;

/**
 * The main class of the Sokoban game has all the logic so the game can be functional.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 25/04/2026
 */

public class Sokoban {
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int DESTINATION = 2;
    public static final int BOX = 3;
    public static final int BOX_DESTINATION = 4;
    public static final int PLAYER = 5;
    public static final int PLAYER_DESTINATION = 6;
    private int height;
    private int width;
    private int[][] board;
    private Player player;
    private ArrayList<Box> boxes;

    /**
     * The constructor for the Sokoban class.
     */
    public Sokoban() {
        height = 9;
        width = 7;
        boxes = new ArrayList<>();
        defaultSokobanLevel();
    } // Constructor Closed

    /**
     * Method that moves the player to a certain position (row, column).
     *
     * @param newRow The row in a new position.
     * @param newColumn The column in a new position.
     * @return ArrayList<int[]> A list that contains the new row and column positions.
     */
    public ArrayList<int[]> movePlayer(int newRow, int newColumn) {
        int playerMovesRow = player.getRow() + newRow;
        int playerMovesColumn = player.getColumn() + newColumn;

        if(!objectInBounds(playerMovesRow, playerMovesColumn) || board[playerMovesRow][playerMovesColumn] == WALL) {
            return getBoardState();
        }

        Box boxInCell = getBoxAt(playerMovesRow, playerMovesColumn);
        if(boxInCell != null) {
            int boxDestinationRow = playerMovesRow + newRow;
            int boxDestinationColumn = playerMovesColumn + newColumn;

            if(!objectInBounds(boxDestinationRow, boxDestinationColumn) || board[boxDestinationRow][boxDestinationColumn] == WALL
            || getBoxAt(boxDestinationRow, boxDestinationColumn) != null) {
                return getBoardState();
            }

            boxInCell.setRow(boxDestinationRow);
            boxInCell.setColumn(boxDestinationColumn);
            boxInCell.setOnDestination(board[boxDestinationRow][boxDestinationColumn] == DESTINATION);
        }

        player.setRow(playerMovesRow);
        player.setColumn(playerMovesColumn);
        return getBoardState();
    } // Method Closed

    /**
     * Method that puts where the object will be on the board.
     *
     * @return ArrayList<int[]> The position list where the object will be on the board.
     */
    public ArrayList<int[]> getBoardState() {
        ArrayList<int[]> boardState = new ArrayList<>();

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                int cellType = board[i][j];
                if(i == player.getRow() && j == player.getColumn()) {
                    cellType = (board[i][j] == DESTINATION) ? PLAYER_DESTINATION : PLAYER;
                } else {
                    Box box = getBoxAt(i, j);
                    if(box != null) {
                        cellType = (board[i][j] == DESTINATION) ? BOX_DESTINATION : BOX;
                    }
                }
                boardState.add(new int[]{i, j, cellType});
            }
        }
        return boardState;
    } // Method Closed

    /**
     * Method that counts the amount of boxes that are on a destination.
     *
     * @return int The total amount of boxes on destination.
     */
    public int countBoxesOnDestination() {
        int aux = 0;
        for(Box box : boxes) {
            if(board[box.getRow()][box.getColumn()] == DESTINATION) {
                aux++;
            }
        }
        return aux;
    } // Method Closed

    /**
     * Method that verifies if a level is completed (all boxes on a destination).
     *
     * @return boolean According to the amount of boxes on destination.
     */
    public boolean isLevelCompleted() {
        return countBoxesOnDestination() == boxes.size();
    } // Method Closed

    /**
     * Method that verifies the size of the game board.
     *
     * @return boolean Verifies, according to the value entered by the player.
     * @throws SokobanException The exceptions according to if the board is small or big.
     */
    public boolean verifyBoardSize() throws SokobanException {
        if(height < 5 || width < 5) {
            throw new SokobanException(SokobanException.BOARD_TOO_SMALL);
        }

        if(height > 20 || width > 20) {
            throw new SokobanException(SokobanException.BOARD_TOO_BIG);
        }
        return true;
    } // Method Closed

    public void changeBoardSize(int newWidth, int newHeight) throws SokobanException {
        this.width = newWidth;
        this.height = newHeight;
        verifyBoardSize();

        board = new int[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(i == 0 || i == height - 1 || j == 0 || j == width -1) {
                    board[i][j] = WALL;
                } else {
                    board[i][j] = FLOOR;
                }
            }
        }
        player = new Player(height - 2, width / 2);
        boxes = new ArrayList<>();
        if(height > 4 && height > 4) {
            boxes.add(new Box(height - 3, width / 2));
            board[1][1] = DESTINATION;
        }
    } // Method Closed

    /**
     * Method that sets the size of the game board.
     * @param width The new width of the board.
     */
    public void setWidth(int width) {
        this.width = width;
    } // Method Closed

    /**
     * Method that sets the size of the game board.
     * @param height The new height of the board.
     */
    public void setHeight(int height) {
        this.height = height;
    } // Method Closed

    /**
     * Method that gets the width of the game board.
     *
     * @return int The width of the board.
     */
    public int getWidth() {
        return width;
    } // Method Closed

    /**
     * Method that gets the height of the game board.
     *
     * @return int The height of the board.
     */
    public int getHeight() {
        return height;
    } // Method Closed

    /**
     * Private method that sets a default level (made according to the paper given).
     */
    private void defaultSokobanLevel() {
        board = new int[][] {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 2, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 2, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };

        player = new Player(7, 3);
        boxes = new ArrayList<>();
        boxes.add(new Box(6,1));
        boxes.add(new Box(6, 2));
    } // Method Closed

    /**
     * Private method that puts the box in a specific coordinate.
     *
     * @param newBoxRow The new row position.
     * @param newBoxColumn The new column position.
     * @return Box The box object where it will be at.
     */
    private Box getBoxAt(int newBoxRow, int newBoxColumn) {
        for(Box box : boxes) {
            if(box.getRow() == newBoxRow && box.getColumn() == newBoxColumn) {
                return box;
            }
        }
        return null;
    } // Method Closed

    /**
     * Private method that verifies any object can't surpass certain bound.
     *
     * @param rowObject The actual row position of the object.
     * @param columnObject The actual column position of the object.
     * @return boolean Verifies if it doesn't excess the bounds.
     */
    private boolean objectInBounds(int rowObject, int columnObject) {
        return rowObject >= 0 && rowObject < height && columnObject >= 0 && columnObject < width;
    } // Method Closed
} // Class Closed
