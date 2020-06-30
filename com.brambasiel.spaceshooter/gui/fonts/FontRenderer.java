package gui.fonts;

import java.awt.image.BufferedImage;

import engine.Game;

public class FontRenderer {
	
	public static void drawText(int xx, int yy, int size, String txt, Font font) {
		Game.g.translate(xx, yy);
		int x = 0;
		for (int i = 0; i < txt.length(); i++) {
			char c = txt.charAt(i);
			BufferedImage img = font.letters.get(c);
			if (img != null) {
				Game.g.drawImage(img, x*size-2, 0, size, size, null);
			}
			x++;
		}
		Game.g.translate(-xx, -yy);
	}
}
