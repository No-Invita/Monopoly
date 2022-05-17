package game.player;

import game.dices.dicesResult;

public class PlayerList {

    public int playersNumber;
    public Player head;
    public Player tail;
    public dicesResult result;
    int mayor = 0;
    

    public PlayerList() {
        this.playersNumber = 0;
        this.head = null;
        this.tail = null;
    }

    public void addPlayer(Player player) {
        player.rollDices();
        System.out.println();
        if (this.head == null) {
            this.head = player;
            this.tail = player;
        } else {
            if (player.result.result <= mayor) {
                this.tail.next = player;
                this.head.prev = player;
                player.prev = this.tail;
                player.next = this.head;
                this.tail = player;
            } else {
                if(player.result.result > head.result.result) {
                    this.tail.next = player;
                    this.head.prev = player;
                    player.prev = this.tail;
                    player.next = this.head;
                    this.head = player;
                }else{
                    Player aux = player;
                    Player tail_aux = tail; 
                    while(aux.result.result  > tail_aux.result.result){
                       tail_aux = tail_aux.prev;    
                    }
                    tail_aux.next.prev = player;
                    player.next = tail_aux.next;
                    player.prev = tail_aux;
                    tail_aux.next = player;
                }
               
            }

        }
        mayor = tail.result.result;
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
