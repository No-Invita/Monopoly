package game;

import game.cards.Card;
import game.dices.dices;
import game.dices.dicesResult;

public class Player {

    public String name;
    public int turno;
    public float money;
    public Card position;
    public boolean prisoner;
    public Player next;
    public Player prev;
	public dicesResult result;

    public Player(String name) {
        this.name = name;
        this.money = 6000000;
        this.prisoner = false;
        this.position = null;
        prev = this;
        next = this;
    }
    
    public void getMoney(float amount) {
        this.money += amount;
    }

    public void giveMoney(float amount) {
        if (amount < this.money) {
            // Here go the action selling ownerships or go bankrupt
        } else {
            this.money -= amount;

        }
    }

    public void goJail() {
        this.prisoner = true;
        // this.position = 10;
    }

    public boolean isInGo() {
        return position.index == 0;
    }

    public void moveAround() {
        rollDices();
        result.display();
        for (int i = 0; i < result.result; i++) {
            moveForward();
        }
    }

    public void moveBackward() {
        position = position.prev;
    }

    public void moveForward() {
        position = position.next;
        if (isInGo()) {
            // cobrar dinero al pasar por salida
        }
    }

    public void rollDices(){
        dicesResult result = dices.rollDices();
        this.result = result;
    }
}
