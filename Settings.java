import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Write a description of class Settings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Settings extends World
{
    private static final int WIDTH = 850;
    private static final int HEIGHT = 480;
    public static final String CONFIG_FILE = "config.txt";
    private Button backButton;
    private Checkbox showEnemyCardsBox;
    private Checkbox play2v2Box;
    /**
     * Constructor for objects of class Settings.
     * 
     */
    public Settings()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1); 
        prepare();
    }
    
    private void prepare() {
        backButton = new Button(100, 40, "Back", 30, Color.BLACK, 23, 5);
        addObject(backButton,60,30);
        
        Text text = new Text("Settings", 50, Color.WHITE);
        addObject(text, WIDTH / 2, 80);
        
        Text showEnemyCards = new Text("Show Enemy Cards", 30, Color.WHITE);
        addObject(showEnemyCards, WIDTH / 4, 150);
        
        showEnemyCardsBox = new Checkbox(false);
        addObject(showEnemyCardsBox, (3 * WIDTH) / 4, 150);
        
        Text play2v2 = new Text("Play 2 vs. 2 UNO", 30, Color.WHITE);
        addObject(play2v2, WIDTH / 4, 220);
        
        play2v2Box = new Checkbox(false);
        addObject(play2v2Box, (3 * WIDTH) / 4, 220);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(backButton)) {
            saveConfig();
            Greenfoot.setWorld(new Menu());
        }
    }
    
    private void saveConfig() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE));
            writer.write("ShowEnemyCard: " + (showEnemyCardsBox.isTick() ? "true" : "false"));
            writer.newLine();
            writer.write("Play2v2: " + (play2v2Box.isTick() ? "true" : "false"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
