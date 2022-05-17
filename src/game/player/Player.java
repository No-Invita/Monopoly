package game.player;

import java.io.IOException;
import java.util.Scanner;
import game.Bank;
import game.Board;
import game.cards.Card;
import game.dices.dices;
import game.dices.dicesResult;
import game.util.WriteFile;
import game.util.ReadFile;
import game.util.DeleteRegister;
import game.pieces.Pieces;

public class Player {

	public String name;
	public int turno;
	public float money;
	public Card position;
	public boolean isPrisoner;
	public int turnsInJail;
	public boolean isBroken;
	public Player next;
	public Player prev;
	public dicesResult result;
	public String properties;
	public Bank bank;
	public int services;
	public int transport;
	public int outjail;
	public int num_properties;
	public Pieces piece;
	Scanner Leer = new Scanner(System.in);

	public Player(String name, Board board, Bank bank, int x, int y, String path) throws IOException {
		this.name = name;
		this.money = 6000000;
		this.isPrisoner = false;
		this.isBroken = false;
		this.position = board.start;
		this.bank = bank;
		this.properties = "src/data/playersproperties/" + name;
		this.services = 0;
		this.transport = 0;
		this.outjail = 0;
		this.prev = this;
		this.num_properties = 0;
		this.piece = new Pieces(x, y, path);
		next = this;
		WriteFile.createFile(properties);
	}

	public void getMoney(float amount) {
		this.money += amount;

	}

	public void giveMoney(float amount) {
		if (amount <= this.money) {
			this.money -= amount;
			System.out.println("Ahora tengo " + this.money);
		} else {
			System.out.println("No tienedes suficiente dinero. Debes vender propiedades o hipotecarlas");
			if (this.num_properties > 0) {
				System.out.println("¿Deseas  negociar o hipotecar?\n1.Negociar\n2.Hipotecar");
				int choose = Leer.nextInt();
				switch (choose) {
					case 1: {

						break;
					}
					default:
						break;
				}
			}

		}
	}

	public void goJail() throws IOException {
		bank.request("gojail", this);
		// this.position = 10;
	}

	public boolean isInGo() {
		return position.index == 0;
	}

	public void moveAround() throws IOException {
		rollDices();
		for (int i = 0; i < result.result; i++) {
			moveForward();
		}
		System.out.println("Destino: " + position.name);
		bank.request("buy", this);
	}

	public void moveAround(boolean x) throws IOException {
		for (int i = 0; i < result.result; i++) {
			moveForward();
		}
		System.out.println("Destino: " + position.name);
		bank.request("buy", this);
	}

	public void moveBackward() {
		position = position.prev;
	}

	public void moveForward() throws IOException {
		position = position.next;
		this.movePiece();
		if (isInGo()) {
			bank.request("go", this);
			System.out.println("pasé por salida");
		}
	}

	public void movePiece() throws IOException {
		if (this.piece.posx > 19 && this.piece.posy == 681) {
			this.piece.posx--;
		} else if (this.piece.posy > 16 && this.piece.posx == 19) {
			this.piece.posy--;
		} else if (this.piece.posx < 686 && this.piece.posy == 16) {
			this.piece.posx++;
		} else {
			this.piece.posy++;
		}
	}

	public void moveTo(int pos) throws IOException {
		do {
			moveForward();
		} while (position.index != pos);
	}

	public void playInJail() throws IOException {
		if (this.turnsInJail < 3) {
			if (this.outjail != 1) {
				System.out.println("¿desea salir de la carcel pagando 200000? \t1: si \t2:no");
				int salir = Leer.nextInt();
				if (salir == 1) {
					bank.request("exitjail", this);
					System.out.println("saliste de la carcel");
				} else {
					rollDices();
					if (result.isPair) {
						bank.request("exitjailfree", this);
						moveAround(true);
					} else {
						System.out.println("ni modo no saliste de la carcel");
						turnsInJail++;
					}
				}
			} else {
				System.out.println("deseas usar la tarjeta de salir de la carcel? \t1: si \t2:no");
				int salir = Leer.nextInt();
				if (salir == 1) {
					bank.request("exitjailwithcard", this);
					moveForward();
				} else if (salir == 2) {
					System.out.println("¿desea salir de la carcel pagando 200000? \t1: si \t2:no");
					salir = Leer.nextInt();
					if (salir == 1) {
						bank.request("exitjail", this);
						System.out.println("saliste de la carcel");
					} else {
						rollDices();
						if (result.isPair) {
							bank.request("exitjailfree", this);
							moveAround(true);
						} else {
							System.out.println("ni modo no saliste de la carcel");
							turnsInJail++;
						}
					}
				}
			}
		} else {
			System.out.println("te toca salir de la carcel pagando 200000");
			bank.request("exitjail", this);
			this.moveForward();
		}
	}

	public dicesResult rollDices() {
		dicesResult result = dices.rollDices();
		result.display();
		return this.result = result;
	}

	public void buyProperty(String name) throws IOException {
		this.num_properties++;
		WriteFile.write(properties, name, this.num_properties);
	}
}