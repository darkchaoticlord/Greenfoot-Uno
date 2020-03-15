import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player extends Actor
{
    private static final int INITIAL = 7;
    public static final int CARD_WIDTH = 50, CARD_HEIGHT = 72;
    int cardGap;
    String name;
    boolean isHorizontal;
    List<Card> cards;
    
    public Player(String name, Deck deck, boolean isHorizontal, int cardGap) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.isHorizontal = isHorizontal;
        this.cardGap = cardGap;
        drawCard(deck, INITIAL);
        repaintCards();
    }
    
    public Player(String name, Deck deck) {
        this(name, deck, true, CARD_WIDTH + 10);
    }
    
    public Player(String name, Deck deck, boolean isHorizontal) {
        this(name, deck, isHorizontal, CARD_WIDTH);
    }
    
    public Player(String name, Deck deck, int cardGap) {
        this(name, deck, true, cardGap);
    }
    
    public void drawCard(Deck deck, int amount) {
        for (int i = 0; i < amount; i++) {
            cards.add(deck.drawCard());
        }
        repaintCards();
    }
    
    public void repaintCards() {
        GreenfootImage image;
        if (this.isHorizontal) {
            image = new GreenfootImage(this.cardGap * this.cards.size() + CARD_WIDTH, 72);
            int x = CARD_WIDTH / 2;
            for (Card card: this.cards) {
                if (GameScreen.showEnemyCards || isUser()) {
                    image.drawImage(card.getImage(), x, 0);
                } else {
                    image.drawImage(new GreenfootImage("Deck.png"), x, 0);
                }
                
                x += this.cardGap;
            }
        } else {
            image = new GreenfootImage(50, this.cardGap * this.cards.size() + CARD_HEIGHT);
            int y = 0;
            for (Card card: this.cards) {
                if (GameScreen.showEnemyCards || isUser()) {
                    image.drawImage(card.getImage(), 0, y);
                } else {
                    image.drawImage(new GreenfootImage("Deck.png"), 0, y);
                }
                
                y += this.cardGap;
            }
        }
        setImage(image);
    }
    
    public abstract void act();
    
    public abstract boolean isUser();
    
    public String getName() {
        return this.name;
    }
}
