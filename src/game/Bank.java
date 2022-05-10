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

    public void demortgageProperty(Card property, Player player) {
        if (property.isMortgable && (property.isMortgabled) && property.owner == player) {
            property.isMortgabled = false;

        }
    }

    public void mortgageProperty(Card property, Player player) {
        if (property.isMortgable && !(property.isMortgabled) && property.owner == player) {
            property.isMortgabled = true;
            pay(property.selling_price / 2, player);
        }
    }

    public void offerProperty(Card property, Player player) throws IOException {
        System.out.println("¿Deseeas comprar esta propiedad?\n1.Si\n2.No");
        int comprar = Leer.nextInt();
        if (comprar == 1) {
            this.tranferProperty(property, player);
        }
    }

    public void pay(float amount, Player player) {
        player.getMoney(amount);
    }

    public void payGo(Player player) {
        this.pay(810000, player);
    }

    public void removeProperty(Card property, Player player) {
        property.owner = null;
    }

    public void request(String type, Player player) throws IOException {
        switch (type) {
            case "buy": {

                if (player.position.owner == null) {
                    offerProperty(player.position, player);
                } else {
                    transferMoney(player, player.position.owner, player.position.rental_price);
                    System.out.println(player.name + " transfiere " + player.position.rental_price + " a "
                            + player.position.owner.name + " Por caer en " + player.position + " Propiedad de "
                            + player.position.owner);
                }
                break;
            }
            case "taxe": {
                demandMoney(player.position.rental_price, player);
                System.out.println(player.name + " pago impuesto a los tombos");
                break;
            }
            case "luck": {
                // Here go when the player falls on luck
            }
            case "Arca": {
                // Here go when the player falls on Arca
            }

            case "go": {
                payGo(player);
            }
        }

    }

    public void transferMoney(Player payer, Player receiver, int amount) throws IOException {
        if (payer.money >= amount) {
            payer.giveMoney(amount);
            receiver.getMoney(amount);
        } else {
            System.out.println("not enough money");
        }
    }

    public void tranferProperty(Card property, Player player) throws IOException {
        if (player.money >= property.selling_price) {
            demandMoney(property.selling_price, player);
            property.owner = player;
            property.isOwned = true;
            player.buyProperty(property.name);
        } else {
            System.out.println("not enough money");
        }
    }

}
