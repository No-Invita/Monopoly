package game;

public class PlayerList {

    int playersNumber;
    Player head;
    Player tail;

    public PlayerList() {
        this.playersNumber = 0;
        this.head = null;
        this.tail = null;
    }

    public void addPlayer(Player player) {
        if (this.head == null) {
            this.head = player;
            this.tail = player;
        } else {
            this.tail.next = player;
            this.head.prev = player;
            player.prev = this.tail;
            player.next = this.head;
            this.tail = player;
        }
        this.playersNumber++;
        player.turno = this.playersNumber;
    }

    public void display() {
        Player current = head;

        do {
            System.out.print(current.name + " -> ");
            current = current.next;
        } while (current != head);
    }

}
