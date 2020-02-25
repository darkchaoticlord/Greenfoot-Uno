import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private List<Card> cards;
    private static final int CARDGAP = 60;
    
    public Player(Card[] cards) {
        this.cards = new ArrayList<>(Arrays.asList(cards));
        repaintCards();
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        GameScreen game = (GameScreen) getWorld();
        Deck deck = game.getDeck();
        if (game.isPlayerTurn() && game.canPlay()) {
            // Clicked own cards
            if (Greenfoot.mouseClicked(this)) {
                int cardIndex = cardSelected();
                
                if (cardIndex != -1) {
                    Card card = cards.get(cardIndex);
                    
                    if (game.canPlayCard(card)) {
                        cards.remove(cardIndex);
                        repaintCards();
                        game.replaceTopCard(card);
                    }
                }
            } else if (Greenfoot.mouseClicked(deck)) {
                Card card = deck.drawCard();
                
                if (game.canPlayCard(card)) {
                    game.toggleCanPlay();
                    playableDeckCard(game, card);
                } else {
                    cards.add(card);
                }
                repaintCards();
            } else {
                return;
            }
            
            GameScreen.wait(2000);
            game.toggleTurn();
        }
        
        if (cards.size() == 0) {
            Text text = new Text("Player Wins!", 60, Color.WHITE);
            game.addObject(text, game.getWidth() / 2, game.getHeight() / 2);
            Greenfoot.stop();
        }
    }
    
    public void drawCard(Deck deck, int amount) {
        for (int i = 0; i < amount; i++) {
            cards.add(deck.drawCard());
        }
        repaintCards();
    }
    
    public void playableDeckCard(GameScreen game, Card card) {
        int ypos = getY() - 80;
        
        Button playCardButton = new Button(150, 45, "Play Card", 30, Color.BLACK, 20, 6);
        game.addObject(playCardButton, getX() - 120, ypos);
        
        game.addObject(card, getX(), ypos);
        
        Button keepCardButton = new Button(150, 45, "Keep Card", 30, Color.BLACK, 20, 6);
        game.addObject(keepCardButton, getX() + 120, ypos);
        
        while (true) {
            if (Greenfoot.mouseClicked(playCardButton)) {
                game.replaceTopCard(card);
                game.toggleCanPlay();
                break;
            }
            
            if (Greenfoot.mouseClicked(keepCardButton)) {
                cards.add(card);
                game.toggleCanPlay();
                break;
            }
        }
    }
    
    private int cardSelected() {
        int mouseX = Greenfoot.getMouseInfo().getX();
        
        int topLeftX = getX() - getImage().getWidth() / 2;
        int bottomRightX = getX() + getImage().getWidth() / 2;
        
        int index = 0;
        for (int i = topLeftX; i < bottomRightX; i += CARDGAP) {
            if (mouseX >= i && mouseX <= i + CARDGAP - 10) {
                return index;
            }
            index++;
        }
        
        return -1;
    }
    
    private void repaintCards() {
        GreenfootImage image = new GreenfootImage(CARDGAP * this.cards.size(), 72);
        int x = 0;
        for (Card card: this.cards) {
            image.drawImage(card.getImage(), x, 0);
            x += CARDGAP;
        }
        setImage(image);
    }
}
