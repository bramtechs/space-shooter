package com.brambasiel.spaceshooter.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Logger;

import com.brambasiel.spaceshooter.AssetLoader;

public class PixmapFont {

	private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.!";
	private static Logger logger = Logger.getAnonymousLogger();
	private HashMap<Character, BufferedImage> letters;

	public PixmapFont(int w, int h, String extra) {
		try {
			BufferedImage pic = AssetLoader.getTexture("font/pixelfont_" + extra + ".png");
			BufferedImage[] pics = loadFont(w, h, pic);
			letters = new HashMap<>();
			for (int i = 0; i < pics.length; i++) {
				letters.put(LETTERS.charAt(i), pics[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private BufferedImage[] loadFont(int w, int h, BufferedImage image) {
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
			logger.warning("Could not load font png : " + e.getMessage());
		}
		return null;
	}

	public void drawText(Graphics2D graph, int xx, int yy, int size, String txt) {
		graph.translate(xx, yy);
		int x = 0;
		for (int i = 0; i < txt.length(); i++) {
			char c = txt.charAt(i);
			BufferedImage img = letters.get(c);
			if (img != null) {
				graph.drawImage(img, x*size-2, 0, size, size, null);
			}
			x++;
		}
		graph.translate(-xx, -yy);
	}
}
