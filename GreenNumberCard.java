import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreenNumberCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GreenNumberCard extends GreenCard implements NumberCard {
    private int value;
    
    public GreenNumberCard(int index, int value) {
        super(index, "Green_" + String.valueOf(value) + ".png");
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Green " + String.valueOf(this.value);
    }
}
