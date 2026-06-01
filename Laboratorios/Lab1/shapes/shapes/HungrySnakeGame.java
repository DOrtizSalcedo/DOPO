
import javax.swing.*;

/**
 * The player controls a snake, collecting fruits and making the snake growing while avoiding obstacles and clashing with itself.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class HungrySnakeGame {
    private Snake snake;
    private HungrySnakeGame game;
    private int row;
    private int column;
    private Rectangle boardGame;
    private Obstacle obs;
    
    // Cycle 1
    /**
     * Creates the Hungry Snake Game.
     */
    
    public HungrySnakeGame(int row, int column) {
        Rectangle boardGame = new Rectangle();
        boardGame.changeSize(600, 600);
        boardGame.makeVisible();
        boardGame.changeColor("green");
        Snake s = new Snake(this.row, this.column);
        this.snake = s;
        Obstacle ob = new Obstacle();
        this.obs = ob;
    }
    
    
    /**
     * Returns the length of the snake.
     */
    
    public void gameState() {
        JOptionPane.showMessageDialog(null, "Snake length: " + snake.length(), "HungrySnakeGame", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Cycle 2
    
    /**
     * Lets the snake move in a row and in a column (n, s, w, e).
     */
    
    public void newSnakeMove(char direction) {
        snake.move(direction);
        }
    
    /**
     * Shows a error message if the player did a different stated direction.
     */
    
    public void moveError(char direction) {
        if(!validDirection(direction)) {
            JOptionPane.showMessageDialog(null, "Wrong declaration of direction.", "HungrySnakeGame", JOptionPane.ERROR_MESSAGE);
        }
        snake.move(direction);
    }
    
    /**
     * Validates if it's the right direction. 
     */
    private boolean validDirection(char m) {
        return m == 'n' || m == 's' || m == 'e' || m == 'w';
    }
    
    // Cycle 3
    
    /**
     * Shows a message indicating if the player lost the game.
     */
    
    public void lostMessage() {
        if(snake.isOK() == false){
            JOptionPane.showMessageDialog(null, "You lost! Try again, if you want to beat your score!", "HungrySnakeGame", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Resets the game, making the snake to be in a new position.
     */
    public void resetHungrySnakeGame(int row, int column) {
        game = new HungrySnakeGame(this.row, this.column);
    }
}