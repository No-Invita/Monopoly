package game;

import java.io.IOException;

import game.player.Player;
import game.player.PlayerList;
import game.specialcards.CardList;
import game.util.ReadFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner Leer = new Scanner(System.in);
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
        System.out.println("Escribe los nombres de los jugadores");
        // Add the players to the players list and we sort the orden to play
        String name1 = Leer.nextLine();
        Player player1 = new Player(name1, board, bank);
        players.addPlayer(player1);

        String name2 = Leer.nextLine();
        Player player2 = new Player(name2, board, bank);
        players.addPlayer(player2);

        String name3 = Leer.nextLine();
        Player player3 = new Player(name3, board, bank);
        players.addPlayer(player3);
        String name4 = Leer.nextLine();

        Player player4 = new Player(name4, board, bank);
        players.addPlayer(player4);

        // show the orden to play
        System.out.println("El orden a jugar es:\n");
        players.display();
        System.out.println("");
        // start game
        // iterate players list
        Player current = players.head;
        boolean ended = false;
        // while(!ended){
        for (int i = 0; i < 30; i++) {
            if (!current.isBroken) {
                System.out.println("Hola, soy " + current.name + " y estoy en " + current.position.name + " y tengo "
                        + current.money);
                current.moveAround();
            }
            // Is pair result?
            if (current.result.isPair) {
                System.out.println("saqué par");
            } else {
                current = current.next;
            }
            System.out.println();
        }
    }
    // // current.result.display();
    // // if (current.position.isOwnable && !current.position.isOwned) {
    // // bank.offerProperty(current.position, current);
    // // System.out.println(current.position.selling_price);
    // // // System.out.println(current.position.owner.name);
    // // // System.out.println(current.money);

}
