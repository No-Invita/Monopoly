package game.specialcards;

public class CardNode {
    public String description;
    public String type;
    public int param;
    public CardNode next;
    public CardNode prev;

    public CardNode(String description, String type, int param) {
        this.description = description;
        this.type = type;
        this.param = param;
        this.next = this;
        this.prev = this;
    }
}
