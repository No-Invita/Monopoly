package game;

public class Player {

    String name;
    int turno;
    float money;
    int x;
    boolean prisoner;
    Player  next;
    Player prev;

    public Player(String name, float money) {
        this.name = name;
        this.money = money;
        this.prisoner = false;
        this.x = 0;
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
        this.x = 10;
    }
}
