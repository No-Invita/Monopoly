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

    public static void main(String[] args) {
        Player player1 = new Player("Elkin");
        Player player2 = new Player("Luis");
        Player player3 = new Player("Johan");
        Player player4 = new Player("Fabian");
        PlayerList list = new PlayerList();
        list.addPlayer(player1);
        list.addPlayer(player2);
        list.addPlayer(player3);
        list.addPlayer(player4);
        list.display();

    }

}
