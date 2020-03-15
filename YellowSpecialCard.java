import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class YellowSpecialCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YellowSpecialCard extends YellowCard implements SpecialCard {
    private String power;
    
    public YellowSpecialCard(int index, String power) {
        super(index, power.equals("Draw") ? "Yellow_Draw.png" : (power.equals("Reverse") ? "Yellow_Reverse.png" : "Yellow_Skip.png"));
        this.power = power;
    }
    
    public String getPower() {
        return this.power;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Yellow " + this.power;
    }
}
