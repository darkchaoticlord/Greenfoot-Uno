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
        
        if (game.isPlayerTurn()) {
            if (Greenfoot.mouseClicked(this)) {
                int mouseX = Greenfoot.getMouseInfo().getX();
                int mouseY = Greenfoot.getMouseInfo().getY();
                
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
            } else {
                return;
            }
        } else {
            String[] colors = {"Blue", "Green", "Red", "Yellow"};
            topCard.changeColor(colors[Greenfoot.getRandomNumber(colors.length)]);
        }
        
        if (topCard instanceof WildDrawCard) {
            if (game.isPlayerTurn()) {
                game.getComputer().drawCard(game.getDeck(), 4);
            } else {
                game.getPlayer().drawCard(game.getDeck(), 4);
            }
        }
        
        game.removeObject(this);
    }    
}
