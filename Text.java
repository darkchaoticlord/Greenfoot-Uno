import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    private int size;
    private Color textColor;
    private Color background;
    
    public Text(String text, int size, Color textColor) {
        this.size = size;
        this.textColor = textColor;
        this.background = new Color(255, 255, 255, 0);
        setImage(new GreenfootImage(text, size, textColor, background));
    }
    
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void changeText(String text) {
        setImage(new GreenfootImage(text, size, textColor, background));
    }
}
