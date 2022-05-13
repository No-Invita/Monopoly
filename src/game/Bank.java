package game;

import game.cards.Card;
import game.player.Player;
import game.specialcards.CardList;
import game.specialcards.CardNode;

import java.io.IOException;
import java.util.Scanner;

public class Bank {
    float taxeMoney;
    Scanner Leer = new Scanner(System.in);
    CardList luck;
    CardList ark;

    public Bank(CardList luck, CardList ark) {
        this.luck = luck;
        this.ark = ark;
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
        System.out
                .println("¿Deseeas comprar esta propiedad por " + property.selling_price + " ? (Destino)\n1.Si\n2.No");
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
                if (player.position.isOwnable) {

                    if (player.position.owner == null) {
                        offerProperty(player.position, player);
                    } else {
                        switch (player.position.type) {
                            case "transport": {
                                //Here go the code when de player falls on transport
                            }
                            case "service": {
                                //Here go the code when de player falls on service
                            }
                            default: {
                                transferMoney(player, player.position.owner, player.position.rental_price);
                                System.out.println(player.name + " transfiere " + player.position.rental_price + " a "
                                        + player.position.owner.name + " Por caer en " + player.position.name);
                            }
                        }
                    }
                } else {
                    switch (player.position.type) {
                        case "taxe": {
                            demandMoney(player.position.rental_price, player);
                            System.out.println(player.name + " pago impuesto a los tombos");
                            break;
                        }
                        case "luck": {
                            // Leees la tarjeta de fortuna
                            System.out.println("Estas en suerte");
                            CardNode card = luck.pickRandomNode();
                            System.out.println(card.description);
                            switch (card.type) {
                                case "dar": {
                                    demandMoney(card.param, player);
                                    System.out.println("pagué " + card.param);
                                    break;
                                }
                                case "recibir": {
                                    pay(card.param, player);
                                    System.out.println("recibí " + card.param);
                                    break;
                                }
                                case "salir": {
                                    if (player.isPrisoner) {
                                        System.out.println(
                                                "¿Deseas usar esta tarjeta o conservarla?\n1.Usar\2.Conservar");
                                        int choose = Leer.nextInt();
                                        if (choose == 1) {
                                            player.isPrisoner = false;
                                            System.out.println("Has salido de la carcel, celebralo curramba");
                                        } else {
                                            player.buyProperty(card.description);
                                        }
                                    } else {
                                        System.out.println("Como no estas preso, te la guardas, ojo se te cae");
                                        player.buyProperty(card.description);
                                    }
                                }
                            }
                            break;
                        }
                        case "ark": {
                            System.out.println("Estas en arca");
                            CardNode card = ark.pickRandomNode();
                            System.out.println(card.description);
                            switch (card.type) {
                                case "moverse a": {
                                    player.moveTo(card.param);
                                    System.out.println("me moví hasta " + player.position.name);
                                    break;
                                }
                                case "dar": {
                                    demandMoney(card.param, player);
                                    System.out.println("pagué " + card.param);
                                    break;
                                }
                                case "recibir": {
                                    pay(card.param, player);
                                    System.out.println("recibí " + card.param);
                                    break;
                                }
                                case "recibir de todos": {
                                    for (int i = 0; i < 3; i++) {
                                        Player other = player.next;
                                        demandMoney(card.param, other);
                                        pay(card.param, player);
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        case "gotojail": {
                            System.out.println("Te vas pa la carcel");
                            player.goJail();
                        }

                    }
                }
                break;
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
