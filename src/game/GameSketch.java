package game;

import processing.core.PApplet;
//import processing.core.PImage;

public class GameSketch extends PApplet {

    //Todo esto es para leer datos atravez del teclado
    //Trasladar a una clase aparte
    Console c = new Console(10, 100, 48);
    String x = "";
    String space = " ";
    char spaces = space.charAt(0);

    @Override
    public void settings() {
        size(960, 768);

    }

    @Override
    public void setup() {

        c.activate();
    }

    @Override
    public void draw() {
        background(0);
        c.display();
    }

    public void keyPressed() {
        if (keyAnalyzer(key).compareTo("LETTER") == 0 || keyAnalyzer(key).compareTo("NUMBER") == 0) {
            c.addChar(key);
        }
        if (keyCode == BACKSPACE) {
            c.deleteChar();
        }
       
        if (keyCode == ENTER) {
            x = c.chars;
            System.out.println(x);
            c.reset();
        }
    }

    public String keyAnalyzer(char c) {
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8'
                || c == '9') {
            return "NUMBER";
        } else if (c == 'A' || c == 'a' || c == 'B' || c == 'b' || c == 'C' || c == 'c' || c == 'D' || c == 'd'
                || c == 'E' || c == 'e' ||
                c == 'F' || c == 'f' || c == 'G' || c == 'g' || c == 'H' || c == 'h' || c == 'I' || c == 'i' || c == 'J'
                || c == 'j' ||
                c == 'K' || c == 'k' || c == 'L' || c == 'l' || c == 'M' || c == 'm' || c == 'N' || c == 'n'|| c == 'Ñ' || c == 'ñ'  || c == 'O'
                || c == 'o' ||
                c == 'P' || c == 'p' || c == 'Q' || c == 'q' || c == 'R' || c == 'r' || c == 'S' || c == 's' || c == 'T'
                || c == 't' ||
                c == 'U' || c == 'u' || c == 'V' || c == 'v' || c == 'W' || c == 'w' || c == 'X' || c == 'x' || c == 'Y'
                || c == 'y' ||
                c == 'Z' || c == 'z' || c==' ') {
            return "LETTER";
        } else {
            return "OTHER";
        }
    }

    public class Console {
        float x;
        float y;
        String chars;
        int numChars;
        boolean active;
        int font;

        Console(float x, float y, int font) {
            this.x = x;
            this.y = y;
            active = false;
            this.font = font;
            chars = "";
            numChars = 0;
        }

        void display() {
            line(x, y, x, y + font);
            textSize(font);
            text(chars, x, y);
        }

        void addChar(char c) {
            chars += c;
            numChars++;
        }

        String readString() {
            return chars;
        }

        boolean isActive() {
            return active;
        }

        void activate() {
            active = true;
        }

        void deactivate() {
            active = false;
        }

        void reset() {
            chars = "";
        }

        void deleteChar() {
            if (numChars > 0) {
                chars = chars.substring(0, chars.length() - 1);
                numChars -= 1;
            }
        }
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }

}
