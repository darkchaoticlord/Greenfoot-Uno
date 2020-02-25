import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class YellowNumberCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YellowNumberCard extends YellowCard implements NumberCard
{
    private int value;
    
    public YellowNumberCard(int index, int value) {
        super(index, "Yellow_" + String.valueOf(value) + ".png");
        this.value = value;
    }
    
    /**
     * Act - do whatever the YellowNumberCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  
    
    public int getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Yellow " + String.valueOf(this.value);
    }
}
