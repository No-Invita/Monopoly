package game;

import game.cards.Card;
import game.dices.dicesResult;

public class Player {

    String name;
    int turno;
    float money;
    Card position;
    boolean prisoner;
    Player next;
    Player prev;

    public Player(String name, float money) {
        this.name = name;
        this.money = money;
        this.prisoner = false;
        this.position = null;
        prev = this;
        next = this;
    }

    public void giveMoney(float amount) {
        if (amount < this.money) {
            // Here go the action selling ownerships or go bankrupt
        } else {
            this.money -= amount;

        }
    }

    public void getMoney(float amount) {
        this.money += amount;
    }

    public void goJail() {
        this.prisoner = true;
        // this.position = 10;
    }

    public void moveAround(dicesResult result) {
        for (int i = 0; i < result.result; i++) {
            moveForward();
        }
    }

    public boolean isInGo() {
        return position.index == 0;
    }

    public void moveForward() {
        position = position.next;
        if (isInGo()) {
            // cobrar dinero al pasar por salida
        }
    }

    public void moveBackward() {
        position = position.prev;
    }
}
