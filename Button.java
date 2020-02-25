import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    public Button(int width, int height, String text, int textSize, Color textColor, int xOffset, int yOffset) {
        GreenfootImage image = new GreenfootImage("blue_button01.png");
        image.scale(width, height);
        image.drawImage(new GreenfootImage(text, textSize, textColor, new Color(255, 255, 255, 0)), xOffset, yOffset);
        setImage(image);
    }
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
