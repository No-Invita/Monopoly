package game.cards;

public class FreeStop extends Card {

    public FreeStop(String name) {
        this.name = name;
        this.isOwnable = false;
        this.isOwned = false;
        this.owner = null;
        this.type = "freestop";

    }
}
