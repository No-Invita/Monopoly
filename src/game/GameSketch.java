package game;

import inputs.Console;
import inputs.Keyboard;
import processing.core.PApplet;
import processing.core.PImage;
import game.player.Player;
import java.io.IOException;
import game.player.PlayerList;
import game.dices.dices;

public class GameSketch extends PApplet {
	int scene = 1;
	int numPlayers = 1;
	int index = 1;
	int distance = 61;

	float timeexecute;
	float timedices;
	float capturetime;

	String[] input = new String[5];

	PImage bg;
	PImage sceneStart;
	PImage sceneChoosePLayers;
	PImage sceneInputNames;
	PImage ofert;
	PImage[] dados = new PImage[6];
	PImage piece1;
	PImage piece2;
	PImage piece3;
	PImage piece4;

	boolean visible = true;
	boolean loadplayers = false;

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
		bg = loadImage("src/images/bg.png");
		sceneStart = loadImage("src/images/scene_start.png");
		sceneInputNames = loadImage("src/images/scene _inputnames.png");
		sceneChoosePLayers = loadImage("src/images/choosePlayers.png");
		ofert = loadImage("src/images/ofert.png");
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
				textSize(28);
				text("Presiona ESPACIO para empezar", 270, 723);
				break;
			}
			case 2: {
				image(sceneChoosePLayers, 0, 0);
				break;
			}
			case 3: {
				image(sceneInputNames, 0, 0);
				fill(0);
				textSize(28);
				text(index, 680, 350);
				console.display();
				break;
			}
			case 4: {
				// System.out.println("Holaa");
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
				// Display the pieces
				image(piece1, lista.head.piece.posx, lista.head.piece.posy);
				switch (numPlayers) {
					case 2: {
						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						break;
					}
					case 3: {
						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						image(piece3, lista.tail.prev.piece.posx, lista.tail.prev.piece.posy);
						break;
					}
					case 4: {
						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						image(piece3, lista.tail.prev.piece.posx, lista.tail.prev.piece.posy);
						image(piece4, lista.tail.piece.posx, lista.tail.piece.posy);
						break;
					}
				}

				// Display the dice
				image(dados[current.result.results[0] - 1], 793, 622);
				image(dados[current.result.results[1] - 1], 869, 622);

				if (bank.ofert) {
					fill(0);
					textSize(18);
					image(ofert, 140, 228);
					text("Deseas comprar " + current.position.name + " por tan solo\n"
							+ current.position.selling_price, 187, 298);
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

				try {
					current.moveAround();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bank.ofert && mouseX > 195 && mouseX < 283 && mouseY > 397 && mouseY < 447) {
				try {
					bank.tranferProperty(current.position, current);
				} catch (IOException e) {
					e.printStackTrace();
				}
				bank.ofert = false;
			} else if (bank.ofert && mouseX > 486 && mouseX < 283 && mouseY > 397 && mouseY < 574) {
				bank.ofert = false;
				System.out.println("Hola");
			}

		}
	}

	public void loadPlayer() throws IOException {

		Player player1 = new Player(input[1], bank.board, bank, 699, 689, "piece1.png");
		lista.addPlayer(player1);
		switch (numPlayers) {
			case 2: {
				Player player2 = new Player(input[2], bank.board, bank, 722, 689, "piece2.png");
				lista.addPlayer(player2);
				break;
			}
			case 3: {
				Player player2 = new Player(input[2], bank.board, bank, 722, 689, "piece2.png");
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, 699, 714, "piece3.png");
				lista.addPlayer(player3);

				break;
			}
			case 4: {
				Player player2 = new Player(input[2], bank.board, bank, 722, 689, "piece2.png");
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, 699, 714, "piece3.png");
				lista.addPlayer(player3);
				Player player4 = new Player(input[4], bank.board, bank, 726, 714, "piece4.png");
				lista.addPlayer(player4);
				break;
			}
		}
		lista.display();
		current = lista.head.next;
		piece1 = loadImage(lista.head.piece.path);
		piece2 = loadImage(lista.head.next.piece.path);
		piece3 = loadImage(lista.tail.prev.piece.path);
		piece4 = loadImage(lista.tail.piece.path);

	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

}
