package game;

import inputs.Console;
import inputs.Keyboard;
import processing.core.PApplet;
import processing.core.PImage;
import game.player.Player;
import java.io.IOException;
import game.player.PlayerList;
import game.Main;;

public class GameSketch extends PApplet {
	PImage bg;
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

		surface.setSize(960, 768);
		//console.activate();
		bg = loadImage("src/images/bg.png");
		bg.resize(960, 768);
		piece1 = loadImage(lista.head.piece.path);
		piece2 = loadImage(lista.head.next.piece.path);
		piece3 = loadImage(lista.head.next.next.piece.path);
		piece4 = loadImage(lista.tail.piece.path);
		
	}

	@Override
	public void draw() {
		image(bg, 0, 0);
		image(piece1, lista.head.piece.posx, lista.head.piece.posy);
		image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
		image(piece3, lista.head.next.next.piece.posx, lista.head.next.next.piece.posy);
		image(piece4, lista.tail.piece.posx, lista.tail.piece.posy);
		try {

			lista.head.moveAround();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			x = console.chars;
			System.out.println(x);
			console.reset();
		}
	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

}
