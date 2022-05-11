package game;

import java.io.IOException;

import game.player.Player;
import game.player.PlayerList;
import game.specialcards.CardList;
import game.util.ReadFile;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create the board
        Board board = new Board();
        // create the luck and Ark mass
        CardList luck = new CardList();
        CardList ark = new CardList();
        // Create Bank
        Bank bank = new Bank(luck, ark);
        // Read the data files
        String[] data = ReadFile.read("src/data/casillas");
        String[] luckdata = ReadFile.read("src/data/luck");
        String[] arkdata = ReadFile.read("src/data/ark");
        // Load the board
        board.loadBoard(data);
        // load the luck and ark cards
        luck.loadList(luckdata);
        ark.loadList(arkdata);
        // create the players list
        PlayerList players = new PlayerList();
        // Add the players to the players list
        Player player1 = new Player("Elkin", board, bank);
        Player player2 = new Player("Luis", board, bank);
        Player player3 = new Player("Johan", board, bank);
        Player player4 = new Player("Fabian", board, bank);
        players.addPlayer(player1);
        players.addPlayer(player2);
        players.addPlayer(player3);
        players.addPlayer(player4);
        // start game
        // iterate players list
        Player current = players.head;
        boolean ended = false;
        // while(!ended){
        for (int i = 0; i < 30; i++) {
            if (!current.isBroken) {

                System.out.println(
                        "Hola, soy " + current.name + " y estoy en " + current.position.name + " y tengo "
                                + current.money);
                current.moveAround();
                // current.result.display();
                // if (current.position.isOwnable && !current.position.isOwned) {
                // bank.offerProperty(current.position, current);
                // System.out.println(current.position.selling_price);
                // // System.out.println(current.position.owner.name);
                // // System.out.println(current.money);
                // }
                if (current.result.isPair) {
                    // System.out.println("saqué par");
                } else {
                    current = current.next;
                }
                System.out.println();
            }
        }

    }

}
