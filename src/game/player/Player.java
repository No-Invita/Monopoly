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
import game.pieces.Pieces;

public class Player {

	public String name;
	public int turno;
	public int money;
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
	public int numpairs;
	public Pieces piece;
	public boolean boleano = false;
	public boolean movearound;
	Scanner Leer = new Scanner(System.in);
	private String move;

	public String[] coor = new String[40];
	public String[] coords;
	public boolean teleport = false;

	public Player(String name, Board board, Bank bank)
			throws IOException {
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
		this.numpairs = 0;
		this.piece = new Pieces();
		next = this;
		this.movearound = true;
		WriteFile.createFile(properties);
	}

	public void getMoney(int amount) {
		this.money += amount;

	}

	public void giveMoney(int amount) {
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

	}

	public boolean isInGo() {
		return position.index == 0;
	}

	public void moveAround() throws IOException {
		rollDices();
		for (int i = 0; i < 2; i++) {
			moveForward();
		}
		System.out.println("Destino: " + position.name);
		// bank.request("buy", this);
	}

	public void moveAround(boolean x) throws IOException {
		for (int i = 0; i < result.result; i++) {
			moveForward();
		}
		System.out.println("Destino: " + position.name);
		// bank.request("buy", this);
	}

	public void moveBackward() {
		position = position.prev;
	}

	public void moveBackward(int x) {
		Card aux = position;
		for (int i = 0; i < x; i++) {
			aux = aux.prev;
		}
		do {
			moveBackward();
		} while (position.index != aux.index);
	}

	public void moveForward() throws IOException {
		position = position.next;
		if (isInGo()) {
			bank.request("go", this);
			System.out.println("pasé por salida");
		}
	}

	public void moveTo(int pos) throws IOException {
		Card aux = position;
		Card search = position;
		int i = 0;
		int j = 0;
		if (pos == 0) {
			do {
				aux = aux.next;
				i++;

			} while (!aux.type.equals("transport"));
			do {
				search = search.prev;
				j++;
				System.out.println("----" + aux.type + "----");
			} while (!search.type.equals("transport"));
			if (i <= j) {
				do {
					moveForward();
				} while (position.index != aux.index);
			} else {
				do {
					moveBackward();
				} while (position.index != search.index);

			}
		} else {
			do {
				moveForward();
			} while (position.index != pos);
		}

	}

	public void movePiece() throws IOException {

		switch (this.turno) {
			case 1: {
				coor = ReadFile.read("src/game/pieces/piecescoors/coordinatesRED");

				break;
			}
			case 2: {
				coor = ReadFile.read("src/game/pieces/piecescoors/coordinatesBLUE");

				break;
			}
			case 3: {
				coor = ReadFile.read("src/game/pieces/piecescoors/coordinatesGREEN");
				break;
			}
			case 4: {
				coor = ReadFile.read("src/game/pieces/piecescoors/coordinatesYELLOW");
				break;
			}
		}
		coords = coor[position.index].split(",");

		if (this.piece.posx > this.piece.topex && this.piece.posy == this.piece.topey + 670) {
			this.move = "Left";
		} else {

			if (this.piece.posy > this.piece.topey && this.piece.posx == this.piece.topex) {
				this.move = "Up";
			} else {

				if (this.piece.posx < this.piece.topex + 673 && this.piece.posy == this.piece.topey) {
					this.move = "Right";

				} else {

					if (this.piece.posy < this.piece.topey + 670 && this.piece.posx == this.piece.topex + 673) {
						this.move = "Down";
					} else {
						this.move = "Left";

					}
				}
			}
		}

		if (this.move.equals("Left")) {
			if (this.piece.posx > Integer.parseInt(coords[0])) {
				this.piece.posx = this.piece.posx - 1;

			}

		} else if (this.move.equals("Up")) {

			if (this.piece.posy > Integer.parseInt(coords[1])) {
				this.piece.posy = this.piece.posy - 1;
			}

		} else if (this.move.equals("Right")) {

			if (this.piece.posx < Integer.parseInt(coords[0])) {
				this.piece.posx = this.piece.posx + 1;

			}

		} else if (this.move.equals("Down")) {
			if (this.piece.posy < Integer.parseInt(coords[1])) {
				this.piece.posy = this.piece.posy + 1;

			}
		}

	}

	public void playInJail() throws IOException {
		if (this.turnsInJail < 3) {
			if (this.outjail != 1) {
				bank.ofert = true;
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