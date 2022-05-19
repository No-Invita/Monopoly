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
	float timeexecute;
	float timedices;
	float capturetime;
	PImage bg;
	PImage[] dados = new PImage[6];
	PImage piece1;
	PImage piece2;
	PImage piece3;
	PImage piece4;

	int distance = 61;
	boolean visible = true;
	// Todo esto es para leer datos atravez del teclado
	// Trasladar a una clase aparte
	Console console = new Console(10, 100, 48, this);
	Keyboard teclado = new Keyboard(this, console);
	PlayerList lista = new PlayerList();

	String x = "";
	String space = " ";
	char spaces = space.charAt(0);
	Bank banco;
	int prueba = 50;

	public GameSketch(Bank banco) {
		this.banco = banco;
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
		// console.activate();
		bg = loadImage("src/images/bg.png");
		bg.resize(960, 768);

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

		// Displey de board
		image(bg, 0, 0);
		// Display the pieces
		image(piece1, lista.head.piece.posx, lista.head.piece.posy);
		image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
		image(piece3, lista.head.next.next.piece.posx, lista.head.next.next.piece.posy);
		image(piece4, lista.tail.piece.posx, lista.tail.piece.posy);
		// Display the dice
		image(dados[lista.head.result.results[0] - 1], 793, 622);
		image(dados[lista.head.result.results[1] - 1], 869, 622);

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
			x = console.chars;
			System.out.println(x);
			console.reset();
		}
	}

	@Override
	public void mouseClicked() {

		if (mouseX > 793 && mouseX < 939 && mouseY > 702 && mouseY < 718) {
			// capturetime = true;

			// if (timedices < 5) {
			try {
				lista.head.moveAround();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// }
		}

	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

}
