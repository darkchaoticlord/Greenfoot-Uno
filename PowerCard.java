import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PowerCard extends Card {
    public PowerCard(int index, String fileName) {
        super(index, fileName, null);
    }
    
    public void changeColor(String color) {
        this.color = color;
    }
    
    @Override
    public String getColor() {
        return this.color;
    }
}
