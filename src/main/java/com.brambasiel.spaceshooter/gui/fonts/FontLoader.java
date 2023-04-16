package gui.fonts;

import java.awt.Color;

public class FontLoader {
	public static Font white, black, green, blue, red, yellow;

	public static void Load() {
		white = new Font(8, 8, "white");
		black = new Font(8, 8, "black");
		green = new Font(8, 8, "green");
		blue = new Font(8, 8, "blue");
		yellow = new Font(8,8,"yellow");
		red = new Font(8, 8, "red");
	}
	
	TextColor col;
	enum TextColor{
		Black, Blue, Green, Red, White
	}
	
	public static Font getFont(Color col) {
		Font font = white;
		
		if (col == Color.white) {
			font = white;
		}
		else if (col == Color.black) {
			font = black;
		}
		else if (col == Color.green) {
			font = green;
		}
		else if (col == Color.blue) {
			font = blue;
		}
		else if (col == Color.yellow) {
			font = yellow;
		}
		else if (col == Color.red) {
			font = red;
		}
		
		return font;
	}
}
