import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ColourCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Card extends Actor {
    private int index;
    String color;
    
    public Card(int index, String fileName, String color) {
        this.index = index;
        this.color = color;
        setImage(fileName);
    }  
    
    public int getIndex() {
        return this.index;
    }
    
    public String getColor() {
        return this.color;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            return ((Card) o).index == index;
        }
        return false;
    } 
    
    @Override
    public String toString() {
        return "Index: " + String.valueOf(this.index);
    }
}
