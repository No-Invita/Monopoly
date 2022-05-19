package game.dices;

public class dicesResult {
    public int[] results;
    public boolean isPair = false;
    public int result = 0;
    public String[] dices = new String[6];
    public String[] pathDices = new String[2];

    public dicesResult(int[] results, boolean isPair, String[] pathDices) {
        this.results = results;
        this.isPair = isPair;
        this.result = results[0] + results[1];
        this.pathDices[0] = pathDices[0];
        this.pathDices[0] = pathDices[1];

    }

    public void display() {
        System.out.println(results[0] + " " + results[1] + " " + isPair + " " + result);
    }

}
