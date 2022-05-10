package game.specialcards;

public class LuckNode {
    public String description;
    public String type;
    public int param;
    public LuckNode next;
    public LuckNode prev;


    public LuckNode(String description, String type, int param){
        this.description = description;
        this.type = type;
        this.param = param;
        this.next = this;
        this.prev = this;
    }
}
