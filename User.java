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
public class User extends Player
{
    public User(String name, Deck deck) {
        super(name, deck);
    }
    
    public User(String name, Deck deck, int cardGap) {
        super(name, deck, cardGap);
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() 
    {
        GameScreen game = (GameScreen) getWorld();
        Deck deck = game.getDeck();
        
        if (game.getCurrentPlayer().equals(this) && game.canPlay()) {
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
                
                game.toggleTurn();
            } else {
                return;
            }
        }
        
        if (cards.size() == 0) {
            Text text = new Text("Player Wins!", 60, Color.BLACK);
            game.addObject(text, game.getWidth() / 2, game.getHeight() / 2);
            Greenfoot.stop();
        }
    }
    
    @Override
    public boolean isUser() {
        return true;
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
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        
        if (mouseInfo != null) {
            int mouseX = mouseInfo.getX();
            int topLeftX = getX() - getImage().getWidth() / 2;
            int bottomRightX = getX() + getImage().getWidth() / 2;
            
            int index = 0;
            for (int i = topLeftX; i < bottomRightX; i += this.cardGap) {
                if (mouseX >= i && mouseX <= i + this.cardGap - 10) {
                    return index;
                }
                index++;
            }
        }
        
        return -1;
    }
}
