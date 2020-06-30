package gui.primitives;

import java.awt.Color;

import engine.Game;
import gui.Widget;
import input.Input;
import input.Mouse;

public class Button extends Widget {

	String text = "Button";
	int centerX, centerY;
	Color normal, hover, outline;
	boolean pressed = false;
	
	public Button(int x, int y, int w, int h, String text, Color normal, Color hover) {
		super(x,y);
		this.w = w;
		this.h = h;
		this.text = text;
		this.normal = normal;
		this.hover = hover;
		this.outline = normal.darker().darker().darker();
	}
	
	@Override
	public void update() {
		if (Input.isMouseDown()) {
			pressed = engine.BasicMath.pointInRect(Input.getMousePosition(), x, y, w, h);
			if (pressed) {
				pressed();
			}
		}
	}
	
	public void pressed() {
		Game.print("Pressed " + text);
	}
	
	@Override
	public void draw() {
		if (pressed)
			Game.g.setColor(hover);
		else
			Game.g.setColor(normal);
		Game.g.fillRect(x, y, w, h);

		Game.g.setColor(outline);
		Game.g.drawRect(x, y, w, h);
		
		int xx = (int)((x+w/2-text.length()));
		int yy = (int)(y+h/2);
		Game.g.setColor(Color.black);
		Game.g.drawString(text, xx, yy);
	}
}
