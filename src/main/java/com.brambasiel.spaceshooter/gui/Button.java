package com.brambasiel.spaceshooter.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.brambasiel.spaceshooter.BasicMath;
import com.brambasiel.spaceshooter.Input;
import com.brambasiel.spaceshooter.objects.GameObject;

public final class Button extends GameObject {
    private int width;
    private int height;

    private String text;
    private Color normal, hover, outline;

    private ButtonPressAction action;
    private boolean isBeingPressed;

    // TODO: Add layout capability so I don't need to enter this by hand
    public Button(String text, int w, int h) {
        super();
        this.width = w;
        this.height = h;
        this.text = text;
        colors(Color.WHITE, Color.GRAY);
        link(() -> {
            System.err.println("Button is not linked.");
        });
    }

    public Button position(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Button colors(Color normal, Color hover) {
        this.normal = normal;
        this.hover = hover;
        this.outline = normal.darker().darker().darker();
        return this;
    }

    public Button link(ButtonPressAction action) {
        this.action = action;
        return this;
    }

    @Override
    public void update(float delta, float timePassed) {
        if (Input.isMouseDown()) {
            // TODO: getBounds()
            if (!isBeingPressed && BasicMath.pointInRect(Input.getMousePosition(), (int) x, (int) y, width, height)) {
                action.pressed();
                isBeingPressed = true;
            }
        } else {
            isBeingPressed = false;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(isBeingPressed ? hover : normal);
        graphics.fillRect((int) x, (int) y, width, height);

        graphics.setColor(outline);
        graphics.drawRect((int) x, (int) y, width, height);

        int xx = (int) ((x + width / 2 - text.length()));
        int yy = (int) (y + height / 2);
        graphics.setColor(Color.black);
        graphics.drawString(text, xx, yy);
    }
}