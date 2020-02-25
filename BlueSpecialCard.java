import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlueSpecialCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlueSpecialCard extends BlueCard implements SpecialCard
{
    private String power;
    
    public BlueSpecialCard(int index, String power) {
        super(index, power.equals("Draw") ? "Blue_Draw.png" : (power.equals("Reverse") ? "Blue_Reverse.png" : "Blue_Skip.png"));
        this.power = power;
    }
    
    /**
     * Act - do whatever the BlueSpecialCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    } 
    
    public String getPower() {
        return this.power;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Blue " + this.power;
    }
}
