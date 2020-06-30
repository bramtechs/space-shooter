package engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;

import javax.imageio.ImageIO;

import graphics.SpriteSheet;
import gui.fonts.FontLoader;
import sun.security.util.Debug;

public class MasterLoader {
	
	HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	public MasterLoader() {
		FontLoader.Load();
		Game.print("Loaded resources");
		
		File f = new File("res");
		for (String p : f.list()) {
			if (p.endsWith(".png")) {
				loadImage(p);
				Game.print("Loaded " + p);
			}
		}
		
		Game.print("Loaded sprites.");
	}
	
	private BufferedImage loadImage(String name){
		try {
			BufferedImage image = ImageIO.read(new File("res/" + name + ".png"));
			sprites.put(name, image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedImage getTexture(String name) {
		return sprites.getOrDefault(name, sprites.get("warning0"));
	}
}
