package com.brambasiel.spaceshooter.gui;

import java.awt.Color;

import com.brambasiel.spaceshooter.BasicMath;
import engine.Game;
import gui.Widget;

public class Healthbar extends Widget {

	public int width, height;
	float health, maxHealth, barW;

	public Healthbar(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public void change(float health, float maxHealth) {
		this.health = health;
		this.maxHealth = maxHealth;
	}
	
	@Override
	public void update() {
		barW = Math.max(health / maxHealth * width,0f);
	}

	float time = 0f;
	@Override
	public void draw() {
		Game.g.setColor(Color.LIGHT_GRAY);
		Game.g.fillRect(x, y, width, height);
		
		float h = Math.max(health/maxHealth,0f);
		Color c;
		c = BasicMath.blend(Color.GREEN, Color.RED, BasicMath.clamp(h,0f,1f));

		if (time > 0.25f)
		{
			Game.g.setColor(c);
			Game.g.fillRect(x, y, (int)barW, height);
		}
		
		if (h > 0.2f) {
			h = 0.5f;
		}
		else if (time > 0.5f) {
			time = 0f;
		}
		time += Game.deltaTime;
		
		//Add text
		Game.g.setColor(Color.BLACK);
		Game.g.drawString((int)Math.max(Math.ceil(health),0) + " / " + (int)Math.ceil(maxHealth), (int)(x+width/2-45), (int)(y+height/2)+5);
	}

}
