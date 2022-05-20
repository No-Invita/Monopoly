package game;

import inputs.Console;
import inputs.Keyboard;
import processing.core.PApplet;
import processing.core.PImage;
import game.player.Player;
import java.io.IOException;
import game.player.PlayerList;
import game.dices.dices;
import game.Main;;

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
	PImage[] dados = new PImage[6];
	PImage piece1;
	PImage piece2;
	PImage piece3;
	PImage piece4;

	int distance = 61;
	boolean visible = true;
	boolean loadplayers = false;
	Console console = new Console(360, 386, 24, this);
	Keyboard teclado = new Keyboard(this, console);
	PlayerList lista = new PlayerList();

	String x = "";

	Bank bank;
	int prueba = 50;

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
		console.activate();
		bg = loadImage("src/images/bg.png");
		sceneStart = loadImage("src/images/scene_start.png");
		sceneInputNames = loadImage("src/images/scene _inputnames.png");
		sceneChoosePLayers = loadImage("src/images/choosePlayers.png");
		piece1 = loadImage(lista.head.piece.path);
		piece2 = loadImage(lista.head.next.piece.path);
		piece3 = loadImage(lista.head.next.next.piece.path);
		piece4 = loadImage(lista.tail.piece.path);
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
				if (!loadplayers) {
					thread("loadPlayer");
					loadplayers = true;
				}

				image(bg, 0, 0);
				//Display the pieces
				image(piece1, lista.head.piece.posx, lista.head.piece.posy);
				image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
				image(piece3, lista.head.next.next.piece.posx, lista.head.next.next.piece.posy);
				image(piece4, lista.tail.piece.posx, lista.tail.piece.posy);
				// Display the dice
				image(dados[lista.head.result.results[0] - 1], 793, 622);
				image(dados[lista.head.result.results[1] - 1], 869, 622);
				break;
			}
		}

		// Displey de board

		// try {

		// lista.head.moveAround();

		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// visible = false;

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
		if (key == ' ') {
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

		}
		if (mouseX > 793 && mouseX < 939 && mouseY > 702 && mouseY < 718) {

			try {
				lista.head.moveAround();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void loadPlayer() throws IOException {
		
		Player player1 = new Player(input[1], bank.board, bank, 699, 689, "piece1.png");
		lista.addPlayer(player1);
		switch (numPlayers) {
			case 2: {
				Player player2 = new Player(input[2], bank.board, bank, 699, 689, "piece1.png");
				lista.addPlayer(player2);
				break;
			}
			case 3: {
				Player player2 = new Player(input[2], bank.board, bank, 699, 689, "piece1.png");
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, 699, 689, "piece1.png");
				lista.addPlayer(player3);

				break;
			}
			case 4: {
				Player player2 = new Player(input[2], bank.board, bank, 699, 689, "piece1.png");
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, 699, 689, "piece1.png");
				lista.addPlayer(player3);
				Player player4 = new Player(input[4], bank.board, bank, 699, 689, "piece1.png");
				lista.addPlayer(player4);
				break;
			}
		}
		lista.display();
	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

}
