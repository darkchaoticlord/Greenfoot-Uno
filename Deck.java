import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deck extends Actor
{
    private static final int TOTALCARDS = 108;
    public static final Class[] NUMBERCARDS = {BlueNumberCard.class, RedNumberCard.class, GreenNumberCard.class, YellowNumberCard.class};
    public static final Class[] SPECIALCARDS = {BlueSpecialCard.class, RedSpecialCard.class, GreenSpecialCard.class, YellowSpecialCard.class};
    private Card[] cards;
    private List<Card> cardsInUse;
    
    public Deck() {
        createDeck();
    }
    
    private void createDeck() {
        this.cards = new Card[TOTALCARDS];
        
        int i;
        
        // Creating Wild Cards
        for (i = 0; i < 4; i++) {
            cards[i] = new WildCard(i);
        }
        
        // Creating Wild Draw Cards
        for (; i < 8; i++) {
            cards[i] = new WildDrawCard(i);
        }
        
        
        try {
            // Creating Colored Number Cards
            for (Class numberCard: NUMBERCARDS) {
                cards[i] = (Card) numberCard.getConstructor(int.class, int.class).newInstance(i, 0);
                i++;
                
                for (int j = 1; j < 10; j++) {
                    cards[i] = (Card) numberCard.getConstructor(int.class, int.class).newInstance(i, j);
                    cards[i + 1] = (Card) numberCard.getConstructor(int.class, int.class).newInstance(i + 1, j);
                    i += 2;
                }
            }
            
            // Creating Colored Power Cards
            String[] powers = {"Reverse", "Skip", "Draw"};
            for (Class specialCard: SPECIALCARDS) {
                for (String power: powers) {
                    cards[i] = (Card) specialCard.getConstructor(int.class, String.class).newInstance(i, power);
                    cards[i + 1] = (Card) specialCard.getConstructor(int.class, String.class).newInstance(i + 1, power);
                    i += 2;
                }
            }
            
            this.cardsInUse = new ArrayList<>();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    /**
     * Act - do whatever the Deck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  
    
    public Card drawCard() {
        while (true) {
            Card card = this.cards[Greenfoot.getRandomNumber(TOTALCARDS)];
            if (!cardsInUse.contains(card)) {
                cardsInUse.add(card);
                return card;
            }
        }
    }
    
    public void backToDeck(Card card) {
        cardsInUse.remove(card);
    }
}
