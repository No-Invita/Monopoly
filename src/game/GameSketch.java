package game;

import processing.core.PApplet;
import processing.core.PImage;

public class GameSketch extends PApplet {
    PImage img;
    @Override
    public void settings() {
        size(1366, 768);
        fullScreen();
    }

    @Override
    public void setup() {
        
        img = loadImage("src/images/monopoly.png");
        img.resize(1000, 768);

    }
    @Override
    public void draw() {
        
        image(img,365,0);
    
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}
