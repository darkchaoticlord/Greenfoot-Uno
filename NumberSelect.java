import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NumberSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NumberSelect extends Actor
{
    private String number;
    private boolean selected;
    
    public NumberSelect(String number, boolean selected) {
        this.number = number;
        setSelected(selected);
    }
    
    private void createImage(Color circleColor) {
        GreenfootImage image = new GreenfootImage(40, 40);
        image.setColor(circleColor);
        image.fillOval(0, 0, 40, 40);
        image.drawImage(new GreenfootImage(this.number, 30, Color.BLACK, new Color(255, 255, 255, 0)), 14, 5);
        setImage(image);
    }
    
    /**
     * Act - do whatever the NumberSelect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }   
    
    public void toggleSelected() {
        this.selected = !this.selected;
        createImage(this.selected ? Color.GREEN : Color.RED);
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        createImage(this.selected ? Color.GREEN : Color.RED);
    }
    
    public boolean isSelected() {
        return this.selected;
    }
}
