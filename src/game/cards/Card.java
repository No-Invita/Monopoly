package game.cards;
import game.Player;

public abstract class Card{
    public String name;
    public boolean isOwnable;
    public boolean isOwned;
    public Player owner;
    public int index;
    public Card next = this;
    public Card prev = this;
}