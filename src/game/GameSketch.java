package game;

import java.util.Random;
import game.player.Player;
import game.cards.Card;
import processing.core.PApplet;
import processing.core.PFont;
//import processing.core.PImage;
import processing.core.PImage;

public class GameSketch extends PApplet {

    //Todo esto es para leer datos atravez del teclado
    //Trasladar a una clase aparte
    Console c = new Console(10, 100, 48);
    String x = "";
    String space = " ";
    char spaces = space.charAt(0);
    PImage tablero, dadoLeft, dadoRight, asker;
    String[] dices = new String[6];    
    int i=0, total = 0, contador;  
    boolean cc = false, bAsker = true;
    PFont font;

    

    @Override
    public void settings() {
        size(960, 768);
    }

    @Override
    public void setup() {
        tablero = loadImage("src/images/monopoly.png");
        tablero.resize(768,740);
        dices[0] = "src/images/Dados/diceONE.png";
        dices[1] = "src/images/Dados/diceTWO.png";
        dices[2] = "src/images/Dados/diceTHREE.png";
        dices[3] = "src/images/Dados/diceFOUR.png";
        dices[4] = "src/images/Dados/diceFIVE.png";
        dices[5] = "src/images/Dados/diceSIX.png";
        asker = loadImage("src/images/asker.png");
        font = createFont("src/MonopolyFont/monopoly-inline.ttf",40);

        frameRate(30);
        //c.activate();
    }

    @Override
    public void draw() {
        image(tablero, 0, 0);
        if (cc == false){
            contador = millis()/1000;
            cc = true;            
        }
        useDices(contador);         
        tester();   
        //image(dadoLeft,794, 634);
        // c.display();
    }

    public void useDices(int x){      
        int time = (millis()/1000)-x;                                    
        if(time < 5){
            int valor1 = (int)Math.ceil(Math.random()*5),
            valor2 = (int)Math.ceil(Math.random()*5);
            total = valor1 + valor2 + 2;                        
            dadoLeft = loadImage(dices[valor1]);            
            dadoRight = loadImage(dices[valor2]);
            image(dadoLeft, 794, 634);
            image(dadoRight, 870, 634);
            i++;
            if(i == 6){
                i = 0;
            }            
        }                      
    }

    public void askToBuy (Player p, Card c){
        bAsker = true;
        if(p.position.isOwnable == true) {
            image(asker, 145, 253);
        {
            textSize(20);
            textFont(font);
            text(c.name +
             " \n está disponible para la venta\n ¿Deseas comprarlo? \n\nRecuerda que hoy no fio, mañana si",
              154,264);
        }
        }
    }
        public void tester(){             
            if(bAsker == true){
                image(asker, 145, 253);
            {                
                textFont(font);
                fill(0,0,0);
                text("",314,329);
                text("está disponible para la venta",300,328);
                text("¿Deseas comprarlo/a ahora?",298,349);
                text("Recuerda que hoy no fio, mañana sí.",292,375);
            }
            this.mouseClicked();
            }                                 
        }

        public void mouseClicked() {
            if (mousePressed == true) {
                if (mouseButton == LEFT) {
                    if(bAsker == true){
                        if (mouseX > 211 && mouseX < 299 ) {
                            if (mouseY > 429  && mouseY < 429+37) {
                                bAsker = false;
            
                            }
                        }

                        if (mouseX > 470 && mouseX < 470+88) {
                            if (mouseY > 429  && mouseY < 429+37) {
                                bAsker = false;            
                            }
                        }
                    }
                }
            }
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
