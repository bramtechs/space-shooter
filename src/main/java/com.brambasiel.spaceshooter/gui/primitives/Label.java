package gui.primitives;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.fonts.FontLoader;
import gui.fonts.FontRenderer;
import objects.GameObject;

public class Label extends GameObject {

	private String text;
	private Color col;
	
	private boolean flashes;
	private float timer;
	
	public Label(int x, int y) {
		super(x,y);
	}
	
	public Label flashes() {
		this.flashes = true;
		return this;
	}

	@Override
	public void update(float delta, float timePassed) {
		timer += timePassed;
		if (timer > 1.0f) {
			timer = 0.f;
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		if (!flashes || timer < 0.5f) {
			FontRenderer.drawText((int)x, (int)y, 12, text, FontLoader.getFont(col));
		}
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void setColor(Color col) {
		this.col = col;
	}
}
