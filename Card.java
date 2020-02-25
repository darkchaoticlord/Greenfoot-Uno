import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ColourCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Card extends Actor
{
    private int index;
    String color;
    
    public Card(int index, String fileName, String color) {
        this.index = index;
        this.color = color;
        setImage(fileName);
    }
    
    /**
     * Act - do whatever the ColourCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
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
