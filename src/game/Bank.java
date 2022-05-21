package game;

import game.cards.Card;
import game.player.Player;
import game.player.PlayerList;
import game.specialcards.CardList;
import game.specialcards.CardNode;
import game.util.DeleteRegister;
import game.util.WriteFile;
import java.io.IOException;
import java.util.Scanner;

public class Bank {
	float taxeMoney;
	Scanner Leer = new Scanner(System.in);
	CardList luck;
	CardList ark;
	PlayerList playerList;
	Board board;
	boolean ofert;
	boolean buy;
	public Bank(CardList luck, CardList ark, PlayerList playerList, Board board) {
		this.luck = luck;
		this.ark = ark;
		this.taxeMoney = 0;
		this.playerList = playerList;
		this.board = board;
		this.ofert = false;
	}

	public void demandMoney(float amount, Player player) {
		player.giveMoney(amount);
	}

	public void demortgageProperty(Card property, Player player) {
		if (property.isMortgable && (property.isMortgabled) && property.owner == player) {
			property.isMortgabled = false;

		}
	}

	public void demandMoneyForJailExit(Player player) {
		demandMoney(200000, player);
	}

	public void mortgageProperty(Card property, Player player) {
		if (property.isMortgable && !(property.isMortgabled) && property.owner == player) {
			property.isMortgabled = true;
			pay(property.selling_price / 2, player);
		}
	}

	public void offerProperty(Card property, Player player) throws IOException {
		this.ofert = true;

	}

	public void pay(float amount, Player player) {
		player.getMoney(amount);
	}

	public void payGo(Player player) {
		this.pay(810000, player);
	}

	public void removeProperty(Card property, Player player) {
		property.owner = null;
	}

	public void request(String type, Player player) throws IOException {
		switch (type) {
			case "buy": {
				if (player.position.isOwnable) {
					if (player.position.owner == null) {
						offerProperty(player.position, player);
					} else {
						switch (player.position.type) {
							case "transport": {
								transferMoney(player, player.position.owner,
										player.position.rental_price * player.position.owner.transport);
								System.out.println(player.name + " transfiere "
										+ player.position.rental_price * player.position.owner.transport + " a "
										+ player.position.owner.name + " Por tener " + player.position.owner.transport
										+ " trasnportes");
								break;
							}
							case "service": {
								int paying = player.position.rental_price * player.position.owner.services
										* player.rollDices().result;
								transferMoney(player, player.position.owner,
										paying);
								System.out.println(player + " transfiere " + paying + " a " + player.position.owner
										+ " porque tiene " + player.position.owner.services
										+ "servicios y el resultado de los dados fue " + player.result.result);
								break;
							}
							default: {
								transferMoney(player, player.position.owner, player.position.rental_price);
								System.out.println(player.name + " transfiere " + player.position.rental_price + " a "
										+ player.position.owner.name + " Por caer en " + player.position.name);
							}
						}
					}
				} else {
					switch (player.position.type) {
						case "taxe": {
							demandMoney(player.position.rental_price, player);
							System.out.println(player.name + " pago impuesto a los tombos");
							break;
						}
						case "luck": {
							// Leees la tarjeta de fortuna
							System.out.println("Estas en suerte");
							CardNode card = luck.pickRandomNode();
							System.out.println(card.description);
							switch (card.type) {
								case "dar": {
									demandMoney(card.param, player);
									System.out.println("pagué " + card.param);
									break;
								}
								case "recibir": {
									pay(card.param, player);
									System.out.println("recibí " + card.param);
									break;
								}
								case "salir": {
									if (!luck.isPicked) {
										player.outjail = 1;
									} else {
										request("luck", player);
									}

									break;

								}
								case "apresar": {
									player.goJail();
									break;
								}
								case "mover a": {
									player.moveTo(card.param);
									System.out.println("Me moví hasta " + player.position.name);
									break;
								}
							}
							break;
						}
						case "ark": {
							System.out.println("Estas en arca");
							CardNode card = ark.pickRandomNode();
							System.out.println(card.description);
							switch (card.type) {
								case "moverse a": {
									player.moveTo(card.param);
									System.out.println("Me moví hasta " + player.position.name);
									break;
								}
								case "dar": {
									demandMoney(card.param, player);
									System.out.println("pagué " + card.param);
									break;
								}
								case "recibir": {
									pay(card.param, player);
									System.out.println("recibí " + card.param);
									break;
								}
								case "recibir de todos": {
									for (int i = 0; i < 3; i++) {
										Player other = player.next;
										demandMoney(card.param, other);
										pay(card.param, player);
									}
									break;
								}
							}
							break;
						}
						case "gotojail": {
							System.out.println("Te vas pa la carcel");
							player.goJail();
							break;
						}
					}
				}
				break;
			}
			case "go": {
				payGo(player);
				break;
			}
			case "gojail": {
				System.out.println("encarcelado");
				player.isPrisoner = true;
				player.turnsInJail = 0;
				while (player.position.index != 10) {
					player.movePiece();
					player.position = player.position.next;
				}
				break;
			}
			case "exitjail": {
				System.out.println("saliste de la carcel");
				demandMoneyForJailExit(player);
				player.isPrisoner = false;
				player.turnsInJail = 0;
				break;
			}
			case "exitjailfree": {
				System.out.println("saliste de la carcel gratis");
				player.isPrisoner = false;
				player.turnsInJail = 0;
				break;
			}
			case "exitjailwithcard": {
				System.out.println("saliste de la carcel gratis con tarjeta");
				player.isPrisoner = false;
				player.turnsInJail = 0;
				player.outjail = 0;
				luck.isPicked = false;
				break;
			}

		}
	}

	public void transferMoney(Player payer, Player receiver, int amount) throws IOException {
		if (payer.money >= amount) {
			payer.giveMoney(amount);
			receiver.getMoney(amount);

		} else {
			System.out.println("not enough money");
		}
	}

	public void tranferProperty(Card property, Player player) throws IOException {
		if (player.money >= property.selling_price) {

			demandMoney(property.selling_price, player);
			property.owner = player;
			property.isOwned = true;
			player.buyProperty(property.name);
			switch (property.type) {
				case "transport": {
					player.transport++;
					System.out.println("Tengo " + player.transport + " Transportes");
					break;
				}
				case "service": {
					player.services++;
					System.out.println("Tengo " + player.services + " Services");
					break;
				}
			}
		} else {
			System.out.println("not enough money");
		}
	}

	public void negociar(Player emisor, Player receptor, String index_ownwership_emisor, int index_ownwership_receptor,
			float amount_emisor, float amount_receptor) throws IOException {
		String proper = DeleteRegister.deleteRegister(emisor.properties, index_ownwership_emisor);
		WriteFile.write(receptor.properties, proper, receptor.num_properties);
	}

}
