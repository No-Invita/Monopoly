package game.player;

import java.io.IOException;

import game.Bank;
import game.Board;
import game.cards.Card;
import game.dices.dices;
import game.dices.dicesResult;
import game.util.WriteFile;

public class Player {

    public String name;
    public int turno;
    public float money;
    public Card position;
    public boolean prisoner;
    public Player next;
    public Player prev;
    public dicesResult result;
    public String properties;
    public Bank bank;

    public Player(String name, Board board, Bank bank) throws IOException {
        this.name = name;
        this.money = 6000000;
        this.prisoner = false;
        this.position = board.start;
        this.bank = bank;
        this.properties = "src/data/playersproperties/" + name;
        prev = this;
        next = this;
        WriteFile.createFile(properties);
    }

    public void getMoney(float amount) {
        this.money += amount;
    }

    public void giveMoney(float amount) {
        if (amount < this.money) {
            this.money -= amount;
        } else {

        }
    }

    public void goJail() {
        this.prisoner = true;
        // this.position = 10;
    }

    public boolean isInGo() {
        return position.index == 0;
    }

    public void moveAround() throws IOException {
        System.out.println("Saque");
        rollDices();
        for (int i = 0; i < result.result; i++) {
            moveForward();
        }
        System.out.println(position.name);
        if (!position.name.equals("Arca") && !position.name.equals("Suerte") && !position.name.equals("Tombos")
                && !position.name.equals("Sincelejo") && !position.name.equals("Carcel Modelo")
                && !position.name.equals("Vayase a la carcel") && !position.name.equals("Volcan del totumo")) {
            bank.request("buy", this);
        }else{
            //Here go the code when the player falls on a luck or arca or carcel or Sinecelejo or tomobos
            // or vayase a la carcel or volcan del totumo
        }

    }

    public void moveBackward() {
        position = position.prev;
    }

    public void moveForward() throws IOException {
        position = position.next;
        if (isInGo()) {
            bank.request("go", this);
            System.out.println("pasé por salida");
        }
    }

    public void rollDices() {
        dicesResult result = dices.rollDices();
        this.result = result;
        result.display();
    }

    public void buyProperty(String name) throws IOException {
        WriteFile.write(properties, name);
    }
}
