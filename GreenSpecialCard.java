import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreenSpecialCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GreenSpecialCard extends GreenCard implements SpecialCard {
    private String power;
    
    public GreenSpecialCard(int index, String power) {
        super(index, power.equals("Draw") ? "Green_Draw.png" : (power.equals("Reverse") ? "Green_Reverse.png" : "Green_Skip.png"));
        this.power = power;
    }
    
    public String getPower() {
        return this.power;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Green " + this.power;
    }
}
