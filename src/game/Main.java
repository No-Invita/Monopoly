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
		Scanner Leer = new Scanner(System.in);
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
		Bank bank = new Bank(luck, ark, players);
		GameSketch sketch = new GameSketch(bank);

		Player player1 = new Player(sketch.input, board, bank, 699, 689, "piece1.png");
		players.addPlayer(player1);
		// // Add the players to the players list and we sort the orden to play
		switch (sketch.numPlayers) {
			case 2: {
				Player player2 = new Player(sketch.input, board, bank, 722, 689, "piece2.png");
				players.addPlayer(player2);
				break;
			}
			case 3: {
				Player player2 = new Player(sketch.input, board, bank, 722, 689, "piece2.png");
				players.addPlayer(player2);
				Player player3 = new Player(sketch.input, board, bank, 699, 714, "piece3.png");
				players.addPlayer(player3);

				break;
			}
			case 4: {
				Player player2 = new Player(sketch.input, board, bank, 722, 689, "piece2.png");
				players.addPlayer(player2);
				Player player3 = new Player(sketch.input, board, bank, 699, 714, "piece3.png");
				players.addPlayer(player3);
				Player player4 = new Player(sketch.input, board, bank, 726, 714, "piece4.png");
				players.addPlayer(player4);
			}
		}


		// // show the orden to play
		System.out.println("----"+sketch.numPlayers);
		System.out.println("El orden a jugar es:\n");
		players.display();
		
		// // start game
		// // iterate players list
		Player current = players.head;
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
