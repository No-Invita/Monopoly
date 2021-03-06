package game;

import game.cards.Card;
import game.player.Player;
import game.player.PlayerList;
import game.specialcards.CardList;
import game.specialcards.CardNode;
//import game.util.DeleteRegister;
import java.io.IOException;
import java.util.Scanner;

public class Bank {
	float taxeMoney;
	Scanner Leer = new Scanner(System.in);
	CardList luck;
	CardList ark;
	PlayerList playerList;
	Board board;
	public boolean ofert;
	boolean buy;
	boolean fallontransport;
	boolean pay;
	int paying = 0;
	boolean fallonservices;
	boolean launchluck, launchArk;
	public boolean launchexitjailwithcard;
	CardNode card;
	public boolean launchjail;
	public boolean launchwelcome;
	public boolean luckexitjail;
	public boolean exitjailoverturn;
	public boolean broken;

	public Bank(CardList luck, CardList ark, PlayerList playerList, Board board) {
		this.luck = luck;
		this.ark = ark;
		this.taxeMoney = 0;
		this.playerList = playerList;
		this.board = board;
		this.ofert = false;
	}

	public void demandMoney(int amount, Player player) {
		if (amount <= player.money) {
			player.giveMoney(amount);
		} else {
			player.isBroken = true;
			broken = true;
		}
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

	public void pay(int amount, Player player) {
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
								if (player.position.owner != player) {
									transferMoney(player, player.position.owner,
											player.position.rental_price * player.position.owner.transport);
									fallontransport = true;
								}
								break;
							}
							case "service": {
								switch (player.position.owner.services) {
									case 1: {
										paying = player.position.rental_price * 4
												* player.rollDices().result;
										break;
									}
									case 2: {
										paying = player.position.rental_price * 10
												* player.rollDices().result;
										break;
									}
								}
								transferMoney(player, player.position.owner,
										paying);
								fallonservices = true;
								break;
							}
							default: {
								if (player.position.owner != player) {
									transferMoney(player, player.position.owner, player.position.rental_price);
									pay = true;
								}
								break;
							}
						}
					}
				} else {
					switch (player.position.type) {
						case "taxe": {
							demandMoney(player.position.rental_price, player);
							pay = true;
							break;
						}
						case "luck": {
							card = luck.pickRandomNode();
							System.out.println(card.description);
							launchluck = true;
							player.movearound = false;
							switch (card.type) {
								case "dar": {
									demandMoney(card.param, player);
									System.out.println("pagu?? " + card.param);
									break;
								}
								case "recibir": {
									pay(card.param, player);
									break;
								}
								case "salir": {
									if (!luck.isPicked) {
										player.outjail = 1;
									} else {
										request("buy", player);
									}
									break;
								}
								case "apresar": {
									player.goJail();
									break;
								}
								case "mover a": {
									player.moveTo(card.param);
									break;
								}
							}
							break;
						}
						case "ark": {
							card = ark.pickRandomNode();
							System.out.println(card.description);
							launchArk = true;
							player.movearound = false;
							switch (card.type) {
								case "moverse a": {
									player.moveTo(card.param);
									System.out.println("Me mov?? hasta " + player.position.name);
									break;
								}
								case "dar": {

									demandMoney(card.param, player);
									System.out.println("pagu?? " + card.param);

									break;
								}
								case "recibir": {
									pay(card.param, player);
									System.out.println("recib?? " + card.param);
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
								case "moverse atras": {
									player.moveBackward(card.param);
									player.piece.posx = Integer
											.parseInt(player.coor[player.position.index].split(",")[0]);
									player.piece.posy = Integer
											.parseInt(player.coor[player.position.index].split(",")[1]);
									break;
								}
							}
							break;
						}
						case "gotojail": {
							launchjail = true;
							player.goJail();
							break;
						}
					}
				}
				break;
			}
			case "go": {
				payGo(player);
				launchwelcome = true;
				break;
			}
			case "gojail": {
				player.isPrisoner = true;
				player.turnsInJail = 0;
				while (player.position.index != 10) {
					player.position = player.position.next;
				}
				player.piece.posx = Integer.parseInt(player.coor[player.position.index].split(",")[0]);
				player.piece.posy = Integer.parseInt(player.coor[player.position.index].split(",")[1]);

				break;
			}
			case "exitjail": {
				demandMoneyForJailExit(player);
				player.isPrisoner = false;
				player.turnsInJail = 0;
				exitjailoverturn = true;
				break;
			}
			case "exitjailfree": {
				player.moveAround(true);
				player.isPrisoner = false;
				player.turnsInJail = 0;
				luckexitjail = true;
				break;
			}
			case "exitjailwithcard": {
				player.moveAround();
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
			payer.isBroken = true;
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
			// Aqui va una imagen que te indique no puedes comparar la propiedad ya que no
			// tienes suficiente nero
		}
	}

}
