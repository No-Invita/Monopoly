package game.dices;

public class dicesResult {
    public int[] results;
    public boolean isPair = false;
    public int result = 0;

    public dicesResult(int[] results, boolean isPair) {
        this.results = results;
        this.isPair = isPair;
        this.result = results[0] + results[1];
    }

    public void display() {
        System.out.println(results[0] + " " + results[1] + " " + isPair + " "+result);
    }

}
