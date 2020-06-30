package gui.fonts;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import engine.Game;
import graphics.SpriteLoader;

public class Font {

	public HashMap<Character, BufferedImage> letters;
	public String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.!";

	public Font(int w, int h, String extra) {
		try {
			BufferedImage pic = SpriteLoader.loadImage("font/pixelfont_" + extra + ".png");
			BufferedImage[] pics = loadFont(w, h, pic);
			letters = new HashMap<Character, BufferedImage>();
			for (int i = 0; i < pics.length; i++) {
				letters.put(c.charAt(i), pics[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	BufferedImage[] loadFont(int w, int h, BufferedImage image) {

		try {
			int ww = image.getWidth() / w;
			int hh = image.getHeight() / h;
			BufferedImage[] imgs = new BufferedImage[w * h];

			int i = 0;
			for (int y = 0; y < w; y++) {
				for (int x = 0; x < h; x++) {
					imgs[i] = image.getSubimage(x*ww, y*hh, ww, hh);
					i++;
				}
			}
			return imgs;
		} catch (Exception e) {
			Game.print("Could not load font png : " + e.getMessage());
		}
		return null;
	}
}
