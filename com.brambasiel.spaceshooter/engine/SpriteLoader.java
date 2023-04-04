package engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import gui.fonts.FontLoader;

public class SpriteLoader {

	private static final HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();

	public static void load() {
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

	private static BufferedImage loadImage(String name) {
		try {
			BufferedImage image = ImageIO.read(new File("res/" + name + ".png"));
			sprites.put(name, image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage getTexture(String name) {
		return sprites.getOrDefault(name, sprites.get("warning0"));
	}

	public static BufferedImage getRandomAsteroidTexture() {
		int randomIndex = BasicMath.randInt(5);
		String textureName = String.format("asteroid%d.png", randomIndex);
		return getTexture(textureName);
	}
}
