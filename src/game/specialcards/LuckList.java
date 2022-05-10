package game.specialcards;

import game.util.ReadFile;

public class LuckList {
    public LuckNode head;
    public LuckNode tail;
    int size = 0;

    public void add(String description, String type, int param) {
        LuckNode p = new LuckNode(description, type, param);

        if (head == null) {
            head = tail = p;
        } else {
            tail.next = p;
            p.next = head;
            tail = p;
        }
        size++;
    }

    public void display() {
        if (head == null) {
            System.out.println("Lista vacia");
        } else {
            LuckNode p = head;
            System.out.println("The nodes of the singly linked list are: ");
            do {
                System.out.print(p.type + " -> ");
                p = p.next;
            } while (p != head);
            if (p == head)
                System.out.print("null\n");
        }
    }

    public void loadList(String[] leer) {
        for (String place : leer) {
            String[] piece = place.split(";");
            add(
                    piece[0].trim(),
                    piece[1].trim(),
                    Integer.parseInt(piece[2].trim()));
        }
    }

    public LuckNode pickRandomNode() {
        int random = (int) Math.ceil(Math.random() * size);

        LuckNode p = head;
        for (int i = 0; i < random; i++) {
            p = p.next;
        }
        return p;
    }

    public static void main(String[] args) {
        String[] leer = ReadFile.read("src/data/luck");

        LuckList x = new LuckList();
        x.loadList(leer);
        x.display();
    }
}
