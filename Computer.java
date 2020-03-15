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
public class Computer extends Player
{
    private boolean isHorizontal;
    
    public Computer(String name, Deck deck, boolean isHorizontal, int cardGap) {
        super(name, deck, isHorizontal, cardGap);
    }
    
    @Override
    public void act() {
        GameScreen game = (GameScreen) getWorld();
        
        GameScreen.wait(2000);
        
        if (game.getCurrentPlayer().equals(this) && game.canPlay()) {
            List<Card> playableCards = new ArrayList<>();
            for (Card card : this.cards) {
                if (game.canPlayCard(card)) {
                    playableCards.add(card);
                }
            }
            
            if (playableCards.size() == 0) {
                Card card  = game.getDeck().drawCard();
                cards.add(card);
                repaintCards();
                
                GameScreen.wait(1000);
                
                if (game.canPlayCard(card)) {
                    cards.remove(card);
                    repaintCards();
                    game.replaceTopCard(card);
                }
            } else {
                Card playing = playableCards.get(Greenfoot.getRandomNumber(playableCards.size()));
                cards.remove(playing);
                repaintCards();
                game.replaceTopCard(playing);
            }
        }
        
        if (cards.size() == 0) {
            Text text = new Text("Computer Wins!", 60, Color.WHITE);
            game.addObject(text, game.getWidth() / 2, game.getHeight() / 2);
            Greenfoot.stop();
        }
    }  
    
    @Override
    public boolean isUser() {
        return false;
    }
}
