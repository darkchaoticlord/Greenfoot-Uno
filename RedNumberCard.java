import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedNumberCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedNumberCard extends RedCard implements NumberCard {
    private int value;
    
    public RedNumberCard(int index, int value) {
        super(index, "Red_" + String.valueOf(value) + ".png");
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Red " + String.valueOf(this.value);
    }
}
