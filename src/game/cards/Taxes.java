package game.cards;

public class Taxes extends Card {

    public Taxes(String name) {
        this.name = name;
        this.isOwnable = true;
        this.isOwned = false;
        this.owner = null;
    }

}
