package game;

public class Ownerships {
    
    String name;
    int selling_price;
    int rental_price;
    Ownerships next;
    Ownerships prev;
    boolean own;
    int index;

    public Ownerships(String name, int selling_price, int rental_price) {
        this.name = name;
        this.selling_price = selling_price;
        this.rental_price = rental_price;
        this.own = false;
    }
}