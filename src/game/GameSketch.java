package game;

import inputs.Console;
import inputs.Keyboard;
import processing.core.PApplet;
import processing.core.PImage;

public class GameSketch extends PApplet {
	PImage bg;
	PImage piece1;
	int posx_piece1 = 689;
	int posy_piece1 = 665;
	int distance = 61;
	// Todo esto es para leer datos atravez del teclado
	// Trasladar a una clase aparte
	Console c = new Console(10, 100, 48, this);
	Keyboard teclado = new Keyboard(this, c);

	String x = "";
	String space = " ";
	char spaces = space.charAt(0);

	@Override
	public void settings() {
		//size(960, 768);
		fullScreen();
		//surface.setSize(960, 768);
		
	}

	@Override
	public void setup() {
		surface.setSize(960, 768);
		bg = loadImage("src/images/bg.png");
		piece1 = loadImage("src/images/piece1.png");
		bg.resize(960, 768);
		
			
	}

	@Override
	public void draw() {
		image(bg, 0, 0);
		image(piece1, posx_piece1, posy_piece1);
		movePieces();
	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

	public void movePieces() {
		if (posx_piece1 <= 689 && posy_piece1 <= 685) {
			posx_piece1--;
		}
		if (posx_piece1 >= 21 && posy_piece1 <= 685) {
			posy_piece1--;
		}
		if (posx_piece1 >= 21 && posy_piece1 <= 13) {
			posx_piece1++;
		}
		if(posx_piece1 <= 690 && posy_piece1 <=13){
			posy_piece1++;
		}
		// if(posx_piece1 >=667){
		// posy_piece1++;
		// }if(posy_piece1 >=685){

		// }

	}

}
