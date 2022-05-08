package game.cards;

public class Ownership extends Card {
    public Ownership(String name, int selling_price, int rental_price) {
        this.name = name;
        this.type = "ownership";
        this.selling_price = selling_price;
        this.rental_price = rental_price;
        this.isOwnable = true;
        this.isOwned = false;
        this.owner = null;
        this.isMortgable = true;
        this.isMortgabled = false;
    }
}