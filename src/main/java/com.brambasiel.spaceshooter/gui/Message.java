package com.brambasiel.spaceshooter.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Game;
import objects.GameObject;

public class Message extends GameObject {
	
	float timer;
	String text;
	Color col;
	
	public Message(String text, Color col) {
		super(Game.w/2f-text.length()*9,Game.h/2f);
		this.text = text;
		this.col = col;
	}

	@Override
	public void update(float delta, float time) {
		timer += Game.deltaTime;
		if (timer > 3f) {
			selfDestruct();
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		// TODO Auto-generated method stub
		FontRenderer.drawText((int)x, (int)y, 18, text, FontLoader.getFont(col));
	}
}
