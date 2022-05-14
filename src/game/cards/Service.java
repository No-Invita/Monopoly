package game.cards;

public class Service extends Card {
	public Service(String name, int selling_price) {
		this.name = name;
		this.type = "service";
		this.isOwnable = true;
		this.owner = null;
		this.isOwned = false;
		this.selling_price = selling_price;
		this.rental_price = 4000;
		this.isMortgable = true;
		this.isMortgabled = false;
	}
}
