package game.cards;

public class Transports extends Card {

    public Transports(String name, int selling_price, int rental_price) {
        this.name = name;
        this.type = "transport";
        this.isOwnable = true;
        this.owner = null;
        this.isOwned = false;
        this.selling_price = selling_price;
        this.rental_price = rental_price;
        this.isMortgable = true;
        this.isMortgabled = false;
    }
}
