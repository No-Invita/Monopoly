package game;

import java.io.IOException;

import game.player.PlayerList;
import game.specialcards.CardList;
import game.util.ReadFile;


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

	}
}
