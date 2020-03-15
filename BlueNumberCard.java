import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlueNumberCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlueNumberCard extends BlueCard implements NumberCard {
    private int value; 
    
    public BlueNumberCard(int index, int value) {
        super(index, "Blue_" + String.valueOf(value) + ".png");
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Blue " + String.valueOf(this.value);
    }
}
