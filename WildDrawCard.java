import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WildDrawCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WildDrawCard extends PowerCard {
    public WildDrawCard(int index) {
        super(index, "Wild_Draw.png");
    }
    
    @Override
    public void changeColor(String color) {
        super.changeColor(color);
        setImage("Wild_Draw_" + color + ".png");
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Card: WildDraw";
    }
}
