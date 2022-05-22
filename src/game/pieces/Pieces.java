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

  public Pieces(String path) {
    
    this.path += path;
    this.distancex = 63;
    this.distancey = 62 ;
    
  }

}
