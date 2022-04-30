package game;

public class board {

    Ownerships start = null;
    Ownerships end = null;
    int index = -1;

    public void addBox(String name, int selling_price, int rental_price) {
        index++;
        Ownerships box = new Ownerships(name, selling_price, rental_price);

        if (start == null) {
            start = box;
            end = box;
            box.index = index;
        } else {
            end.next = box;
            box.prev = end;
            end = box;
            box.next = start;
            start.prev = box;
            box.index = index;
        }
        
    }
}
