package game;

import game.cards.*;
import game.util.ReadFile;

public class Board {

    Card start = null;
    Card end = null;
    int index = -1;

    public void addBox(String name, int selling_price, int rental_price, String cardType) {
        index++;
        Card box = null;

        box = determineType(name, selling_price, rental_price, cardType, box);

        if (start == null) {
            start = box;
            end = box;
            box.index = index;
        } else {
            end.next = box;
            box.prev = end;
            end = box;
            box.next = start;
            start.prev = box;
            box.index = index;
        }

    }

    private Card determineType(String name, int selling_price, int rental_price, String cardType, Card box) {
        switch (cardType) {
            case "go": {
                box = new Go();
                break;
            }

            case "ownership": {
                box = new Ownership(name, selling_price, rental_price);
                break;
            }

            // case "card": {
            //     // box = new Card();
            //     break;
            // }

            case "jail": {
                box = new Jail();
                break;
            }

            // case "taxes": {
            //     // box = new Taxes();
            //     break;
            // }

            // case "transport": {
            //     // box = new Transport();
            //     break;
            // }

            // case "luck": {
            //     // box = new Luck();
            //     break;
            // }

            // case "free stop": {
            //     // box = new FreeStop();
            //     break;
            // }

            // case "service": {
            //     // box = new Service();
            //     break;
            // }
            default: {
                box = new Ownership(name, selling_price, rental_price);
            }
        }
        return box;
    }

    public void loadBoard(String[] leer) {
        for (String place : leer) {
            String[] piece = place.split(",");
            addBox(
                    piece[0],
                    Integer.parseInt(piece[1]),
                    Integer.parseInt(piece[2]), piece[3]);
        }
    }

    public static void main(String[] args) {
        String[] leer = ReadFile.read("src/data/casillas");
        Board tablero = new Board();
        tablero.loadBoard(leer);

        Card current = tablero.start;
        do {
            System.out.print(current.name + " -> ");
            current = current.next;
        } while (current != tablero.start);

    }
}
