package game.cards;



public class Go extends Card {

    public Go() {
        this.name = "Sincelejo";
        this.type = "go";
        this.isOwnable = false;
        this.isOwned = false;
        this.owner = null;
        this.index = 0;
        this.next = this;
        this.prev = this;
    }
}