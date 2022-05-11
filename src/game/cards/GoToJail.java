package game.cards;

public class GoToJail extends Card {
    public GoToJail() {
        this.name = "Vayase a la carcel";
        this.type = "gotojail";
        this.isOwnable = false;
        this.isOwned = false;
        this.owner = null;
        this.index = 30;
        this.next = this;
        this.prev = this;
    }
}
