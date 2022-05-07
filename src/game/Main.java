package game;

import game.util.ReadFile;

public class Main {
    public static void main(String[] args) {
        // Create the board
        Board board = new Board();
        // Read the data file
        String[] data = ReadFile.read("src/data/casillas");
        // Load the board
        board.loadBoard(data);
        // create the players list
        PlayerList players = new PlayerList();
        // Add the players to the players list
        Player player1 = new Player("Elkin", 0);
        Player player2 = new Player("Luis", 0);
        Player player3 = new Player("Johan", 0);
        Player player4 = new Player("Fabian", 0);
        players.addPlayer(player1);
        players.addPlayer(player2);
        players.addPlayer(player3);
        players.addPlayer(player4);
        // start game

    }

}
