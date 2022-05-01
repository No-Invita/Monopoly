package game.dices;

public class dices {
    public static int max_value = 6;

    public static dicesResult rollDices() {
        int first =(int) Math.ceil(Math.random() * max_value);
        int last =(int) Math.ceil(Math.random() * max_value);

        int[] results = {first, last};
        dicesResult result = new dicesResult(results, isPair(first, last));
        return result;
    }

    public static boolean isPair(int first, int last){
        return first == last ? true : false;
    }

    public static void main(String[] args) {
        for (int index = 0; index < 10; index++) {
           dicesResult result = rollDices();
           result.display();
        }
    }
}
