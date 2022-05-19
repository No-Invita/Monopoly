package game.dices;

public class dices {
    public static String[] dices = new String[6];
    public static String[] pahtDices = new String[2];

    public static void upLoad() {
        for (int i = 0; i < 6; i++){
            dices[i] = "src/images/dice" + (i + 1) + ".png";
        }
           
    }

    public static int max_value = 6;

    public static dicesResult rollDices() {
        int first = (int) Math.ceil(Math.random() * max_value);
        pahtDices[0] = dices[first - 1];
        int last = (int) Math.ceil(Math.random() * max_value);
        pahtDices[1] = dices[last - 1];
        int[] results = { first, last };
        dicesResult result = new dicesResult(results, isPair(first, last), pahtDices);
        return result;
    }

    public static boolean isPair(int first, int last) {
        return first == last ? true : false;
    }

    public static void main(String[] args) {
        for (int index = 0; index < 10; index++) {
            dicesResult result = rollDices();
            result.display();
        }
    }
}
