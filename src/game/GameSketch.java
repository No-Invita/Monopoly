package game;

import inputs.Console;
import inputs.Keyboard;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import game.player.Player;
import java.io.IOException;
import game.player.PlayerList;
import game.dices.dices;

public class GameSketch extends PApplet {
	int scene = 1;
	int numPlayers = 1;
	int index = 1;

	float timeexecute;
	float timedices;
	float capturetime;

	String[] input = new String[5];

	PImage bg;
	PImage sceneStart;
	PImage sceneChoosePLayers;
	PImage sceneInputNames;
	PImage ofert;
	PImage player;
	PImage[] dados = new PImage[6];
	PImage piece1;
	PImage piece2;
	PImage piece3;
	PImage piece4;
	PImage dialog;

	PFont font, font2;

	boolean changeturn = false;
	boolean loadplayers = false;
	boolean move = false;
	boolean launch = false;

	Console console = new Console(360, 386, 24, this);
	Keyboard teclado = new Keyboard(this, console);
	PlayerList lista;
	Bank bank;
	Player current;

	public GameSketch(Bank banco) {
		this.bank = banco;
		this.lista = banco.playerList;
	}

	@Override
	public void settings() {
		fullScreen();
	}

	@Override
	public void setup() {
		surface.setResizable(true);
		surface.setSize(960, 768);
		console.deactivate();
		font = createFont("src/font/monopoly-inline.ttf", 35);
		bg = loadImage("src/images/bg.png");
		sceneStart = loadImage("src/images/scene_start.png");
		sceneInputNames = loadImage("src/images/scene _inputnames.png");
		sceneChoosePLayers = loadImage("src/images/choosePlayers.png");

		ofert = loadImage("src/images/ofert.png");
		piece1 = loadImage("src/images/piece1.png");
		piece2 = loadImage("src/images/piece2.png");
		piece3 = loadImage("src/images/piece3.png");
		piece4 = loadImage("src/images/piece4.png");
		dialog = loadImage("src/images/dialog.png");
		player = loadImage("src/images/player.png");
		dices.upLoad();
		for (int i = 0; i < dices.dices.length; i++) {
			dados[i] = loadImage(dices.dices[i]);
		}

	}

	@Override
	public void draw() {

		timeexecute = millis() / 1000;
		switch (scene) {
			case 1: {
				image(sceneStart, 0, 0);
				fill(0);
				textFont(font);
				textSize(35);
				text("Presiona espacio para empezar", 270, 723);
				break;
			}
			case 2: {
				image(sceneChoosePLayers, 0, 0);
				break;
			}
			case 3: {
				image(sceneInputNames, 0, 0);
				fill(0);
				textFont(font);
				text(index, 680, 350);
				console.display();
				break;
			}
			case 4: {
				if (!loadplayers) {
					try {
						this.loadPlayer();
					} catch (IOException e) {

						e.printStackTrace();
					}
					loadplayers = true;
				}
				// dispaly te board
				image(bg, 0, 0);
				image(dialog, 452, 117);
				// Display the pieces
				fill(0);
				textFont(font);
				textSize(18);
				image(piece1, lista.head.piece.posx, lista.head.piece.posy);
				image(player, 809, 50);
				text(lista.head.name, 845, 66);
				switch (numPlayers) {
					case 2: {
						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						image(player, 809, 102);
						text(lista.head.next.name, 845, 207);
						break;
					}
					case 3: {
						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						image(piece3, lista.tail.prev.piece.posx, lista.tail.prev.piece.posy);
						image(player, 809, 192);
						image(player, 809, 347);
						text(lista.head.next.name, 845, 207);
						text(lista.head.next.next.name, 845, 352);
						break;
					}
					case 4: {
						try {
							current.movePiece();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (move) {
							if (current.piece.posx == Integer.parseInt(current.coords[0])
									&& current.piece.posy == Integer.parseInt(current.coords[1])) {
								try {
									bank.request("buy", current);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								move = false;
							}
						}

						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						image(piece3, lista.tail.prev.piece.posx, lista.tail.prev.piece.posy);
						image(piece4, lista.tail.piece.posx, lista.tail.piece.posy);
						image(player, 809, 192);
						image(player, 809, 337);
						image(player, 809, 482);
						text(lista.head.next.name, 845, 207);
						text(lista.head.next.next.name, 845, 352);
						text(lista.tail.name, 845, 497);

						break;
					}
				}

				// Display dices
				image(dados[current.result.results[0] - 1], 793, 622);
				image(dados[current.result.results[1] - 1], 869, 622);

				if (bank.ofert) {
					fill(0);
					textFont(font);
					textSize(21);
					image(ofert, 145, 253);
					text("Deseas comprar " + current.position.name + " por tan solo\n"
							+ current.position.selling_price, 187, 298);
				}
				if (bank.fallontransport) {
					bank.buy = false;
					bank.fallonservices = false;
					fill(0);
					textFont(font);
					textSize(12);
					text(current.name + " transfiere " //caer en transport
							+ current.position.rental_price * current.position.owner.transport + " a\n" +
							current.position.owner.name
							+ "porque tiene\n " + current.position.owner.transport
							+ "\ntransportes", 503, 143);
				}
				if (bank.fallonservices) { //caer en servicios
					bank.buy = false;
					bank.fallontransport = false;
					fill(0);
					textFont(font);
					textSize(12);
					text(current.name + " transfiere " + bank.paying + " a\n" + 
							current.position.owner
							+ "porque tiene " + current.position.owner.services
							+ "\nservicios y el resultado\n de los dados fue " + current.result.result, 503, 143);
				}
				if (bank.pay) { //Aqui es cuando cae en algo que hay pagar en general
					bank.fallonservices = false;
					bank.fallontransport = false;
					fill(0);
					textFont(font);
					textSize(12);
					switch (current.position.type) {
						case "taxe": { /// Son inpuestos
							text("Joa no te tocaba :(\nte toca pagar " + current.position.rental_price
									+ " a los tombos", 493,
									160);
							break;
						}
						default: { //A alguen por caer en algo
							text(current.name + " transfiere " + current.position.rental_price + " a "
									+ current.position.owner.name + "\nPor caer en " + current.position.name, 493, 160);
							break;
						}
					}
				}
				break;
			}
		}
	}

	public void keyPressed() {

		if (teclado.keyAnalyzer(key).compareTo("LETTER") == 0 || teclado.keyAnalyzer(key).compareTo("NUMBER") == 0) {
			console.addChar(key);
		}
		if (keyCode == BACKSPACE) {
			console.deleteChar();
		}

		if (keyCode == ENTER) {
			if (index <= numPlayers) {
				input[index] = console.chars;
				System.out.println(input[index]);
				if (index == numPlayers) {
					scene = 4;
				}

			}
			console.reset();
			index++;
		}
		if (key == ' ' && scene == 1) {
			scene = 2;
		}
	}

	@Override
	public void mouseClicked() {

		if (scene == 2) {
			if (mouseX > 54 && mouseX < 276 && mouseY > 67 && mouseY < 195) {
				numPlayers = 2;
				scene = 3;
			} else if (mouseX > 54 && mouseX < 276 && mouseY > 306 && mouseY < 434) {
				numPlayers = 3;
				scene = 3;
			} else if (mouseX > 54 && mouseX < 276 && mouseY > 545 && mouseY < 673) {
				numPlayers = 4;
				scene = 3;
			}
			console.activate();
		}
		if (scene == 4) {
			if (mouseX > 793 && mouseX < 939 && mouseY > 702 && mouseY < 718) {
				if (!bank.ofert) {
					if (!current.isBroken) {
						if (!current.isPrisoner) {
							if (!changeturn) {
								try {
									image(loadImage("src/images/launch.png"), 794, 704);
									current.moveAround();
									move = true;
								} catch (IOException e) {
									e.printStackTrace();
								}
								changeturn = true;
							}
						}
					}
				}
			}
			if (bank.ofert && mouseX > 195 && mouseX < 283 && mouseY > 422 && mouseY < 472) {
				try {
					bank.tranferProperty(current.position, current);
				} catch (IOException e) {
					e.printStackTrace();
				}
				bank.ofert = false;
			} else if (bank.ofert && mouseX > 486 && mouseX < 574 && mouseY > 422 && mouseY < 472) {
				bank.ofert = false;
			}

			if (mouseX > 793 && mouseX < 939 && mouseY > 729 && mouseY < 745) {
				if (changeturn) {
					image(loadImage("src/images/change.png"), 794, 731);
					bank.pay = false;
					bank.fallontransport = false;
					current = current.next;
					current.result.results = current.prev.result.results;
					changeturn = false;

				}
			}
		}
	}

	public void loadPlayer() throws IOException {

		Player player1 = new Player(input[1], bank.board, bank, "piece1.png");
		lista.addPlayer(player1);
		switch (numPlayers) {
			case 2: {
				Player player2 = new Player(input[2], bank.board, bank, "piece2.png");
				lista.addPlayer(player2);
				break;
			}
			case 3: {
				Player player2 = new Player(input[2], bank.board, bank, "piece2.png");
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, "piece3.png");
				lista.addPlayer(player3);

				break;
			}
			case 4: {
				Player player2 = new Player(input[2], bank.board, bank, "piece2.png");
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, "piece3.png");
				lista.addPlayer(player3);
				Player player4 = new Player(input[4], bank.board, bank, "piece4.png");
				lista.addPlayer(player4);
				break;
			}
		}
		lista.asingcoords();
		lista.display();
		current = lista.head;
		System.out.println("\n" + current.turno);

	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

}
