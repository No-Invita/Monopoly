package game.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {

    public static void write(String fileName, String content) throws IOException {
        try {
            FileWriter writer = new FileWriter(fileName + ".txt", true);
            PrintWriter out = new PrintWriter(writer);
            out.println(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public static void writeall(String fileName, String[] content) throws IOException {

        try {
            FileWriter writer = new FileWriter(fileName + ".txt", false);
            PrintWriter out = new PrintWriter(writer);
            for (String x : content) {
                out.println(x);
            }

            out.println(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void appendall(String fileName, String[] content) throws IOException {

        try {
            FileWriter writer = new FileWriter(fileName + ".txt", true);
            PrintWriter out = new PrintWriter(writer);
            for (String x : content) {
                out.println(x);
            }

            out.println(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}