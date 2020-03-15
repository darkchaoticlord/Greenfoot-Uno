import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WildCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WildCard extends PowerCard {
    public WildCard(int index) {
        super(index, "Wild.png");
    }
    
    @Override
    public void changeColor(String color) {
        super.changeColor(color);
        setImage("Wild_" + color + ".png");
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: Wild";
    }
}
