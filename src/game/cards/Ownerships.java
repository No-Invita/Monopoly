package game.cards;

public class Ownerships extends Card {
    public int selling_price;
    public int rental_price;
    public int index;

    public Ownerships(String name, int selling_price, int rental_price) {
        this.name = name;
        this.selling_price = selling_price;
        this.rental_price = rental_price;
        this.isOwnable = true;
        this.isOwned = false;
        this.owner = null;
    }


    public static void main(String[] args) {
        Ownerships x = new Ownerships("joaco", 10, 50);
    }
}