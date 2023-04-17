package com.brambasiel.spaceshooter.states.menu;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.brambasiel.spaceshooter.BasicMath;
import com.brambasiel.spaceshooter.AssetLoader;
import com.brambasiel.spaceshooter.GameWindow;
import com.brambasiel.spaceshooter.objects.GameObject;

public class Asteroid extends GameObject {

    private float speed;
    private int size;
    private BufferedImage sprite;

    public Asteroid() {
        super(0.f, 0.f);
        restart();
    }

    void restart() {
        x = BasicMath.randFloat(GameWindow.WIDTH);
        speed = BasicMath.randFloat(18f, 32f);
        size = BasicMath.randInt(14, 14 * 4);
        y = BasicMath.randFloat(-GameWindow.HEIGHT - size);
        sprite = AssetLoader.getInstance().getRandomAsteroidTexture();
    }

    @Override
    public void update(float delta, float timePassed) {
        y += speed * delta;
        if (y > GameWindow.HEIGHT + size) {
            restart();
        }
    }

    @Override
    public void draw(Graphics2D graph) {
        graph.drawImage(sprite, (int) x, (int) y, size, size, null);
    }
}
