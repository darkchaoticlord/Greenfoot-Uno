import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    private static final int WIDTH = 850;
    private static final int HEIGHT = 480;
    private Button newGameButton;
    private Button quitGameButton;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Menu()
    {    
        super(WIDTH, HEIGHT, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        newGameButton = new Button(190, 45, "New Game", 30, Color.BLACK, 37, 7);
        addObject(newGameButton,429,201);
        quitGameButton = new Button(190, 45, "Quit Game", 30, Color.BLACK, 37, 7);
        addObject(quitGameButton,429,293);
        Text text = new Text("UNO", 50, Color.WHITE);
        addObject(text,423,85);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(newGameButton)) {
            Greenfoot.setWorld(new GameScreen());
        }
        
        if (Greenfoot.mouseClicked(quitGameButton)) {
            Greenfoot.stop();
        }
    }
}
