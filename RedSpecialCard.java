import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedSpecialCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedSpecialCard extends RedCard implements SpecialCard {
    private String power;
    
    public RedSpecialCard(int index, String power) {
        super(index, power.equals("Draw") ? "Red_Draw.png" : (power.equals("Reverse") ? "Red_Reverse.png" : "Red_Skip.png"));
        this.power = power;
    }
    
    public String getPower() {
        return this.power;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Red " + this.power;
    }
}
