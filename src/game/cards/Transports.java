package game.cards;

public class Transports extends Card {
    public int selling_price;
    public int rental_price;

    public Transports(String name) {
        this.name = name;
        this.isOwnable = true;
        this.owner = null;
        this.isOwned = false;
        this.selling_price = 50000;
        this.rental_price = 2500;
    }
}
