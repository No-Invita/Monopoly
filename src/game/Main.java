package game;

import java.io.IOException;

import game.player.Player;
import game.player.PlayerList;
import game.util.ReadFile;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create the board
        Board board = new Board();
        // Create Bank
        Bank bank = new Bank();
        // Read the data file
        String[] data = ReadFile.read("src/data/casillas");
        // Load the board
        board.loadBoard(data);
        // create the players list
        PlayerList players = new PlayerList();
        // Add the players to the players list
        Player player1 = new Player("Elkin", board);
        Player player2 = new Player("Luis", board);
        Player player3 = new Player("Johan", board);
        Player player4 = new Player("Fabian", board);
        players.addPlayer(player1);
        players.addPlayer(player2);
        players.addPlayer(player3);
        players.addPlayer(player4);
        // start game
        // iterate players list
        Player current = players.head;
        boolean ended = false;
        // while(!ended){
        for (int i = 0; i < 10; i++) {
            current.moveAround();
            System.out.println(
                    "Hola, soy " + current.name + " y estoy en " + current.position.name + " y tengo "
                            + current.money);
            current.result.display();
            if (current.position.isOwnable && !current.position.isOwned) {
                bank.offerProperty(current.position, current);
                System.out.println(current.position.selling_price);
                // System.out.println(current.position.owner.name);
                System.out.println(current.money);
            }
            if (current)
                if (current.result.isPair) {
                    System.out.println("saqué par");
                } else {
                    current = current.next;
                }
        }

    }

}
