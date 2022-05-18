package inputs;
import game.GameSketch;

public class Console {
	public float x;
	public float y;
	public String chars;
	public int numChars;
	public boolean active;
	public int font;
	public GameSketch sketch;
	public String output = "";

	public Console(float x, float y, int font,GameSketch sketch) {
		this.x = x;
		this.y = y;
		active = false;
		this.font = font;
		this.chars = "";
		this.numChars = 0;
		this.sketch = sketch;
	}

	public void display() {
		sketch.line(x, y, x, y + font);
		sketch.textSize(font);
		sketch.text(this.chars, x, y);
		
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
		System.out.println(this.numChars);
		System.out.println(this.chars);
		this.chars = "";
	}

	public void deleteChar() {
		if (numChars > 0) {
			this.chars = this.chars.substring(0, this.chars.length() - 1);
			numChars -= 1;
		}
	}
}
