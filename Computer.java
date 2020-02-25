import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Write a description of class Computer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Computer extends Actor
{
    private List<Card> cards;
    private static final int CARDGAP = 30;
    
    public Computer(Card[] cards) {
        this.cards = new ArrayList<>(Arrays.asList(cards));
        repaintCards();
    }
    
    private void repaintCards() {
        GreenfootImage image = new GreenfootImage(CARDGAP * this.cards.size() + CARDGAP, 72);
        int x = 0;
        for (Card card: this.cards) {
            // image.drawImage(new GreenfootImage("Deck.png"), x, 0);
            image.drawImage(card.getImage(), x, 0);
            x += CARDGAP;
        }
        setImage(image);
    }
    
    /**
     * Act - do whatever the Computer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        GameScreen game = (GameScreen) getWorld();
        
        if (!game.isPlayerTurn() && game.canPlay()) {
            List<Card> playableCards = new ArrayList<>();
            for (Card card : this.cards) {
                if (game.canPlayCard(card)) {
                    playableCards.add(card);
                }
            }
            
            if (playableCards.size() == 0) {
                drawCard(game.getDeck(), 1);
                repaintCards();
            } else {
                Card playing = playableCards.get(Greenfoot.getRandomNumber(playableCards.size()));
                cards.remove(playing);
                repaintCards();
                game.replaceTopCard(playing);
            }
            
            GameScreen.wait(2000);
            game.toggleTurn();
        }
        
        if (cards.size() == 0) {
            Text text = new Text("Computer Wins!", 60, Color.WHITE);
            game.addObject(text, game.getWidth() / 2, game.getHeight() / 2);
            Greenfoot.stop();
        }
    }  
    
    public void drawCard(Deck deck, int amount) {
        for (int i = 0; i < amount; i++) {
            Card card = deck.drawCard();
            cards.add(card);
        }
        repaintCards();
    }
    
    public Card getCard() {
        return this.cards.get(Greenfoot.getRandomNumber(this.cards.size()));
    }
}
