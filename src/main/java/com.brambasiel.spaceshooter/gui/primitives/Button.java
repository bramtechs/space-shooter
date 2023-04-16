package gui.primitives;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.BasicMath;
import input.Input;
import objects.GameObject;

public final class Button extends GameObject<Button> {
	private int width;
	private int height;

	private String text;
	private Color normal, hover, outline;

	private ButtonPressAction action;
	private boolean isBeingPressed;

	// TODO: Add layout capability so I don't need to enter this by hand
	public Button(String text, int w, int h) {
		this.width = w;
		this.height = h;
		this.text = text;
		colors(Color.WHITE, Color.GRAY);
		link(() -> {
			System.err.println("Button is not linked.");
		});
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