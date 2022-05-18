package game.pieces;

import game.player.Player;
import game.GameSketch;

public class Pieces {

  public int distancex;
  public int distancey;
  public int posx;
  public int posy;
  public String path = "src/images/";
  public int topex;
  public int topey;

  public Pieces(int x, int y, String path) {
    this.posx = x;
    this.posy = y;
    this.path += path;
    this.distancex = 60;
    this.distancey = 63;
    this.topex = this.posx - 676;
    this.topey = this.posy - 668;
  }

}
