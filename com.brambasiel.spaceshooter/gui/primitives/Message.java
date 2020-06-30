package gui.primitives;

import java.awt.Color;

import engine.Game;
import gui.fonts.FontLoader;
import gui.fonts.FontRenderer;
import objects.GameObject;
import objects.Handler;
import objects.ID;

public class Message extends GameObject {
	
	float timer;
	String text;
	Color col;
	
	public Message(String text, Color col) {
		super(Game.w/2f-text.length()*9,Game.h/2f,ID.GUI);
		this.text = text;
		this.col = col;
	}

	@Override
	public void update() {
		timer += Game.deltaTime;
		if (timer > 3f) {
			Handler.obj.deleteObj(this);
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		FontRenderer.drawText((int)x, (int)y, 18, text, FontLoader.getFont(col));
	}
}
