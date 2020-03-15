import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Write a description of class GameScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameScreen extends World {
    // Screen resolution
    private static final int WIDTH = 850;
    private static final int HEIGHT = 480;
    
    // Game parameters
    public static boolean showEnemyCards;
    public static boolean play2v2;
    public static int numOfPlayers;
    
    // Players and its properties
    private Player[] playerOrder;
    private int currentPlayer;
    private Direction direction;
    
    // User playable mode
    private boolean canPlay;
    
    // UI Element
    private Button backButton;
    
    // Extra Game Elements
    private Deck deck;
    private Card topCard;
    private Text turnLabel;
    private Text directionLabel;
    
    /**
     * Constructor for objects of class GameScreen.
     * 
     */
    public GameScreen()
    {
        super(WIDTH, HEIGHT, 1); 
        readConfig();
        prepare();
    }
    
    private void readConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Settings.CONFIG_FILE));
            
            showEnemyCards = Boolean.parseBoolean(reader.readLine().split(" ")[1]);
            play2v2 = Boolean.parseBoolean(reader.readLine().split(" ")[1]);
            numOfPlayers = Integer.parseInt(reader.readLine().split(" ")[1]);
            
            // System.out.println("Show Enemy Cards: " + String.valueOf(showEnemyCards));
            // System.out.println("Play 2 vs. 2: " + String.valueOf(play2v2));
            // System.out.println("Number of Players: " + String.valueOf(numOfPlayers));
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        backButton = new Button(100, 40, "Back", 30, Color.BLACK, 23, 5);
        addObject(backButton,60,30);
        deck = new Deck();
        addObject(deck,70,240);
        
        if (play2v2) {
            User userOne = new User("User 1", deck);
            addObject(userOne,475,430);
            
            User userTwo = new User("User 2", deck);
            addObject(userTwo,475,50);
            
            Computer computerOne = new Computer("Computer 1", deck, false, 30);
            addObject(computerOne,200,260);
            
            Computer computerTwo = new Computer("Computer 2", deck, false, 30);
            addObject(computerTwo,750,260);
            
            this.playerOrder = new Player[] {userOne, computerOne, userTwo, computerTwo};
        } else {
            User userOne;
            Computer computerOne, computerTwo, computerThree;
            
            userOne = new User("User", deck);
            addObject(userOne,475,430);
            
            computerOne = new Computer("Computer 1", deck, true, 30);
            addObject(computerOne,475,50);
            
            if (this.numOfPlayers == 2) {
                this.playerOrder = new Player[] {userOne, computerOne};
            }else if (this.numOfPlayers == 3) {
                computerTwo = new Computer("Computer 2", deck, false, 30);
                addObject(computerTwo,200,260);
                
                this.playerOrder = new Player[] {userOne, computerTwo, computerOne};
            } else if (this.numOfPlayers == 4) {
                computerTwo = new Computer("Computer 2", deck, false, 30);
                addObject(computerTwo,200,260);
                
                computerThree = new Computer("Computer 3", deck, false, 30);
                addObject(computerThree,750,260);
                
                this.playerOrder = new Player[] {userOne, computerTwo, computerOne, computerThree};
            }
        }
        this.currentPlayer = 0;
        
        while (topCard == null || topCard.getIndex() < 8 || topCard.getIndex() > 84) {
            topCard = deck.drawCard();
        }
        addObject(topCard, 475, 240);
        
        this.canPlay = true;
        
        this.direction = Direction.CW;
        
        turnLabel = new Text("Playing:\nUser" + (play2v2 ? " 1" : ""), 30, Color.WHITE);
        addObject(turnLabel, turnLabel.getImage().getWidth() / 2 + 25, HEIGHT - 30);
        
        directionLabel = new Text("Direction:\n" + this.direction.toString(), 30, Color.WHITE);
        addObject(directionLabel, WIDTH - directionLabel.getImage().getWidth() / 2 - 10, 30);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Menu());
        }
    }
    
    private void showTurn() {
        turnLabel.changeText("Playing:\n" + playerOrder[currentPlayer].getName());
        repaint();
    }
    
    public boolean canPlayCard(Card card) {
        boolean isPowerCard = card instanceof PowerCard;
        boolean doesColorMatch = card.getColor() != null && topCard.getColor() != null && card.getColor().equals(topCard.getColor());
        boolean doesNumberMatch = card instanceof NumberCard && topCard instanceof NumberCard && ((NumberCard) card).getValue() == ((NumberCard) topCard).getValue();
        boolean doesSpecialMatch = card instanceof SpecialCard && topCard instanceof SpecialCard && ((SpecialCard) card).getPower().equals(((SpecialCard) topCard).getPower());
        return isPowerCard || doesColorMatch || doesNumberMatch || doesSpecialMatch;
    }
    
    public void replaceTopCard(Card card) {
        deck.backToDeck(topCard);
        addObject(card, topCard.getX(), topCard.getY());
        topCard = card;
        repaint();
        
        if (card instanceof PowerCard) {
            // Playing Power cards (Wild or Wild Draw)
            
            if (getCurrentPlayer().isUser()) {
                toggleCanPlay();
            
                ColorSelect selection = new ColorSelect();
                addObject(selection, topCard.getX(), topCard.getY());
                
                if (topCard instanceof WildDrawCard) {
                    this.playerOrder[getNextPlayerIndex()].drawCard(deck, 4);
                }
            } else {
                String[] colors = {"Blue", "Green", "Red", "Yellow"};
                ((PowerCard) topCard).changeColor(colors[Greenfoot.getRandomNumber(colors.length)]);
            
                if (topCard instanceof WildDrawCard) {
                    this.playerOrder[getNextPlayerIndex()].drawCard(deck, 4);
                }
                
                toggleTurn();
            }
        } else if (card instanceof SpecialCard) {
            // Player Special Colored Cards
            SpecialCard specialCard = (SpecialCard) card;
            
            if (specialCard.getPower().equals("Draw")) {
                this.playerOrder[getNextPlayerIndex()].drawCard(deck, 2);
                toggleTurn();
            } else if (specialCard.getPower().equals("Reverse")) {
                this.direction = this.direction.toggleDirection();
                this.directionLabel.changeText("Direction:\n" + this.direction.toString());
            } else if (specialCard.getPower().equals("Skip")) {
                toggleTurn();
            }
            
            GameScreen.wait(500);
            
            toggleTurn();
        } else {
            toggleTurn();
        }
    }
    
    public static void wait(int microseconds) {
        try {
            Thread.sleep(microseconds);
        } catch (InterruptedException e) {
            System.out.println("Sleep stopped!");
        }
    }
    
    public Card getTopCard() {
        return this.topCard;
    }
    
    public Player getPlayer(int index) {
        return this.playerOrder[index];
    }
    
    public Player getCurrentPlayer() {
        return this.playerOrder[currentPlayer];
    }
    
    public Deck getDeck() {
        return this.deck;
    }
    
    public int getCurrentPlayerIndex() {
        return this.currentPlayer;
    }
    
    public int getNextPlayerIndex() {
        return (this.currentPlayer + 1) % this.playerOrder.length;
    }
    
    public void toggleTurn() {
        this.currentPlayer += direction.getAmount();
        
        if (this.currentPlayer == playerOrder.length) {
            this.currentPlayer = 0;
        }
        
        if (this.currentPlayer == -1) {
            this.currentPlayer = playerOrder.length - 1;
        }
        
        showTurn();
    }
    
    public boolean canPlay() {
        return this.canPlay;
    }
    
    public void toggleCanPlay() {
        this.canPlay = !this.canPlay;
    }
}
