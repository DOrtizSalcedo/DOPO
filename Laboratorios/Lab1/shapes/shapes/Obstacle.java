
import java.util.Random;

/**
 * An inconvenience that tries to make the player lose.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class Obstacle {
    private Rectangle obstacle;
    private Random randomPlace;
    
    /**
     * Makes an obstacle in the field.
     */
    public Obstacle() {
        Rectangle obstacle = new Rectangle();
        Random randomPlace = new Random();
        int randomHorizontal = randomPlace.nextInt(10, 100);
        int randomVertical = randomPlace.nextInt(30, 200);
        obstacle.changeSize(40, 40);
        obstacle.makeVisible();
        obstacle.changeColor("black");
        obstacle.moveHorizontal(randomHorizontal);
        obstacle.moveVertical(randomVertical);
    }
}