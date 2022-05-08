package game;

import game.cards.Card;
import game.player.Player;

import java.io.IOException;
import java.util.Scanner;

public class Bank {
    float taxeMoney;
    Scanner Leer = new Scanner(System.in);

    public Bank() {
        this.taxeMoney = 0;
    }

    public void demandMoney(float amount, Player player) {
        player.giveMoney(amount);
    }

    public void pay(float amount, Player player) {
        player.getMoney(amount);
    }

    public void payGo(Player player) {
        this.pay(810000, player);
    }

    public void offerProperty(Card property, Player player) throws IOException {
        System.out.println("¿Deseeas comprar esta propiedad?\n1.Si\n2.No");
        int comprar = Leer.nextInt();
        if (comprar == 1) {
            this.tranferProperty(property, player);
        }
    }

    public void tranferProperty(Card property, Player player) throws IOException {
        demandMoney(property.selling_price, player);
        property.owner = player;
        property.isOwned = true;
        player.buyProperty(property.name);
    }

    public void removeProperty(Card property, Player player) {
        property.owner = null;
    }

    public void mortgageProperty(Card property, Player player) {
        if (property.isMortgable && !(property.isMortgabled) && property.owner == player) {
            property.isMortgabled = true;
            pay(property.selling_price / 2, player);
        }
    }

    public void demortgageProperty(Card property, Player player) {
        if (property.isMortgable && (property.isMortgabled) && property.owner == player) {
            property.isMortgabled = false;

        }
    }
}
