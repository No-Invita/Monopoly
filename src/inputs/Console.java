package inputs;

import game.GameSketch;

public class Console {
	float x;
	float y;
	String chars;
	int numChars;
	boolean active;
	int font;
	GameSketch sketch;

	public Console(float x, float y, int font, GameSketch sketch) {
		this.x = x;
		this.y = y;
		active = false;
		this.font = font;
		chars = "";
		numChars = 0;
		this.sketch = sketch;
	}

	public void display() {
		sketch.line(x, y, x, y + font);
		sketch.textSize(font);
		sketch.text(chars, x, y);
	}

	public void addChar(char c) {
		chars += c;
		numChars++;
	}

	public String readString() {
		return chars;
	}

	public boolean isActive() {
		return active;
	}

	public void activate() {
		active = true;
	}

	public void deactivate() {
		active = false;
	}

	public void reset() {
		chars = "";
	}

	public void deleteChar() {
		if (numChars > 0) {
			chars = chars.substring(0, chars.length() - 1);
			numChars -= 1;
		}
	}
}