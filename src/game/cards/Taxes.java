package game.cards;

public class Taxes extends Card {

    public Taxes(String name, int rental_price) {
        this.name = name;
        this.rental_price = rental_price;
        this.isOwnable = false;
        this.isOwned = false;
        this.owner = null;
    }

}
