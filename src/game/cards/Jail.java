package game.cards;

public class Jail extends Card {

    public Jail() {
        this.name = "jail";
        this.isOwnable = false;
        this.isOwned = false;
        this.owner = null;
        this.index = 10;
        this.next = this;
        this.prev = this;
    }
}