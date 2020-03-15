import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
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
    private NumberSelect[] playerNumbers;
    private Checkbox showEnemyCardsBox;
    private Checkbox play2v2Box;
    
    private Text numberOfPlayersLabel;
    private int numberOfPlayers;
    
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
        
        numberOfPlayersLabel = new Text("Total number of players:", 30, Color.WHITE);
        addObject(numberOfPlayersLabel, WIDTH / 4, 290);
        
        playerNumbers = new NumberSelect[] {new NumberSelect("2", true), new NumberSelect("3", false), new NumberSelect("4", false)};
        for (int i = 0; i < playerNumbers.length; i++) {
            addObject(playerNumbers[i], 60 * i + 580, 290);
        }
        
        readConfig();
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(backButton)) {
            saveConfig();
            Greenfoot.setWorld(new Menu());
        }
        
        for (int i = 0; i < playerNumbers.length; i++) {
            if (Greenfoot.mouseClicked(playerNumbers[i])) {
                selectPlayerNumber(i);
            }
        }
        
        if (Greenfoot.mouseClicked(showEnemyCardsBox)) {
            showEnemyCardsBox.toggleCheckbox();
        }
        
        if (Greenfoot.mouseClicked(play2v2Box)) {
            play2v2Box.toggleCheckbox();
            if (play2v2Box.isTick()) {
                removeObject(numberOfPlayersLabel);
                for (int i = 0; i < playerNumbers.length; i++) {
                    removeObject(playerNumbers[i]);
                }
            } else {
                addObject(numberOfPlayersLabel, WIDTH / 4, 290);
                for (int i = 0; i < playerNumbers.length; i++) {
                    addObject(playerNumbers[i], 60 * i + 580, 290);
                }
            }
        }
    }
    
    private void selectPlayerNumber(int num) {
        playerNumbers[num].setSelected(true);
        playerNumbers[(num + 1) % 3].setSelected(false);
        playerNumbers[(num + 2) % 3].setSelected(false);
                
        this.numberOfPlayers = num + 2;
    }
    
    private void readConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Settings.CONFIG_FILE));
            
            if (Boolean.parseBoolean(reader.readLine().split(" ")[1])) {
                showEnemyCardsBox.toggleCheckbox();
            }
            
            if (Boolean.parseBoolean(reader.readLine().split(" ")[1])) {
                play2v2Box.toggleCheckbox();
            }
            
            selectPlayerNumber(Integer.parseInt(reader.readLine().split(" ")[1]) - 2);
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveConfig() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE));
            
            writer.write("ShowEnemyCard: " + String.valueOf(showEnemyCardsBox.isTick()));
            writer.newLine();
            writer.write("Play2v2: " + String.valueOf(play2v2Box.isTick()));
            writer.newLine();
            writer.write("NumberOfPlayers: " + String.valueOf(numberOfPlayers));
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
