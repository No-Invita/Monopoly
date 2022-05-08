package game.cards;

import game.player.Player;

public class Go extends Card {

    public Go() {
        this.name = "Go";
        this.isOwnable = false;
        this.isOwned = false;
        this.owner = null;
        this.index = 0;
        this.next = this;
        this.prev = this;
    }
}