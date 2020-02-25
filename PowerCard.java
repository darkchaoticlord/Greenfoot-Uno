import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PowerCard extends Card
{
    public PowerCard(int index, String fileName) {
        super(index, fileName, null);
    }
    
    public void changeColor(String color) {
        this.color = color;
    }
    
    @Override
    public String getColor() {
        return this.color;
    }
    
    /**
     * Act - do whatever the PowerCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
}
