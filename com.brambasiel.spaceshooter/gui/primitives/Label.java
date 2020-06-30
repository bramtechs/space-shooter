package gui.primitives;

import java.awt.Color;

import engine.Game;
import gui.Widget;
import gui.fonts.FontLoader;
import gui.fonts.FontRenderer;

public class Label extends Widget{

	String text;
	Color col;
	
	boolean flash;
	float timer;
	
	public Label(int x, int y, boolean flash) {
		super(x,y);
		this.flash = flash;
	}
	
	public void change(String text) {
		this.text = text;
	}
	public void change(String text, Color c, boolean flash) {
		this.text = text;
		this.col = c;
		this.flash = flash;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!flash) {
			timer = 0f;
			return;
		}
		
		timer +=  Game.deltaTime;
		if (timer > 1f) {
			timer = 0f;
		}
	}

	@Override
	public void draw() {
		if (text == null) text = " ";
				
		if (timer < 0.5f) {
			FontRenderer.drawText(x, y, 12, text, FontLoader.getFont(col));
		}
	}
	
}
