import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WildCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WildCard extends PowerCard
{
    public WildCard(int index) {
        super(index, "Wild.png");
    }
    
    /**
     * Act - do whatever the WildCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    } 
    
    @Override
    public void changeColor(String color) {
        super.changeColor(color);
        setImage("Wild_" + color + ".png");
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Wild";
    }
}
