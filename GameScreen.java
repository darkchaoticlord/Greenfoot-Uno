import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameScreen extends World
{
    private static final int WIDTH = 850;
    private static final int HEIGHT = 480;
    private boolean playerTurn;
    private boolean canPlay;
    private Button backButton;
    private Deck deck;
    private Player player;
    private Computer computer;
    private Card topCard;
    private Class topCardClass;
    private TurnArrow turnArrow;
    private Text turnLabel;
    /**
     * Constructor for objects of class GameScreen.
     * 
     */
    public GameScreen()
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
        backButton = new Button(100, 40, "Back", 30, Color.BLACK, 23, 5);
        addObject(backButton,60,30);
        deck = new Deck();
        addObject(deck,70,240);
        
        Card[] playerCards = new Card[7];
        for (int i = 0; i < playerCards.length; i++) {
            playerCards[i] = deck.drawCard();
        }
        
        player = new Player(playerCards);
        addObject(player,425,430);
        
        Card[] computerCards = new Card[7];
        for (int i = 0; i < computerCards.length; i++) {
            computerCards[i] = deck.drawCard();
        }
        
        computer = new Computer(computerCards);
        addObject(computer,425,50);
        
        while (topCard == null || topCard.getIndex() < 8 || topCard.getIndex() > 84) {
            topCard = deck.drawCard();
        }
        addObject(topCard, 425, 240);
        
        this.playerTurn = true;
        this.canPlay = true;
        
        turnArrow = new TurnArrow();
        turnLabel = new Text("Playing", 30, Color.WHITE);
        addObject(turnArrow, 730, player.getY());
        addObject(turnLabel, 800, player.getY());
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Menu());
        }
    }
    
    private void showTurn() {
        turnArrow.setLocation(turnArrow.getX(), this.playerTurn ? player.getY() : computer.getY());
        turnLabel.setLocation(turnLabel.getX(), this.playerTurn ? player.getY() : computer.getY());
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
            toggleCanPlay(); 
            ColorSelect selection = new ColorSelect();
            addObject(selection, topCard.getX(), topCard.getY());
        } else if (card instanceof SpecialCard) {
            // Player Special Colored Cards
            SpecialCard specialCard = (SpecialCard) card;
            
            if (specialCard.getPower().equals("Reverse")) {
                toggleTurn();
            } else if (specialCard.getPower().equals("Skip")) {
                toggleTurn();
            } else {
                if (this.playerTurn) {
                    computer.drawCard(deck, 2);
                } else {
                    player.drawCard(deck, 2);
                }
            }
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
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Computer getComputer() {
        return this.computer;
    }
    
    public Deck getDeck() {
        return this.deck;
    }
    
    public boolean isPlayerTurn() {
        return this.playerTurn;
    }
    
    public void toggleTurn() {
        this.playerTurn = !this.playerTurn;
        showTurn();
    }
    
    public boolean canPlay() {
        return this.canPlay;
    }
    
    public void toggleCanPlay() {
        this.canPlay = !this.canPlay;
    }
}
