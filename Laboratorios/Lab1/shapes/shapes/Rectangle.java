import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle{

    public static int EDGES = 4;
    
    public int height;
    public int width;
    public int xPosition;
    public int yPosition;
    public String color;
    public boolean isVisible;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 20;
        width = 20;
        xPosition = 250;
        yPosition = 250;
        color = "green";
        isVisible = false;
    }
    
    /**
     * Create a square based on their perimeter.
     */
    public Rectangle(int perimeter){
        int sides = perimeter / 4;
        
        this.height = sides;
        this.width = sides;
        this.xPosition = 100;
        this.yPosition = 100;
        this.color = "blue";
        this.isVisible = false;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        if(newWidth >= 0){
            erase();
            height = newHeight;
            width = newWidth;
            draw();
        } else{
            System.out.println("Can't create a Rectangle with zero width.");
        }
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Returns the perimeter of a Rectangle.
     */
    public void perimeter(int newHeight, int newWidth) {
        height = newHeight;
        width = newWidth;

        int perimeterRectangle = 2 * (height + width);
        System.out.println("The perimeter of your Rectangle is: " + perimeterRectangle);
    }
    
    /**
     * Increases (+) or decreases (-) the perimeter, keeping their proportion.
     */
    public void zoom(char z) {
        if(z == '+'){
            height *= 2;
            width *= 2;
        } else if(z == '-'){
            height *= 0.5;
            width *= 0.5;
        }
    }
    
    /**
     * Moves, falling to the right (positive) or left (negative).
     */
    public void walk(int times){
        for(int i = 1; i <= Math.abs(times); i++){
            if(times > 0){
                xPosition += i;
            } else{
                xPosition -= i;
            }
        }
    }
    
    /**
     * Returns the area of a Rectangle.
     */
    public void area(int newHeight, int newWidth){
        height = newHeight;
        width = newWidth;
        
        if(height > 0 && width > 0){
            int areaRectangle = newHeight * newWidth;
            System.out.println("The area of the Rectangle is: " + areaRectangle);
        } else {
            System.out.println("The height and width must be > 0");
        }
    }
}

