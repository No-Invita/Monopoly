package game;

import java.io.IOException;

import game.player.Player;
import game.player.PlayerList;
import game.specialcards.CardList;
import game.util.ReadFile;
import processing.core.PImage;
import inputs.Console;
import java.util.Scanner;
import game.GameSketch;

public class Main {
	public static void main(String[] args) throws IOException {
		// // Create the board
		Board board = new Board();
		// // create the luck and Ark mass
		CardList luck = new CardList();
		CardList ark = new CardList();
		// // Read the data files
		String[] data = ReadFile.read("src/data/casillas");
		String[] luckdata = ReadFile.read("src/data/luck");
		String[] arkdata = ReadFile.read("src/data/ark");
		// // Load the board
		board.loadBoard(data);
		// // load the luck and ark cards
		luck.loadList(luckdata);
		ark.loadList(arkdata);
		// // create the players list
		PlayerList players = new PlayerList();
		// // Create Bank
		Bank bank = new Bank(luck, ark, players,board);
		GameSketch sketch = new GameSketch(bank);
		//Player current = players.head;
		sketch.run();

		// boolean ended = false;
		// while(!ended){
		// for (int i = 0; i < 50; i++) {
		// if (!current.isBroken) {
		// System.out
		// .println("Hola, soy " + current.name + " y estoy en " + current.position.name
		// + " y tengo "
		// + current.money);
		// if (!current.isPrisoner) {
		// current.moveAround();
		// System.out.println("moviendo");
		// } else {
		// System.out.println("moviendo en carcel");
		// current.playInJail();
		// }
		// // Is pair result?
		// if (current.result.isPair) {
		// System.out.println("saqué par");
		// } else {
		// current = current.next;
		// }
		// System.out.println("");
		// }
		// }
		// Leer.close();
	}
}
