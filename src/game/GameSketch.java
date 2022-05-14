package game;

import inputs.Console;
import inputs.Keyboard;
import processing.core.PApplet;
//import processing.core.PImage;

public class GameSketch extends PApplet {

	// Todo esto es para leer datos atravez del teclado
	// Trasladar a una clase aparte
	Console c = new Console(10, 100, 48, this);
	Keyboard teclado = new Keyboard(this, c);

	String x = "";
	String space = " ";
	char spaces = space.charAt(0);

	@Override
	public void settings() {
		size(960, 768);

	}

	@Override
	public void setup() {

		c.activate();
	}

	@Override
	public void draw() {
		background(0);
		teclado.keyPressed();
		c.display();
	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

}
