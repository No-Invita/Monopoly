package game.pieces;

import game.player.Player;

public class Pieces {

  public int distance;
  public int posx;
  public int posy;
  public String path = "src/images/";

  public Pieces(int x, int y, String path) {
    this.posx = x;
    this.posy = y;
    this.path += path;
  }

}
