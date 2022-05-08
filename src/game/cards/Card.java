package game.cards;

import game.player.Player;

public abstract class Card {
    public String name;
    public boolean isOwnable = false;
    public boolean isOwned = false;
    public boolean isMortgable = false;
    public boolean isMortgabled = false;
    public int selling_price = 0;
    public int rental_price = 0;
    public Player owner;
    public int index;
    public Card next = this;
    public Card prev = this;
}