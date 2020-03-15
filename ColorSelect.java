import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ColorSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ColorSelect extends Actor
{
    /**
     * Act - do whatever the ColorSelect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        GameScreen game = (GameScreen) getWorld();
        PowerCard topCard = (PowerCard) game.getTopCard();
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        
        if (game.getCurrentPlayer().isUser() && Greenfoot.mouseClicked(this) && mouseInfo != null) {
            int mouseX = mouseInfo.getX();
            int mouseY = mouseInfo.getY();
            
            int topLeftX = getX() - getImage().getWidth() / 2;
            int topLeftY = getY() - getImage().getHeight() / 2;
            int bottomRightX = getX() + getImage().getWidth() / 2;
            int bottomRightY = getY() + getImage().getHeight() / 2;
            
            if (mouseX >= topLeftX && mouseX <= getX() && mouseY >= topLeftY && mouseY <= getY()) {
                // Blue
                topCard.changeColor("Blue");
            } else if (mouseX >= getX() && mouseX <= bottomRightX && mouseY >= topLeftY && mouseY <= getY()) {
                // Red
                topCard.changeColor("Red");
            } else if (mouseX >= topLeftX && mouseX <= getX() && mouseY >= getY() && mouseY <= bottomRightY) {
                // Yellow
                topCard.changeColor("Yellow");
            } else {
                // Green
                topCard.changeColor("Green");
            }
            game.toggleCanPlay();
            game.removeObject(this);
            game.toggleTurn();
        }
    }    
}
