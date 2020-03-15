import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Checkbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checkbox extends Actor
{
    private static final int SQUARE_SIZE = 20;
    private boolean checked;
    
    public Checkbox(boolean checked) {
        this.checked = checked;
        if (this.checked) {
            drawBox("green_tick.png");
        } else {
            drawBox("red_cross.png");
        }
    }
    
    /**
     * Act - do whatever the Checkbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    } 
    
    public boolean isTick() {
        return this.checked;
    }
    
    public void toggleCheckbox() {
        this.checked = !this.checked;
        
        if (this.checked) {
            drawBox("green_tick.png");
        } else {
            drawBox("red_cross.png");
        }
    }
    
    private void drawBox(String imageFile) {
        GreenfootImage checkbox = new GreenfootImage(SQUARE_SIZE, SQUARE_SIZE);
        checkbox.drawRect(1, 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);
        checkbox.drawImage(new GreenfootImage(imageFile), 2, 2);
        setImage(checkbox);
    }
}
