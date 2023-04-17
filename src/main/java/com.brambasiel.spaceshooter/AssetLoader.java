package com.brambasiel.spaceshooter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import com.brambasiel.spaceshooter.gui.PixmapFont;

public class AssetLoader {

    private static AssetLoader instance;

    public static AssetLoader getInstance() {
        if (instance == null) {
            instance = new AssetLoader();
        }
        return instance;
    }

    private final Logger logger = Logger.getLogger(AssetLoader.class.getName());
    private final Map<String, BufferedImage> sprites;
    private final Map<Color, PixmapFont> fonts;

    public AssetLoader() {
        // load sprites
        sprites = new HashMap<>();
        File f = new File("res");
        for (String p : f.list()) {
            if (p.endsWith(".png")) {
                loadImage(p);
                logger.info("Loaded " + p);
            }
        }

        // load fonts
        fonts = new HashMap<>();
        // TODO: combine into one font
        fonts.put(Color.white, new PixmapFont(8, 8, "white"));
        fonts.put(Color.black, new PixmapFont(8, 8, "black"));
        fonts.put(Color.green, new PixmapFont(8, 8, "green"));
        fonts.put(Color.blue, new PixmapFont(8, 8, "blue"));
        fonts.put(Color.yellow, new PixmapFont(8, 8, "yellow"));
        fonts.put(Color.red, new PixmapFont(8, 8, "red"));

        logger.info("Loaded resources");
    }

    private BufferedImage loadImage(String name) {
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

    public PixmapFont getFont(Color col) {
        return fonts.get(col);
    }

    public BufferedImage getRandomAsteroidTexture() {
        int randomIndex = BasicMath.randInt(5);
        String textureName = String.format("asteroid%d.png", randomIndex);
        return getTexture(textureName);
    }
}
