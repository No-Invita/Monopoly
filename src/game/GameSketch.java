package game;

import processing.core.PApplet;

public class GameSketch extends PApplet{


    @Override
    public void settings() {
        size(1366, 768);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}
