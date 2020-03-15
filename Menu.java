import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World {
    // Screen Resolution
    private static final int WIDTH = 850;
    private static final int HEIGHT = 480;
    
    // UI Buttons
    private Button newGameButton;
    private Button quitGameButton;
    private Button settingsButton;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Menu() {    
        super(WIDTH, HEIGHT, 1); 
        prepare();
    }

    private void prepare() {
        Text text = new Text("UNO", 80, Color.WHITE);
        addObject(text, WIDTH / 2, 90);
        newGameButton = new Button(190, 45, "New Game", 30, Color.BLACK, 37, 7);
        addObject(newGameButton, WIDTH / 2, 200);
        settingsButton = new Button(190, 45, "Settings", 30, Color.BLACK, 50, 7);
        addObject(settingsButton, WIDTH / 2, 270);
        quitGameButton = new Button(190, 45, "Quit Game", 30, Color.BLACK, 37, 7);
        addObject(quitGameButton, WIDTH / 2, 340);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(newGameButton)) {
            Greenfoot.setWorld(new GameScreen());
        }
        
        if (Greenfoot.mouseClicked(settingsButton)) {
            Greenfoot.setWorld(new Settings());
        }
        
        if (Greenfoot.mouseClicked(quitGameButton)) {
            Greenfoot.stop();
        }
    }
}
