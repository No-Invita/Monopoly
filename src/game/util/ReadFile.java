package game.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class ReadFile {
    public static String[] read(String file_name) {
        String[] lines = new String[1];

        try {
            BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
            String line = "";
            String temp = new String();
            while ((line = br.readLine()) != null) {
                temp += line + "\n";
            }

            lines = temp.split("\n");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        String[] places = read("src/data/casillas");
        for (String place : places) {
            System.out.println(place);
        }
    }
}
