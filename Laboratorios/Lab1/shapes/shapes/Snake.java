 


/**
 * A snake that traverses searching for food to grow.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class Snake {
    private int headRow;
    private int headColumn;
    private int tailRow;
    private int tailColumn;
    private int length;
    private boolean ok;
    private boolean isVisible;
    
    /**
     * Creates a snake in a default position with a default length.
     */
    public Snake(int row, int column) {
        headRow = row;
        headColumn = column;
        tailRow = row;
        tailColumn = column;
        length = 1;
        ok = false;
        isVisible = true;
    }
    
    // Cycle 1

    /**
     * Returns the position of the head. 
     */
    public int[] head() {
        return new int[]{headRow, headColumn};
    }
    
    /**
     * Returns the position of the tail.
     */
    public int[] tail() {
        return new int[]{tailRow, tailColumn};
    }
    
    /**
     * Indicates where the snake moves (n, s, w, e).
     */
    public void move(char direction) {
        if(!ok) return;
        
        tailRow = headRow;
        tailColumn = headColumn;
        switch(direction){
            case 'n': headRow--;
            break;
            
            case 's': headRow++;
            break;
            
            case 'e': headColumn++;
            break;
            
            case 'w': headColumn--;
            break;
            
            default: ok = false;
            break;
        }
    }
    
    // Cycle 2
    
    /**
     * If the snake eats an apple, then it keeps moving and grows in a direction.
     */
    public void grow(char direction) {
        if(!ok) return;
        
        tailRow = headRow;
        tailColumn = headColumn;
        switch(direction){
            case 'n': headRow--;
            break;
            
            case 's': headRow++;
            break;
            
            case 'e': headColumn++;
            break;
            
            case 'w': headColumn--;
            break;
            
            default: ok = false;
            break;
    }
    length++;
}
    
    /**
     * Returns false if the last move can't be done.
     */
    public boolean isOK() {
        return ok;
    }
    
    // Cycle 3
    
    /**
     * Returns the length of the snake.
     */
    public int length() {
        return length;
    }
    
    /**
     * Makes the snake visible. If it was visible, does nothing.
     */
    public void makeVisible() {
        isVisible = true;
    }
    
    /**
     * Makes the snake invisible. If it was invisible, does nothing.
     */
    public void makeInvisible() {
        isVisible = false;
    }
    
}
