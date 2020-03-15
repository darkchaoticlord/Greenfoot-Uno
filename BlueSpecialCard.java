import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlueSpecialCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlueSpecialCard extends BlueCard implements SpecialCard {
    private String power;
    
    public BlueSpecialCard(int index, String power) {
        super(index, power.equals("Draw") ? "Blue_Draw.png" : (power.equals("Reverse") ? "Blue_Reverse.png" : "Blue_Skip.png"));
        this.power = power;
    }
    
    public String getPower() {
        return this.power;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Blue " + this.power;
    }
}
