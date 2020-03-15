/**
 * Write a description of class Direction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Direction {
    CW(1), CCW(-1);
    
    private int amount;
    
    private Direction(int amount) {
        this.amount = amount;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public Direction toggleDirection() {
        return this.equals(CW) ? CCW : CW;
    }
}
