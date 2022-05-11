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
    public boolean isPrisoner;
    public boolean isBroken;
    public Player next;
    public Player prev;
    public dicesResult result;
    public String properties;
    public Bank bank;

    public Player(String name, Board board, Bank bank) throws IOException {
        this.name = name;
        this.money = 6000000;
        this.isPrisoner = false;
        this.isBroken = false;
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
        this.isPrisoner = true;
        while (position.index != 10) {
            position = position.next;
        }
        // this.position = 10;
    }

    public boolean isInGo() {
        return position.index == 0;
    }

    public void moveAround() throws IOException {
        rollDices();
        for (int i = 0; i < result.result; i++) {
            moveForward();
        }
        System.out.println(position.name);
        bank.request("buy", this);
    }

    public void moveBackward() {
        position = position.prev;
    }

    public void moveForward() throws IOException {
        position = position.next;
        if (isInGo()) {
            bank.request("go", this);
            System.out.println("pase por salida");
        }
    }

    public void moveTo(int pos) throws IOException {
        do {
            moveForward();
        } while (position.index != pos);
    }

    public void rollDices() {
        dicesResult result = dices.rollDices();
        this.result = result;
    }

    public void buyProperty(String name) throws IOException {
        WriteFile.write(properties, name);
    }
}