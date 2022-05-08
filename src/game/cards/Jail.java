package game.cards;

public class Jail extends Card {

    public Jail() {
        this.name = "Carcel Modelo";
        this.type = "jail";
        this.isOwnable = false;
        this.isOwned = false;
        this.owner = null;
        this.index = 10;
        this.next = this;
        this.prev = this;
    }
}